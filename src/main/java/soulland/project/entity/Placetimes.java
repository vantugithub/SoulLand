package soulland.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Placetimes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String listImage;
	
	@Column
	private String location;
	
	@Column
	private String nickName;

	@Column
	private String birthDate;
	
	@Column
	private String deathDate;

	public Placetimes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getListImage() {
		return listImage;
	}

	public void setListImage(String listImage) {
		this.listImage = listImage;
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

	public Placetimes(Long id, String listImage, String location, String nickName, String birthDate, String deathDate) {
		this.id = id;
		this.listImage = listImage;
		this.location = location;
		this.nickName = nickName;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
	}
	
	
}
