package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import jp.canetrash.vicuna.logic.OAuthLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tfunato
 *
 */
@Controller
public class Auth {

	@Autowired
	private OAuthLogic oAuthLogic;

	@RequestMapping("/oauth2callback")
	public String auth(@RequestParam("code") String code, Map<String, Object> model) {
		oAuthLogic.storeCredential(code);
		return "read";
	}
}
