package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
