package jp.canetrash.vicuna.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.entity.DamagePortalEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamagePortalDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<DamagePortalEntity> findByGeoRange(
			PortalSearchConditionDto condition) {
		Object[] params = new Object[4];
		List<DamagePortalEntity> result = jdbcTemplate.query("", params,
				new RowMapper<DamagePortalEntity>() {

					public DamagePortalEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return null;
					}

				});
		return result;
	}

	public void save(DamagePortalEntity portalEntity) {
		// TODO Auto-generated method stub
		
	}
}
