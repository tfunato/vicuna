package jp.canetrash.vicuna.web.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jp.canetrash.vicuna.dto.SearchCondtionDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tfunato
 *
 */
@Controller
@RequestMapping("/datalist")
public class DataList {

	@RequestMapping("")
	public String list(Map<String, Object> model) {
		model.put("listMenuActive", true);
		return "list";
	}

	@RequestMapping("search")
	@ResponseBody
	public String search(@RequestParam Map<String, String> params) {
Set<Entry<String, String>> entrySet = params.entrySet();
Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){ 
			Entry<String, String> next = iterator.next();
			System.out.println("key:" + next.getKey() + " param:" + next.getValue());
		}

		return "{ \"draw\": 24, \"recordsTotal\": 1, \"recordsFiltered\": 1, \"data\": [ [\"ABCDEFG\", \"TEST\", \"2015-10-10 00:00\"]]}";
	}
}
