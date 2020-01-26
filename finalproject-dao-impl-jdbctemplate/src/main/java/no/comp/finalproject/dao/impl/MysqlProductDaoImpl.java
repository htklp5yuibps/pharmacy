package no.comp.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import no.comp.finalproject.dao.ProductDao;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.constant.ProductStatus;

public class MysqlProductDaoImpl implements ProductDao {

	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Long create(Product entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		PreparedStatementCreator pstc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(
						"insert into `product` (`name`, `info`, `cost`, `type`)" + " values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

				pst.setString(1, entity.getName());
				pst.setString(2, entity.getInfo());
				pst.setDouble(3, entity.getCost());
				pst.setString(4, entity.getType());

				return pst;
			}
		};
		
		if (entity.getImages() != null) {
			for (Long image: entity.getImages()) {
				template.update("insert into `productimages` (`product_id`, `image_id`)"
						+ " values (?, ?)", entity.getId(), image);
			}
		}

		template.update(pstc, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Product> read() {
		List<Product> products = template.query(
				"select `id`, `name`, `info`, `cost`, `status`, `type`" + " from `product`",
				productMapper);
		
		for (Product product: products) {
			List<Long> images = template.queryForList("select `image_id` from `productimage` where `product_id` = ?",
					Long.class, product.getId());
			
			Long[] array = new Long[images.size()];
			product.setImages(images.toArray(array));
		}
		
		return products;
	}

	@Override
	public Integer update(Product entity) {
		int rowAffected = template.update(
				"update `product` set `name` = ?, `info` = ?, `cost` = ?, status = ?, `type` = ?" + " where `id` = ?",
				entity.getName(), entity.getInfo(), entity.getCost(), entity.getStatus(), entity.getType(), entity.getId());

		template.update("delete from `productimage` where `product_id` = ?", entity.getId());

		if (entity.getImages() != null) {
			for (Long imageId : entity.getImages()) {
				template.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con
								.prepareStatement("insert into `productimage` (`product_id`, `image_id`)" + " values (?, ?)");
						pst.setLong(1, entity.getId());
						pst.setLong(2, imageId);
						return pst;
					}
				});
			}
		}

		return rowAffected;
	}

	@Override
	public Product delete(Long id) {
		template.update("update `product` set `status` = ? where `id` = ?",
				ProductStatus.UNAVAILABLE, id);
		
		return template.query("select `id`, `name`, `info`, `cost`, `status`, `type`"
				+ " from `product` where `id` = ?", productMapper, id).get(0);
	}

	private RowMapper<Product> productMapper = new RowMapper<Product>() {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getLong(1));
			product.setName(rs.getString(2));
			product.setInfo(rs.getString(3));
			product.setCost(rs.getDouble(4));
			product.setStatus(rs.getString(5));
			product.setType(rs.getString(6));
			return product;
		}
	};

}
