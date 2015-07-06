package jp.canetrash.vicuna.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.canetrash.vicuna.dto.DataListDto;
import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.dto.SearchCondtionDto;
import jp.canetrash.vicuna.entity.PortalEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private static Log logger = LogFactory.getLog(PortalDaoImpl.class);

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

	@Override
	public DataListDto findByCondition(SearchCondtionDto condition) {
		StringBuilder sql = new StringBuilder(
				" from portal p, damage_portal dp, damage_report_mail drm"
						+ " where p.id = dp.portal_id"
						+ " and dp.message_id = drm.message_id");
		// search condition
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if (condition.getSearch() != null && !condition.getSearch().isEmpty()
				&& condition.getSearch().get("value") != null
				&& condition.getSearch().get("value").length() != 0) {

			String searchValue = condition.getSearch().get("value");
			paramSource.addValue("searchValue", "%" + searchValue + "%");
			sql.append(" and (");
			sql.append(" drm.opposite_agent_name like :searchValue or p.portal_name like :searchValue ");
			sql.append(" ) ");
		}
		// order by
		StringBuilder orderByPart = new StringBuilder().append(" order by ");
		if (condition.getOrder() != null && condition.getOrder().size() == 0) {
			for (Map<String, String> orders : condition.getOrder()) {
				for (Entry<String, String> order : orders.entrySet()) {
					if (order.getKey().equals("column")) {
						if (order.getValue().equals("0")) {
							orderByPart.append("drm.opposite_agent_name");
						} else if (order.getValue().equals("1")) {
							orderByPart.append("p.portal_name");
						} else if (order.getValue().equals("2")) {
							orderByPart.append("drm.attack_date");
						}
					}
					if (order.getKey().equals("dir")) {
						orderByPart.append(" " + order.getValue());
					}
					// TODO multi order
				}
			}
		} else {
			orderByPart.append("drm.attack_date desc");
		}
		// paging
		orderByPart.append(" limit " + condition.getLength());
		orderByPart.append(" offset "
				+ (condition.getLength() * condition.getStart()));

		String retrievePart = "select drm.opposite_agent_name as opp_ag_name,  p.portal_name as portal_name, p.portal_intel_url as intel_url, drm.attack_date as attack_date ";
		String countPart = "select count(drm.opposite_agent_name) ";

		DataListDto dataListDto = new DataListDto();
		// count
		Integer recordsTotal = namedParameterJdbcTemplate.queryForObject(
				countPart.toString() + sql.toString(), paramSource,
				Integer.class);
		logger.info("TotalCount:" + recordsTotal);
		dataListDto.setRecordsTotal(recordsTotal);

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		List<String[]> dataList = namedParameterJdbcTemplate.query(
				retrievePart.toString() + sql.toString()
						+ orderByPart.toString(), paramSource,
				new RowMapper<String[]>() {
					public String[] mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String[] data = new String[3];
						data[0] = rs.getString("opp_ag_name");
						data[1] = rs.getString("portal_name");
						data[2] = sdf.format(rs.getDate("attack_date"));
						return data;
					}
				});
		dataListDto.setRecordsFiltered(dataList.size());
		dataListDto.setData(dataList);
		dataListDto.setDrow(condition.getDraw());
		return dataListDto;
	}

}
