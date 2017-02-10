package jp.canetrash.vicuna.dto;

/**
 * @author tfunato
 *
 */
public class PortalSearchConditionDto {

	private String neLat;
	private String swLat;
	private String neLng;
	private String swLng;
    private String agentName;

	public String getNeLat() {
		return neLat;
	}

	public void setNeLat(String neLat) {
		this.neLat = neLat;
	}

	public String getSwLat() {
		return swLat;
	}

	public void setSwLat(String swLat) {
		this.swLat = swLat;
	}

	public String getNeLng() {
		return neLng;
	}

	public void setNeLng(String neLng) {
		this.neLng = neLng;
	}

	public String getSwLng() {
		return swLng;
	}

	public void setSwLng(String swLng) {
		this.swLng = swLng;
	}

    public String getAgentName() {
        return this.agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
