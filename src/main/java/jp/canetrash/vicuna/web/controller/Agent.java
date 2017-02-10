package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tfunato
 *
 */
@Controller
public class Agent {

	@RequestMapping("/agent")
	public String agent(Map<String, Object> model) {
		return "agent";
	}
}
