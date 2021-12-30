package soulland.project.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

@SuppressWarnings("serial")
@Entity
public class Contributions implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int contributionType;
	
	@CreatedDate
	private LocalDateTime createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User userContributions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Memorials contributionMemorial;

	@OneToOne(fetch = FetchType.LAZY)
	private Tributes tribute;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Attachments attachment;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Story story;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Flowers flower;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getContributionType() {
		return contributionType;
	}

	public void setContributionType(int contributionType) {
		this.contributionType = contributionType;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public User getUserContributions() {
		return userContributions;
	}

	public void setUserContributions(User userContributions) {
		this.userContributions = userContributions;
	}

	public Memorials getContributionMemorial() {
		return contributionMemorial;
	}

	public void setContributionMemorial(Memorials contributionMemorial) {
		this.contributionMemorial = contributionMemorial;
	}

	public Tributes getTribute() {
		return tribute;
	}

	public void setTribute(Tributes tribute) {
		this.tribute = tribute;
	}

	public Attachments getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachments attachment) {
		this.attachment = attachment;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Flowers getFlower() {
		return flower;
	}

	public void setFlower(Flowers flower) {
		this.flower = flower;
	}

	public Contributions(Long id, int contributionType, LocalDateTime createDate, User userContributions,
			Memorials contributionMemorial, Tributes tribute, Attachments attachment, Story story, Flowers flower) {
		this.id = id;
		this.contributionType = contributionType;
		this.createDate = createDate;
		this.userContributions = userContributions;
		this.contributionMemorial = contributionMemorial;
		this.tribute = tribute;
		this.attachment = attachment;
		this.story = story;
		this.flower = flower;
	}

	public Contributions() {
	}

	@Override
	public String toString() {
		return "Contributions [id=" + id + ", contributionType=" + contributionType + "]";
	}
	
	
	
}
