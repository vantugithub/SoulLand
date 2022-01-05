package soulland.project.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class CreateMemorialFormTemp implements Serializable{
	private String fullName;
	private String gender;
	private String causeOfDeath;
	private String ralationship;
	private String biography;
	private int privacyType;
	private MultipartFile files;
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
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	public String getRalationship() {
		return ralationship;
	}
	public void setRalationship(String ralationship) {
		this.ralationship = ralationship;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public int getPrivacyType() {
		return privacyType;
	}
	public void setPrivacyType(int privacyType) {
		this.privacyType = privacyType;
	}
	public MultipartFile getFiles() {
		return files;
	}
	public void setFiles(MultipartFile files) {
		this.files = files;
	}
	public CreateMemorialFormTemp() {
	}
	public CreateMemorialFormTemp(String fullName, String gender, String causeOfDeath, String ralationship,
			String biography, int privacyType, MultipartFile files) {
		this.fullName = fullName;
		this.gender = gender;
		this.causeOfDeath = causeOfDeath;
		this.ralationship = ralationship;
		this.biography = biography;
		this.privacyType = privacyType;
		this.files = files;
	}
	
}
