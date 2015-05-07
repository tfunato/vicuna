package jp.canetrash.vicuna.controller;

import jp.canetrash.vicuna.db.DamageReportMailDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Index {

	private DamageReportMailDao damageReportMailDao;
	
	@Autowired
	public void setDamageReportMailDao(DamageReportMailDao damageReportMailDao) {
		this.damageReportMailDao = damageReportMailDao;
	}

	@RequestMapping("/")
	@ResponseBody
	String index() {
		this.damageReportMailDao.selectAllDamageReport();
		return "Hello World!";
	}
}
