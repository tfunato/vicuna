package jp.canetrash.vicuna.parser;

import java.io.Serializable;

/**
 * Portal
 * 
 * @author tfunato
 *
 */
public class Portal implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -7343219909407218498L;

	private String portalName;

	private String portalIntelUrl;

	private String longitude;

	private String latitude;

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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
