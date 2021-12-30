package soulland.project.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Memorials implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String fullname;
	
	@Column
	private String gender;
	
	@Column
	private String causeOfDeath;
	
	@Column
	private String backgroundSong;
	
	@Column
	private String avatar;
	
	@Column(columnDefinition="TEXT")
	private String biology;
	
	@Column
	private int privacyType;
	
	@UpdateTimestamp
	private LocalDateTime dateCreated;
	
	@CreatedDate
	private LocalDateTime dateUpdated;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Placetimes placetime;
	
	@OneToMany(mappedBy = "memorialParticipants")
	private Set<Participants> participants = new HashSet<Participants>();
	
	@OneToMany(mappedBy = "contributionMemorial")
	private List<Contributions> contributions = new ArrayList<Contributions>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getBackgroundSong() {
		return backgroundSong;
	}

	public void setBackgroundSong(String backgroundSong) {
		this.backgroundSong = backgroundSong;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getPrivacyType() {
		return privacyType;
	}

	public void setPrivacyType(int privacyType) {
		this.privacyType = privacyType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Placetimes getPlacetime() {
		return placetime;
	}

	public void setPlacetime(Placetimes placetime) {
		this.placetime = placetime;
	}

	public Set<Participants> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participants> participants) {
		this.participants = participants;
	}

	public List<Contributions> getContributions() {
		return contributions;
	}

	public void setContributions(List<Contributions> contributions) {
		this.contributions = contributions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBiology() {
		return biology;
	}

	public void setBiology(String biology) {
		this.biology = biology;
	}

	public Memorials() {
	}

	public Memorials(Long id, String fullname, String gender, String causeOfDeath, String backgroundSong, String avatar,
			String biology, int privacyType, LocalDateTime dateCreated, LocalDateTime dateUpdated, User user,
			Placetimes placetime, Set<Participants> participants, List<Contributions> contributions) {
		this.id = id;
		this.fullname = fullname;
		this.gender = gender;
		this.causeOfDeath = causeOfDeath;
		this.backgroundSong = backgroundSong;
		this.avatar = avatar;
		this.biology = biology;
		this.privacyType = privacyType;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.user = user;
		this.placetime = placetime;
		this.participants = participants;
		this.contributions = contributions;
	}
	
	
}
