package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import jp.canetrash.vicuna.logic.OAuthLogic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tfunato
 *
 */
@Controller
public class Read {

	private static final Log logger = LogFactory.getLog(Read.class);

	@Autowired
	private OAuthLogic oAuthLogic;

	@RequestMapping("/read")
	public String read(Map<String, Object> model) {
		model.put("readMenuActive", true);
		if (!oAuthLogic.isAuthorized()) {
			String url = oAuthLogic.getAuthPage();
			logger.info("auth:" + url);
			return "redirect:" + url;
		}
		return "read";
	}
}
