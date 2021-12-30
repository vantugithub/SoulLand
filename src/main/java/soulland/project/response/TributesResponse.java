package soulland.project.response;

import java.util.List;

public class TributesResponse {
	private Long id;
	private String eulogy;
	private List<TributesResponse> list;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEulogy() {
		return eulogy;
	}
	public void setEulogy(String eulogy) {
		this.eulogy = eulogy;
	}
	public List<TributesResponse> getList() {
		return list;
	}
	public void setList(List<TributesResponse> list) {
		this.list = list;
	}
	public TributesResponse(Long id, String eulogy, List<TributesResponse> list) {
		this.id = id;
		this.eulogy = eulogy;
		this.list = list;
	}
	
	
}
