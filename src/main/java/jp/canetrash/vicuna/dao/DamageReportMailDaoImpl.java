package jp.canetrash.vicuna.dao;

import java.util.Date;

import jp.canetrash.vicuna.entity.DamageReportMailEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailDaoImpl extends
		AbstractDao<DamageReportMailEntity, String> implements
		DamageReportMailDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * @param gmailId
	 * @return
	 */
	@Override
	public boolean exists(String gmailId) {
		Integer count = this.namedParameterJdbcTemplate
				.queryForObject(
						"select count(gmail_id) from damage_report_mail where gmail_id = :gmailId",
						new MapSqlParameterSource("gmailId", gmailId),
						Integer.class);
		return count != 0 ? true : false;
	}

	/**
	 * @param mailEntity
	 * @return
	 */
	@Override
	public DamageReportMailEntity save(DamageReportMailEntity mailEntity) {

		mailEntity.setCreateDate(new Date());

		String sql = "insert into damage_report_mail (gmail_id, message_id, opposite_agent_name, attack_date, create_date) "
				+ " values(:gmailId, :messageId, :oppositeAgentName, :attackDate, :createDate)";
		this.namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(mailEntity));
		return mailEntity;
	}
}
