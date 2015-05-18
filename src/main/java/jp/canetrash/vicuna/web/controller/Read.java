package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import jp.canetrash.vicuna.logic.OAuthLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tfunato
 *
 */
@Controller
public class Read {

	@Autowired
	private OAuthLogic oAuthLogic;
	
	@RequestMapping("/read")
	public String read(Map<String, Object> model) {
		
		if (!oAuthLogic.isAuthorized()) {
			return "redirect:" + oAuthLogic.getAuthPage();
		}
		return "read";
	}
}
