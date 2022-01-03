package soulland.project.response;

public class DetailMemorials {
	private Long id;
	private String urlImage;
	private String causeOfDeath;
	private String fullName;
	private String gender;
	private String biography;
	private String location;
	private String nickName;
	private String birthDate;
	private String deathDate;
	private int privacyType;
	
	public int getPrivacyType() {
		return privacyType;
	}
	public void setPrivacyType(int privacyType) {
		this.privacyType = privacyType;
	}
	private String[] urlListImage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String[] getUrlListImage() {
		return urlListImage;
	}
	public void setUrlListImage(String[] urlListImage) {
		this.urlListImage = urlListImage;
	}
	public DetailMemorials(Long id, String urlImage, String causeOfDeath, String fullName, String gender,
			String biography, String location, String nickName, String birthDate, String deathDate,
			String[] urlListImage) {
		this.id = id;
		this.urlImage = urlImage;
		this.causeOfDeath = causeOfDeath;
		this.fullName = fullName;
		this.gender = gender;
		this.biography = biography;
		this.location = location;
		this.nickName = nickName;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.urlListImage = urlListImage;
	}
	public DetailMemorials() {
	}
	public DetailMemorials(Long id, String urlImage, String causeOfDeath, String fullName, String gender,
			String biography, String location, String nickName, String birthDate, String deathDate, int privacyType,
			String[] urlListImage) {
		this.id = id;
		this.urlImage = urlImage;
		this.causeOfDeath = causeOfDeath;
		this.fullName = fullName;
		this.gender = gender;
		this.biography = biography;
		this.location = location;
		this.nickName = nickName;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.privacyType = privacyType;
		this.urlListImage = urlListImage;
	}
	
	
	
}
