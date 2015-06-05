package jp.canetrash.vicuna.dao;

import java.util.Date;

import jp.canetrash.vicuna.entity.DamagePortalEntity;
import jp.canetrash.vicuna.entity.DamagePortalEntity.DamagePortalKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamagePortalDaoImpl extends
		AbstractDao<DamagePortalEntity, DamagePortalKey> implements
		DamagePortalDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * @param portalEntity
	 */
	@Override
	public DamagePortalEntity save(DamagePortalEntity portalEntity) {
		portalEntity.setCreateDate(new Date());
		String sql = "insert into damage_portal (message_id, seq, portal_id, create_date) "
				+ "values (:messageId, :seq, :portalId, :createDate)";
		this.namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(portalEntity));
		return portalEntity;
	}
}
