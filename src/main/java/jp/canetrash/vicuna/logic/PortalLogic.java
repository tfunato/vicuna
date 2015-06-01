package jp.canetrash.vicuna.logic;

import java.util.ArrayList;
import java.util.List;

import jp.canetrash.vicuna.dto.PortalDto;
import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.entity.DamagePortalEntity;
import jp.canetrash.vicuna.repository.DamagePortalRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortalLogic {

	private static Log logger = LogFactory.getLog(PortalLogic.class);

	@Autowired
	private DamagePortalRepository damagePortalRepository;

	/**
	 * @param condition
	 * @return
	 */
	public List<PortalDto> searchPortals(PortalSearchConditionDto condition) {
		logger.info("neLat:" + condition.getNeLat());
		logger.info("swLat:" + condition.getSwLat());
		logger.info("neLng:" + condition.getNeLng());
		logger.info("swLng:" + condition.getSwLng());
		List<DamagePortalEntity> searchResult = damagePortalRepository
				.findByGeoParameter(condition.getNeLat(), condition.getSwLat(),
						condition.getNeLng(), condition.getSwLng());
		List<PortalDto> result = new ArrayList<>();
		for (DamagePortalEntity entity : searchResult) {
			PortalDto portal = new PortalDto();
			portal.setTitle(entity.getPortalName());
			portal.setIntel(entity.getPortalIntelUrl());
			portal.setLat(entity.getLatitude());
			portal.setLng(entity.getLongitude());
			result.add(portal);
		}
		return result;
	}
}
