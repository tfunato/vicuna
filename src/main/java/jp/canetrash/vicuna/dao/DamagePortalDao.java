package jp.canetrash.vicuna.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.entity.DamagePortalEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author tfunato
 *
 */
@Component
public class DamagePortalDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * @param condition
	 * @return
	 */
	public List<DamagePortalEntity> findByGeoRange(
			PortalSearchConditionDto condition) {
		Assert.notNull(condition.getNeLat());
		Assert.notNull(condition.getSwLat());
		Assert.notNull(condition.getNeLng());
		Assert.notNull(condition.getSwLng());

		String sql = "select p.portal_name, p.latitude, p.longitude, p.portal_intel_url"
				+ " from damage_portal p where p.latitude <= :neLat"
				+ " and p.latitude >= :swLat and p.longitude <= :neLng"
				+ " and p.longitude >= :swLng group by p.latitude, p.longitude"
				+ " having max(p.create_date) limit 1000";

		List<DamagePortalEntity> result = namedParameterJdbcTemplate.query(sql,
				new BeanPropertySqlParameterSource(condition),
				new RowMapper<DamagePortalEntity>() {
					public DamagePortalEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						DamagePortalEntity entity = new DamagePortalEntity();
						entity.setPortalName(rs.getString("portal_name"));
						entity.setPortalIntelUrl(rs
								.getString("portal_intel_url"));
						entity.setLatitude(rs.getFloat("latitude"));
						entity.setLongitude(rs.getFloat("longitude"));
						return entity;
					}

				});
		return result;
	}

	/**
	 * @param portalEntity
	 */
	public void save(DamagePortalEntity portalEntity) {
		portalEntity.setCreateDate(new Date());
		String sql = "insert into damage_portal (message_id, seq, portal_name, longitude, latitude, portal_intel_url, create_date) "
				+ "values (:messageId, :seq, :portalName, :longitude, :latitude, :portalIntelUrl, :createDate)";
		this.namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(portalEntity));
	}
}
