package jp.canetrash.vicuna.web.controller;

import java.util.Map;

import jp.canetrash.vicuna.repository.DamageReportMailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tfunato
 *
 */
@Controller
public class Index {
	@Autowired
	private DamageReportMailRepository damageReportMailRepository;

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		this.damageReportMailRepository.findAll();
		return "about";
	}
}
