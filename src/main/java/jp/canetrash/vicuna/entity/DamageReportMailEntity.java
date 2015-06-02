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

	private String messageId;

	private Date attackDate;

	private String oppsiteAgentName;

	private Date createDate;

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

	public String getOppsiteAgentName() {
		return oppsiteAgentName;
	}

	public void setOppsiteAgentName(String oppsiteAgentName) {
		this.oppsiteAgentName = oppsiteAgentName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
