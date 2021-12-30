package soulland.project.response;

public class MemoHomeResponse {
	private long id;
	private String avatar;
	private String causeOfDeath;
	private String fullName;
	private String gender;
	private String biography;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public MemoHomeResponse(long id, String avatar, String causeOfDeath, String fullName, String gender, String biography) {
		this.id = id;
		this.avatar = avatar;
		this.causeOfDeath = causeOfDeath;
		this.fullName = fullName;
		this.gender = gender;
		this.biography = biography;
	}
	
	
	
}
