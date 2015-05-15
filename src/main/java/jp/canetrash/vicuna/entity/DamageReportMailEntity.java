package jp.canetrash.vicuna.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author tfunato
 *
 */
@Entity
public class DamageReportMailEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3109042741571228371L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String messageId;

	private Date attackDate;

	private String oppsiteAgentName;

	private Date createDate;

	@OneToMany
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
