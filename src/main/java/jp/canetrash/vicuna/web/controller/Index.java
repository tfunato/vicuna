package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
        model.put("message", "Hello world");
		return "index";
	}
}
