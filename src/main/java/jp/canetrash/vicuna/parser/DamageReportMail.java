package jp.canetrash.vicuna.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Ingress damage report mail
 * 
 * @author tfunato
 * 
 */
public class DamageReportMail implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -3138353120141222204L;

	private String messageId;

	private Date date;

	private String from;

	private String to;

	private String subject;

	private String oppositeAgentName;

	private String agentName;

	private List<Portal> portals = new ArrayList<>();;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOppositeAgentName() {
		return oppositeAgentName;
	}

	public void setOppositeAgentName(String oppositeAgentName) {
		this.oppositeAgentName = oppositeAgentName;
	}

	public List<Portal> getPortals() {
		return portals;
	}

	public void setPortals(List<Portal> portals) {
		this.portals = portals;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

}
