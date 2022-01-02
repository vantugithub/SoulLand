package soulland.project.request;

public class CommentFlowerRquest {
	private Long idMemo;
	private String textComment;
	public Long getIdMemo() {
		return idMemo;
	}
	public void setIdMemo(Long idMemo) {
		this.idMemo = idMemo;
	}
	public String getTextComment() {
		return textComment;
	}
	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}
	public CommentFlowerRquest(Long idMemo, String textComment) {
		this.idMemo = idMemo;
		this.textComment = textComment;
	}
	
	
}
