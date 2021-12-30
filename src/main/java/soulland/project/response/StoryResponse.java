package soulland.project.response;

import java.util.Set;

public class StoryResponse {
	private Long id;
	private String content;
	private String urlImage;
	private Set<StoryResponse> list;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public Set<StoryResponse> getList() {
		return list;
	}
	public void setList(Set<StoryResponse> list) {
		this.list = list;
	}
	public StoryResponse(Long id, String content, String urlImage, Set<StoryResponse> list) {
		this.id = id;
		this.content = content;
		this.urlImage = urlImage;
		this.list = list;
	}
	
	
	
}
