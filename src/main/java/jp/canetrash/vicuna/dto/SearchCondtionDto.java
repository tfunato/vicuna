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

	private Integer start;
	private Integer length;
	private Integer draw;

	/*
	private List<Map<String, String>> columns;
	private List<Map<String, String>> order;
	*/

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

	/*
	public List<Map<String, String>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<String, String>> columns) {
		this.columns = columns;
	}

	public List<Map<String, String>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<String, String>> order) {
		this.order = order;
	}
	*/

}
