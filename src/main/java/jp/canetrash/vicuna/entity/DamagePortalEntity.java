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

	private String messageId;

	private Integer seq;

	private String portalId;

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

	public String getPortalId() {
		return portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public DamagePortalKey getPrimaryKey() {
		return new DamagePortalKey(messageId, seq);
	}

	public class DamagePortalKey implements Serializable {
		private static final long serialVersionUID = 1L;

		private String messageId;
		private Integer seq;

		public DamagePortalKey(String messageId, Integer seq) {
			this.messageId = messageId;
			this.seq = seq;
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
	}

}
