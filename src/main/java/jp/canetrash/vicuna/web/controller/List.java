package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import jp.canetrash.vicuna.dto.SearchCondtionDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tfunato
 *
 */
@Controller
public class List {

	@RequestMapping("/list")
	public String list(Map<String, Object> model) {
		model.put("listMenuActive", true);
		return "list";
	}

	@RequestMapping("list/search")
	@ResponseBody
	public String search(@RequestParam SearchCondtionDto condition) {

		return "{ \"draw\": 24, \"recordsTotal\": 1, \"recordsFiltered\": 1, \"data\": [ [\"ABCDEFG\", \"TEST\", \"2015-10-10 00:00\"]]}";
	}
}
