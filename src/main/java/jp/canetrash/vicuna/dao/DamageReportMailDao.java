package jp.canetrash.vicuna.dao;

import jp.canetrash.vicuna.entity.DamageReportMailEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(DamageReportMailEntity mailEntity) {
		// TODO Auto-generated method stub

	}

	public boolean exists(String messageId) {
		// TODO Auto-generated method stub
		return false;
	}

}
