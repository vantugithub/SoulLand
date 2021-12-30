package soulland.project.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpFormRequest {
    
	@NotBlank
	@Size(min=2, max = 50)
	private String firstName;
	
	@NotBlank
	@Size(min=3, max = 50)
	private String lastName;
	
	@NotBlank
    @Size(min=3, max = 50)
	private String username;
	
	@NotBlank
    @Size(min=3, max = 50)
	private String password;
	
	@NotBlank
    @Size(min=10, max = 50)
	private String email;
	
	@NotBlank
    @Size(min=3, max = 100)
	private String address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	private Set<String> role;
	
	public Set<String> getRole() {
    	return this.role;
    }
    
    public void setRole(Set<String> role) {
    	this.role = role;
    }
	
}
