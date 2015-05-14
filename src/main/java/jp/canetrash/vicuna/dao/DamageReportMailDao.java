package jp.canetrash.vicuna.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.canetrash.vicuna.entity.DamageReportMailEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DamageReportMailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<DamageReportMailEntity> selectAllDamageReport() {
		List<DamageReportMailEntity> result = jdbcTemplate.query(
				"SELECT * FROM DAMAGE_REPORT_MAIL",
				new BasicDamageReportMailRowMapper());
		return result;
	}

	class BasicDamageReportMailRowMapper implements
			RowMapper<DamageReportMailEntity> {
		@Override
		public DamageReportMailEntity mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			DamageReportMailEntity entity = new DamageReportMailEntity();
			entity.setMessageId(rs.getString("MESSAGE_ID"));
			entity.setAttackDate(rs.getDate("ATTACK_DATE"));
			entity.setOppsiteAgentName(rs.getString("OPPOSITE_AGENT_NAME"));
			entity.setPortalName(rs.getString("PORTAL_NAME"));
			entity.setPortalIntelUrl(rs.getString("PORTAL_INTEL_URL"));
			entity.setLongitude(rs.getFloat("LONGITUDE"));
			entity.setLatitude(rs.getFloat("LATITUDE"));
			return entity;
		}
	}
}
