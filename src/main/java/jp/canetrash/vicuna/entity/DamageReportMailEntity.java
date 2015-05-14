package jp.canetrash.vicuna.entity;

import java.util.Date;

/**
 * @author tfunato
 *
 */
public class DamageReportMailEntity {

	private String messageId;

	private Date attackDate;

	private String oppsiteAgentName;

	private String portalName;

	private String portalIntelUrl;

	private Float longitude;

	private Float latitude;

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

	public String getPortalName() {
		return portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	public String getPortalIntelUrl() {
		return portalIntelUrl;
	}

	public void setPortalIntelUrl(String portalIntelUrl) {
		this.portalIntelUrl = portalIntelUrl;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
}
