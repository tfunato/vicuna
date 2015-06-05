package jp.canetrash.vicuna.dao;

import java.util.List;

import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.entity.DamagePortalEntity;
import jp.canetrash.vicuna.entity.DamagePortalEntity.DamagePortalKey;

/**
 * @author tfunato
 *
 */
public interface DamagePortalDao extends
		Dao<DamagePortalEntity, DamagePortalKey> {

	/**
	 * search by geo info
	 * 
	 * @param condition
	 * @return
	 */
	List<DamagePortalEntity> findByGeoRange(PortalSearchConditionDto condition);
}
