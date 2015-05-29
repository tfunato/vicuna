package jp.canetrash.vicuna.web.controller;

import java.util.ArrayList;
import java.util.List;

import jp.canetrash.vicuna.dto.PortalDto;
import jp.canetrash.vicuna.dto.PortalSearchConditionDto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Portals {

	private static Log logger = LogFactory.getLog(Portals.class);

	@RequestMapping("/portals")
	public @ResponseBody List<PortalDto> portals(
			PortalSearchConditionDto condition) {
		logger.info(condition.getNeLat());
		logger.info(condition.getSwLat());
		logger.info(condition.getNeLng());
		logger.info(condition.getSwLng());

		List<PortalDto> list = new ArrayList<>();
		PortalDto dto = new PortalDto();
		dto.setIntel("aaa");
		dto.setLat(1.001F);
		dto.setLng(2.001F);
		dto.setTitle("name");
		list.add(dto);
		return list;
	}
}
