package soulland.project.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User extends DateAudit {

	private static final long serialVersionUID = -6109789394548885438L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String lastName;
	
	@Column
	private String firstName;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	@Column
	private String image;
	
	@Column
	private String phone;
	
	@Column
	private boolean active;

	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<Memorials> memorials = new HashSet<Memorials>();
	
	@OneToMany(mappedBy = "userParticipants")
	private Set<Participants> participants = new HashSet<Participants>();
	
	@OneToMany(mappedBy = "userContributions")
	private Set<Contributions> contributions = new HashSet<Contributions>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Memorials> getMemorials() {
		return memorials;
	}

	public void setMemorials(Set<Memorials> memorials) {
		this.memorials = memorials;
	}

	public Set<Participants> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participants> participants) {
		this.participants = participants;
	}

	public Set<Contributions> getContributions() {
		return contributions;
	}

	public void setContributions(Set<Contributions> contributions) {
		this.contributions = contributions;
	}
	
	public User(String firstName ,String lastName, String username, String email,String phone,String password) {
		this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

	public User(Long id, String lastName, String firstName, String username, String password, String email,
			String address, String image, String phone, boolean active, Set<Role> roles, Set<Memorials> memorials,
			Set<Participants> participants, Set<Contributions> contributions) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.image = image;
		this.phone = phone;
		this.active = active;
		this.roles = roles;
		this.memorials = memorials;
		this.participants = participants;
		this.contributions = contributions;
	}

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id;
	}
	
	
}
