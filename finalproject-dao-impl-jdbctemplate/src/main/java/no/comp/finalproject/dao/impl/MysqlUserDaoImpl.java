package no.comp.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import no.comp.finalproject.dao.UserDao;
import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.UserStatus;

public class MysqlUserDaoImpl implements UserDao {

	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Long create(User entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		/* PST for user table */
		PreparedStatementCreator userPST = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pst = con.prepareStatement("insert into `user`"
						+ " (`username`, `password`)"
						+ " values (?, ?)", Statement.RETURN_GENERATED_KEYS);
				/* set username */
				pst.setString(1, entity.getUsername());
				/* set password */
				pst.setString(2, entity.getPassword());
				return pst;
			}
		};
		
		/* PST for userinfo table */
		PreparedStatementCreator userinfoPST = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pst = con.prepareStatement("insert into `userinfo`"
						+ " (`user_id`, `name`, `surname`, `patronymic`, `image_id`)"
						+ " values (?, ?, ?, ?, ?)");
				/* set user_id */
				pst.setLong(1, keyHolder.getKey().longValue());
				/* set name */
				pst.setString(2, entity.getName());
				/* set surname */
				pst.setString(3, entity.getSurname());
				/* set patronymic */
				pst.setString(4, entity.getPatronymic());
				/* set image_id */
				if (entity.getImageId() == null || entity.getImageId() <= 0) {
					pst.setNull(5, Types.BIGINT);
				} else {
					pst.setLong(5, entity.getImageId());
				}
				
				return pst;
			}
		};

		template.update(userPST, keyHolder);
		template.update(userinfoPST);

		return keyHolder.getKey().longValue();
	}

	@Override
	public List<User> read() {
		return template
				.query("select `id`, `username`, `password`, `name`, `surname`, `patronymic`, `role`, `status`, `image_id`"
						+ " from `user`" + " inner join `userinfo` on `id` = `user_id`", userMapper);
	}

	@Override
	public Integer update(User entity) {
		/* pst for user table update */
		PreparedStatementCreator userTablePst = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pst = con.prepareStatement("update `user`"
						+ " set `username` = ?,"
						+ " `password` = ?,"
						+ " `role` = ?,"
						+ " `status` = ?"
						+ " where `id` = ?");
				
				pst.setString(1, entity.getUsername());
				pst.setString(2, "{bcrypt}" + entity.getPassword());
				pst.setString(3, entity.getRole());
				pst.setInt(4, entity.getStatus());
				pst.setLong(5, entity.getId());
				
				return pst;
			}
		};
		
		/* pst for userinfo table update */
		PreparedStatementCreator userinfoTablePst = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pst = con.prepareStatement("update `userinfo`"
						+ " set `name` = ?,"
						+ " `surname` = ?,"
						+ " `patronymic` = ?,"
						+ " `image_id` = ?"
						+ " where `user_id` = ?");
				
				pst.setString(1, entity.getName());
				pst.setString(2, entity.getSurname());
				pst.setString(3, entity.getPatronymic());
				if (entity.getImageId() == null || entity.getImageId() <= 0) {
					pst.setNull(4, Types.BIGINT);					
				} else {
					pst.setLong(4, entity.getImageId());
				}
				pst.setLong(5, entity.getId());
				
				return pst;
			}
		};
		
		template.update(userTablePst);
		return template.update(userinfoTablePst);
	}

	@Override
	public User delete(Long id) {
		template.update("update `user` set `status` = ? where `id` = ?",
				UserStatus.INACTIVE.ordinal(), id);
		
		return template.query(
				"select `id`, `username`, `password`, `name`, `surname`, `patronymic`, `role`, `status`, `image_id`"
				+ " from `user`"
				+ " inner join `userinfo` on `id` = `user_id` where `id` = ?", userMapper, id).get(0);
	}

	private RowMapper<User> userMapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			User user = new User();
			user.setId(rs.getLong(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3).replace("{bcrypt}", ""));
			System.out.println(rs.getString(3).replace("{bcrypt}", ""));
			user.setName(rs.getString(4));
			user.setSurname(rs.getString(5));
			user.setPatronymic(rs.getString(6));
			user.setRole(rs.getString(7));
			user.setStatus(rs.getInt(8));
			user.setImageId(rs.getLong(9));

			return user;
		}

	};

}
