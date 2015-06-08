package jp.canetrash.vicuna.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.entity.PortalEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

@Component
public class PortalDaoImpl extends AbstractDao<PortalEntity, String> implements
		PortalDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Value("${visited.portal.search.result.size}")
	private String resultSize;

	@Override
	public PortalEntity save(PortalEntity entity) {
		String input = entity.getLatitude().toString()
				+ entity.getLongitude().toString();
		entity.setId(DigestUtils.md5DigestAsHex(input.getBytes()));
		entity.setCreateDate(new Date());
		String sql = "insert into portal (id, portal_name, latitude, longitude, portal_intel_url, create_date) "
				+ "values (:id, :portalName, :latitude, :longitude, :portalIntelUrl, :createDate)";
		this.namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(entity));
		return entity;
	}

	@Override
	public PortalEntity findByLatLng(Float latitude, Float longitude) {
		String sql = "select * from portal where latitude = :latitude and longitude = :longitude";
		List<PortalEntity> result = namedParameterJdbcTemplate.query(sql,
				new MapSqlParameterSource("latitude", latitude).addValue(
						"longitude", longitude), new RowMapper<PortalEntity>() {

					@Override
					public PortalEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						PortalEntity e = new PortalEntity();
						e.setId(rs.getString("id"));
						e.setLatitude(rs.getFloat("latitude"));
						e.setLongitude(rs.getFloat("longitude"));
						return e;
					}
				});
		if (result.size() == 0) {
			return null;
		} else if (result.size() > 1) {
			throw new IncorrectResultSizeDataAccessException("data size error",
					1, result.size());
		} else {
			return result.get(0);
		}
	}

	@Override
	public List<PortalEntity> findByLatLngRange(
			PortalSearchConditionDto condition) {

		Assert.notNull(condition.getNeLat());
		Assert.notNull(condition.getSwLat());
		Assert.notNull(condition.getNeLng());
		Assert.notNull(condition.getSwLng());

		String sql = "select * from portal p where p.latitude <= :neLat"
				+ " and p.latitude >= :swLat and p.longitude <= :neLng"
				+ " and p.longitude >= :swLng limit " + resultSize;

		List<PortalEntity> result = namedParameterJdbcTemplate.query(sql,
				new BeanPropertySqlParameterSource(condition),
				new RowMapper<PortalEntity>() {
					public PortalEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						PortalEntity entity = new PortalEntity();
						entity.setId(rs.getString("id"));
						entity.setPortalName(rs.getString("portal_name"));
						entity.setPortalIntelUrl(rs
								.getString("portal_intel_url"));
						entity.setLatitude(rs.getFloat("latitude"));
						entity.setLongitude(rs.getFloat("longitude"));
						entity.setCreateDate(rs.getDate("create_date"));
						return entity;
					}

				});
		return result;
	}

}
