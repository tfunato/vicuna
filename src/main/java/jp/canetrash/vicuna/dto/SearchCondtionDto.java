package jp.canetrash.vicuna.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author tfunato
 *
 */
public class SearchCondtionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> search;
	private List<Map<String, String>> order;
	private Integer start;
	private Integer length;
	private Integer draw;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Map<String, String> getSearch() {
		return search;
	}

	public void setSearch(Map<String, String> search) {
		this.search = search;
	}

	public List<Map<String, String>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<String, String>> order) {
		this.order = order;
	}

}
