package soulland.project.response;

import java.time.LocalDateTime;

public class FlowerResponse {
	private Long id;
	private String mess;
	private LocalDateTime lastUpdatedDate;
	
	
	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public FlowerResponse(Long id, String mess, LocalDateTime lastUpdatedDate) {
		this.id = id;
		this.mess = mess;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	
}
