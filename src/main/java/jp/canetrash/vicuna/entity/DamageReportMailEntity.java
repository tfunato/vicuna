package jp.canetrash.vicuna.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author tfunato
 *
 */
@Entity
@Table(name = "DAMAGE_REPORT_MAIL")
public class DamageReportMailEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3109042741571228371L;

	@Id
	@Column(name = "MESSAGE_ID", nullable = false)
	private String messageId;

	@Column(name = "ATTACK_DATE")
	private Date attackDate;

	@Column(name = "OPPSITE_AGENT_NAME")
	private String oppsiteAgentName;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@OneToMany(mappedBy = "damageReportMailEntity")
	private List<DamagePortalEntity> portals;

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

	public List<DamagePortalEntity> getPortals() {
		return portals;
	}

	public void setPortals(List<DamagePortalEntity> portals) {
		this.portals = portals;
	}

}
