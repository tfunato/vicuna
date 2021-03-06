package jp.canetrash.vicuna.web.websocket;

public class ProcessStatus {
	private int totalCount = 0;
	private int processCount = 0;
	private Status status = Status.STOPED;

	public enum Status {
		RUNNING, PREPARING, STOPED, ERROR
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getProcessCount() {
		return processCount;
	}

	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public synchronized void incrementCounter() {
		processCount++;
	}
}
