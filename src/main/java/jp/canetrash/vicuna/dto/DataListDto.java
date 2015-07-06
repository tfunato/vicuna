package jp.canetrash.vicuna.dto;

import java.util.List;

public class DataListDto {

	private Integer drow;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<String[]> data;

	public Integer getDrow() {
		return drow;
	}

	public void setDrow(int drow) {
		this.drow = drow;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<String[]> getData() {
		return data;
	}

	public void setData(List<String[]> data) {
		this.data = data;
	}
}
