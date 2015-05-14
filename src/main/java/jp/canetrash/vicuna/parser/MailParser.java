package jp.canetrash.vicuna.parser;

import javax.mail.Message;


/**
 * Ingress Damage Report Parser
 * 
 * @author tfunato
 * 
 */
public interface MailParser {
	DamageReportMail parse(Message msg);
}
