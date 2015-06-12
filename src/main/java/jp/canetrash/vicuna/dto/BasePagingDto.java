package jp.canetrash.vicuna.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tfunato
 *
 */
public abstract class BasePagingDto<E> {

	private long currentPageNumber;
	private long allRecordCount;
	private long allPageCount;
	private long startRecordNo;
	private long endRecordNo;
	private boolean existPrePage;
	private boolean existNextPage;
	private long pageSize;
	private List<Integer> pageNumberList;

	private List<E> list = new ArrayList<>();

	public long getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(long currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public long getAllRecordCount() {
		return allRecordCount;
	}

	public void setAllRecordCount(long allRecordCount) {
		this.allRecordCount = allRecordCount;
	}

	public long getAllPageCount() {
		return allPageCount;
	}

	public void setAllPageCount(long allPageCount) {
		this.allPageCount = allPageCount;
	}

	public long getStartRecordNo() {
		return startRecordNo;
	}

	public void setStartRecordNo(long startRecordNo) {
		this.startRecordNo = startRecordNo;
	}

	public long getEndRecordNo() {
		return endRecordNo;
	}

	public void setEndRecordNo(long endRecordNo) {
		this.endRecordNo = endRecordNo;
	}

	public boolean isExistPrePage() {
		return existPrePage;
	}

	public void setExistPrePage(boolean existPrePage) {
		this.existPrePage = existPrePage;
	}

	public boolean isExistNextPage() {
		return existNextPage;
	}

	public void setExistNextPage(boolean existNextPage) {
		this.existNextPage = existNextPage;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public List<Integer> getPageNumberList() {
		return pageNumberList;
	}

	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
}
