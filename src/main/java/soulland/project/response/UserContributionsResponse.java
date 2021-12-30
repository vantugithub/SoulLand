package soulland.project.response;

public class UserContributionsResponse {
	private Long id;
	private String fullName;
	private int contributiontype;
	private Object object;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getContributiontype() {
		return contributiontype;
	}
	public void setContributiontype(int contributiontype) {
		this.contributiontype = contributiontype;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
