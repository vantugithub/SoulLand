package soulland.project.response;

import java.time.LocalDateTime;

public class CommentFlowerResponse {
	private String username;
	private String textComment;
	private LocalDateTime dateTime; 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTextComment() {
		return textComment;
	}
	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}
	public CommentFlowerResponse(String username, String textComment) {
		this.username = username;
		this.textComment = textComment;
	}
	
	
}
