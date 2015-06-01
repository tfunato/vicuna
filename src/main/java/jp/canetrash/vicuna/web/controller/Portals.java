package jp.canetrash.vicuna.web.controller;

import java.util.List;

import jp.canetrash.vicuna.dto.PortalDto;
import jp.canetrash.vicuna.dto.PortalSearchConditionDto;
import jp.canetrash.vicuna.logic.PortalLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Portals {

	@Autowired
	private PortalLogic portalLogic;

	@RequestMapping("/portals")
	public @ResponseBody List<PortalDto> portals(
			PortalSearchConditionDto condition) {

		return portalLogic.searchPortals(condition);
	}
}
