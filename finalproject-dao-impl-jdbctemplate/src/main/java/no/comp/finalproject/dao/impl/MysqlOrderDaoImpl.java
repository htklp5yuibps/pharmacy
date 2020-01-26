package no.comp.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import no.comp.finalproject.dao.OrderDao;
import no.comp.finalproject.entity.Order;
import no.comp.finalproject.entity.constant.OrderStatus;

public class MysqlOrderDaoImpl implements OrderDao {

	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Long create(Order entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		PreparedStatementCreator pstc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(
						"insert into `order` (`user_id`) values (?)",
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, entity.getUserId());
				return pst;
			}
		};

		template.update(pstc, keyHolder);
		
		Set<Entry<Long, Integer>> parts = entity.getParts().entrySet();
		
		for (Entry<Long, Integer> part: parts) {
			template.update("insert into `orderdetail` (`order_id`, `product_id`, `count`) values (?, ?, ?)",
					keyHolder.getKey().longValue(), part.getKey(), part.getValue());
		}
		
		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Order> read() {
		List<Order> orders = template.query("select `id`, `user_id`, `order_date`, `end_date`, `status`" + " from `order`",
				orderMapper);

		for (Order order : orders) {
			order.setParts(this.getOrderParts(order.getId()));
		}

		return orders;
	}

	@Override
	public Integer update(Order order) {
		return template.update("update `order`"
				+ " set `user_id` = ?,"
				+ " `order_date` = ?,"
				+ " `end_date` = ?,"
				+ " `status` = ?"
				+ " where `id` = ?",
				order.getUserId(), order.getOrderDate(), order.getEndDate(),
				order.getStatus(), order.getId());
		
//		int affectedRows = 0;
//		template.update("delete from `orderdetail` where `order_id` = ?", entity.getId());
//		
//		for (Entry<Long, Integer> part: entity.getParts().entrySet()) {
//			affectedRows += template.update("insert into `orderdetail` (`order_id`, `product_id`, `count`)"
//					+ " values (?, ?, ?)", entity.getId(), part.getKey(), part.getValue());
//		}
//		
//		return affectedRows;
	}

	@Override
	public Order delete(Long id) {
		template.update("update `order` set `status` = ?, `end_date` = now()" + " where `id` = ?",
				OrderStatus.CANCELED.toString().toLowerCase(), id);
		Order order = template
				.query("select `id`, `user_id`, `order_date`, `end_date`, `status`" + " from `order` where `id` = ?",
						orderMapper, id)
				.get(0);

		order.setParts(this.getOrderParts(id));

		return order;
	}

	private Map<Long, Integer> getOrderParts(Long orderId) {
		Map<Long, Integer> result = new HashMap<>();
		List<Map<Long, Integer>> parts = template
				.query("select `product_id`, `count` from `orderdetail` where `order_id` = ?", partMapper, orderId);

		for (Map<Long, Integer> part : parts) {
			result.putAll(part);
		}

		return result;
	}

	private RowMapper<Order> orderMapper = new RowMapper<Order>() {

		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();

			order.setId(rs.getLong(1));
			order.setUserId(rs.getLong(2));
			try {
				order.setOrderDate(DateConverter.convert(rs.getString(3)));
				order.setEndDate(DateConverter.convert(rs.getString(4)));
			} catch (ParseException exc) {
				throw new RuntimeException(exc);
			}
			order.setStatus(rs.getString(5));

			return order;
		}

	};

	private RowMapper<Map<Long, Integer>> partMapper = new RowMapper<Map<Long, Integer>>() {

		@Override
		public Map<Long, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
			Map<Long, Integer> part = new HashMap<>();
			part.put(rs.getLong(1), rs.getInt(2));
			return part;
		}

	};

}
