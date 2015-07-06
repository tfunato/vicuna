package jp.canetrash.vicuna.web.controller;

import java.util.Map;
import java.util.Map.Entry;

import jp.canetrash.vicuna.dto.DataListDto;
import jp.canetrash.vicuna.dto.SearchCondtionDto;
import jp.canetrash.vicuna.logic.PortalLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tfunato
 *
 */
@Controller
@RequestMapping("/datalist")
public class DataList {
	
	@Autowired
	private PortalLogic portalLogic;

	@RequestMapping
	public String list(Map<String, Object> model) {
		model.put("listMenuActive", true);
		return "list";
	}

	@RequestMapping("search")
	@ResponseBody
	public DataListDto search(SearchCondtionDto params, BindingResult result) {
		System.out.println(result.getErrorCount());
		System.out.println(result.getAllErrors());
		for (Map<String, String> param : params.getOrder()) {
			for (Entry<String, String> p : param.entrySet()) {
				System.out.println(p.getKey() + ":" + p.getValue());
			}
		}
		return portalLogic.searchReport(params);
	}
}
