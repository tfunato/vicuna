package jp.canetrash.vicuna.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author tfunato
 *
 */
@Entity
@Table(name = "DAMAGE_PORTAL")
public class DamagePortalEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 6742715802503773598L;

	public DamagePortalEntity() {
	}

	public DamagePortalEntity(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID", nullable = false)
	private String id;

	@Column(name = "MESSAGE_ID", nullable = false)
	private String messageId;

	@Column(name = "SEQ", nullable = false)
	private Integer seq;

	@Column(name = "PORTAL_NAME")
	private String portalName;

	@Column(name = "PORTA_LINTEL_URL")
	private String portalIntelUrl;

	@Column(name = "LONGITUDE")
	private Float longitude;

	@Column(name = "LATITUDE")
	private Float latitude;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(@JoinColumn(name = "MESSAGE_ID", referencedColumnName = "MESSAGE_ID", insertable = false, updatable = false))
	private DamageReportMailEntity damageReportMailEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public DamageReportMailEntity getDamageReportMailEntity() {
		return damageReportMailEntity;
	}

	public void setDamageReportMailEntity(
			DamageReportMailEntity damageReportMailEntity) {
		this.damageReportMailEntity = damageReportMailEntity;
	}

}
