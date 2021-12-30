package soulland.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Participants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ralationship;
	
	@ManyToOne
	private User userParticipants;
	
	@ManyToOne
	private Memorials memorialParticipants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRalationship() {
		return ralationship;
	}

	public void setRalationship(String ralationship) {
		this.ralationship = ralationship;
	}

	public User getUserParticipants() {
		return userParticipants;
	}

	public void setUserParticipants(User userParticipants) {
		this.userParticipants = userParticipants;
	}

	public Memorials getMemorialParticipants() {
		return memorialParticipants;
	}

	public void setMemorialParticipants(Memorials memorialParticipants) {
		this.memorialParticipants = memorialParticipants;
	}

	public Participants() {
	}

	public Participants(Long id, String ralationship, User userParticipants, Memorials memorialParticipants) {
		this.id = id;
		this.ralationship = ralationship;
		this.userParticipants = userParticipants;
		this.memorialParticipants = memorialParticipants;
	}
	
	
}
