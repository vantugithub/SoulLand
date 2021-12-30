package soulland.project.response;

public class FlowerResponse {
	private Long id;
	private String mess;
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
	public FlowerResponse(Long id, String mess) {
		this.id = id;
		this.mess = mess;
	}
	
}
