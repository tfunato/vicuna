package jp.canetrash.vicuna.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tfunato
 *
 */
public class DamagePortalEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 6742715802503773598L;

	public DamagePortalEntity() {
	}

	private String messageId;

	private Integer seq;

	private String portalName;

	private String portalIntelUrl;

	private Float longitude;

	private Float latitude;

	private Date createDate;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
