package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tfunato
 *
 */
@Controller
public class Dashbord {

	@RequestMapping("/dashboard")
	public String dashbord(Map<String, Object> model) {
		model.put("dashbordMenuActive", true);
		return "dashboard";
	}
}
