package jp.canetrash.vicuna.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tfunato
 *
 */
public class DamageReportMailEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3109042741571228371L;

	private String gmailId;

	private String messageId;

	private Date attackDate;

	private String oppositeAgentName;

	private Date createDate;

	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Date getAttackDate() {
		return attackDate;
	}

	public void setAttackDate(Date attackDate) {
		this.attackDate = attackDate;
	}

	public String getOppositeAgentName() {
		return oppositeAgentName;
	}

	public void setOppositeAgentName(String oppositeAgentName) {
		this.oppositeAgentName = oppositeAgentName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getGmailId() {
		return gmailId;
	}

}
