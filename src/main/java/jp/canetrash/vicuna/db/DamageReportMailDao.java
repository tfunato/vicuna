package jp.canetrash.vicuna.db;

import java.util.ArrayList;
import java.util.List;

import jp.canetrash.vicuna.entity.DamageReportMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DamageReportMailDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public DamageReportMailDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<DamageReportMail> selectAllDamageReport() {
		jdbcTemplate.execute("select * from DAMAGE_REPORT_MAIL");
		return new ArrayList<>();
	}
}
