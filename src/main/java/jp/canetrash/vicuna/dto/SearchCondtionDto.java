package jp.canetrash.vicuna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tfunato
 *
 */
public class SearchCondtionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer start;
	private Integer length;
	private Integer draw;

	private List<SearchDto> search = new ArrayList<>();
	private List<OrderDto> order = new ArrayList<>();

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

	public List<SearchDto> getSearch() {
		return search;
	}

	public void setSearch(List<SearchDto> search) {
		this.search = search;
	}

	public List<OrderDto> getOrder() {
		return order;
	}

	public void setOrder(List<OrderDto> order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
