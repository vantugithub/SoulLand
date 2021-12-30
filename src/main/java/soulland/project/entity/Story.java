package soulland.project.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	@Column
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "rep_story")
	private Story repStory;
	
	@OneToMany(mappedBy = "repStory")
	private Set<Story> replyStories = new HashSet<Story>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Story getRepStory() {
		return repStory;
	}

	public void setRepStory(Story repStory) {
		this.repStory = repStory;
	}

	public Set<Story> getReplyStories() {
		return replyStories;
	}

	public void setReplyStories(Set<Story> replyStories) {
		this.replyStories = replyStories;
	}

	public Story() {
	}

	public Story(Long id, String content, String image, Story repStory, Set<Story> replyStories) {
		this.id = id;
		this.content = content;
		this.image = image;
		this.repStory = repStory;
		this.replyStories = replyStories;
	}
	public Story(String content, String image) {
		this.content = content;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", content=" + content + ", image=" + image + "]";
	}
	
	
	
}
