package jp.canetrash.vicuna.logic;

import jp.canetrash.vicuna.parser.MailParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailLogic {

	@Autowired
	private  MailParser jsoupMailParser;
	
}
