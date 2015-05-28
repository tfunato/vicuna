package jp.canetrash.vicuna.dto;

/**
 * @author tfunato
 *
 */
public class PortalDto {

	/** portal name */
	private String title;

	private Float lat;

	private Float lng;

	/** intel map url */
	private String intel;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public String getIntel() {
		return intel;
	}

	public void setIntel(String intel) {
		this.intel = intel;
	}
}
