package soulland.project.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class PlacetimeRequest implements Serializable{
	
	private Long idMemorials;
	private String location;
	private String nickName;
	private String birthDate;
	private String deathDate;
	private MultipartFile[] files;
	
	public Long getIdMemorials() {
		return idMemorials;
	}
	public void setIdMemorials(Long idMemorials) {
		this.idMemorials = idMemorials;
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
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	
}
