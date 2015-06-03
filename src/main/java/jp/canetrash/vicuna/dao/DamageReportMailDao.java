package jp.canetrash.vicuna.dao;

import java.util.Date;

import jp.canetrash.vicuna.entity.DamageReportMailEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean exists(String gmailId) {
		Integer count = this.jdbcTemplate
				.queryForObject(
						"select count(gmail_id) from damage_report_mail where gmail_id = ?",
						new Object[] { gmailId }, Integer.class);
		return count != 0 ? true : false;
	}

	public void save(DamageReportMailEntity mailEntity) {

		mailEntity.setCreateDate(new Date());

		String sql = "insert into damage_report_mail (gmail_id, message_id, opposite_agent_name, attack_date, create_date) "
				+ " values(:gmailId, :messageId, :attackDate, :oppositeAgentName, :createDate)";
		this.namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(mailEntity));
	}
}
