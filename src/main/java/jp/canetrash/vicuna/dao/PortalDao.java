package jp.canetrash.vicuna.dao;

import java.util.List;

import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.entity.PortalEntity;

/**
 * @author tfunato
 *
 */
public interface PortalDao extends Dao<PortalEntity, String> {

	/**
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	PortalEntity findByLatLng(Float latitude, Float longitude);

	/**
	 * @param condition
	 * @return
	 */
	List<PortalEntity> findByLatLngRange(PortalSearchConditionDto condition);
}
