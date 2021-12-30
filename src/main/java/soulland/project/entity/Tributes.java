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
public class Tributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String eulogy;
	
	@ManyToOne
	@JoinColumn(name = "rep_tribute")
	private Tributes repTribute;
	
	@OneToMany(mappedBy = "repTribute")
	private Set<Tributes> replyTributes = new HashSet<Tributes>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEulogy() {
		return eulogy;
	}

	public void setEulogy(String eulogy) {
		this.eulogy = eulogy;
	}

	public Tributes getRepTribute() {
		return repTribute;
	}

	public void setRepTribute(Tributes repTribute) {
		this.repTribute = repTribute;
	}

	public Set<Tributes> getReplyTributes() {
		return replyTributes;
	}

	public void setReplyTributes(Set<Tributes> replyTributes) {
		this.replyTributes = replyTributes;
	}

	public Tributes(Long id, String eulogy, Tributes repTribute, Set<Tributes> replyTributes) {

		this.id = id;
		this.eulogy = eulogy;
		this.repTribute = repTribute;
		this.replyTributes = replyTributes;
	}

	public Tributes() {

	}
	
	
}
