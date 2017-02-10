package jp.canetrash.vicuna.logic;

import java.util.ArrayList;
import java.util.List;

import jp.canetrash.vicuna.dao.PortalDao;
import jp.canetrash.vicuna.dto.DataListDto;
import jp.canetrash.vicuna.dto.PortalDto;
import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.dto.SearchCondtionDto;
import jp.canetrash.vicuna.entity.PortalEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortalLogic {

	private static Log logger = LogFactory.getLog(PortalLogic.class);

	@Autowired
	private PortalDao portalDao;

	/**
	 * portal search
	 * 
	 * @param condition
	 * @return
	 */
	public List<PortalDto> searchPortals(PortalSearchConditionDto condition) {
		logger.info("neLat:" + condition.getNeLat());
		logger.info("swLat:" + condition.getSwLat());
		logger.info("neLng:" + condition.getNeLng());
		logger.info("swLng:" + condition.getSwLng());
		logger.info("query start...");
		List<PortalEntity> searchResult = portalDao
				.findByLatLngRange(condition);
		logger.info("query end...");
		logger.info("mapping start...");
		List<PortalDto> result = new ArrayList<>();
		for (PortalEntity entity : searchResult) {
			PortalDto portal = new PortalDto();
			portal.setTitle(entity.getPortalName());
			portal.setIntel(entity.getPortalIntelUrl());
			portal.setLat(entity.getLatitude());
			portal.setLng(entity.getLongitude());
			result.add(portal);
		}
		logger.info("mapping end...");
		return result;
	}

	/**
	 * portal search
	 * 
	 * @param condition
	 * @return
	 */
	public List<PortalDto> searchPortalsByAgentName(PortalSearchConditionDto condition) {
		logger.info("agentName:" + condition.getAgentName());
		logger.info("query start...");
		List<PortalEntity> searchResult = portalDao
				.findByOppositeAgentName(condition);
		logger.info("query end...");
		logger.info("mapping start...");
		logger.info("result...:" + searchResult.size());
		List<PortalDto> result = new ArrayList<>();
		for (PortalEntity entity : searchResult) {
			PortalDto portal = new PortalDto();
			portal.setTitle(entity.getPortalName());
			portal.setLat(entity.getLatitude());
			portal.setLng(entity.getLongitude());
            portal.setPortalId(entity.getId());
			result.add(portal);
		}
		logger.info("mapping end...");
		return result;
	}

	/**
	 * report search
	 * 
	 * @param condition
	 * @return
	 */
	public DataListDto searchReport(SearchCondtionDto condition) {

		return portalDao.findByCondition(condition);
	}
}
