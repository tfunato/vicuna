package jp.canetrash.vicuna.dto;

/**
 * @author tfunato
 *
 */
public abstract class BaseSearchListCondtionDto {

	private int pageNumber;
	private int pageSize;
	private int pageRange;

	private String orderColumn;
	private String order;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageRange() {
		return pageRange;
	}

	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
