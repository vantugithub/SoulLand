package soulland.project.jwt.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import soulland.project.entity.User;

public class JwtUserDetails implements UserDetails {

		private static final long serialVersionUID = 1L;

		private Long id;

	    private String name;

	    private String username;

	    private String email;

	    @JsonIgnore
	    private String password;

	    private Collection authorities;

	    public JwtUserDetails(Long id, String name, 
				    		String username, String email, String password, 
				    		Collection authorities) {
	        this.id = id;
	        this.name = name;
	        this.username = username;
	        this.email = email;
	        this.password = password;
	        this.authorities = authorities;
	    }

	    public static JwtUserDetails build(User user) {
	        List authorities = user.getRoles().stream().map(role ->
	                new SimpleGrantedAuthority(role.getName().name())
	        ).collect(Collectors.toList());

	        return new JwtUserDetails(
	                user.getId(),
	                user.getFirstName(),
	                user.getUsername(),
	                user.getEmail(),
	                user.getPassword(),
	                authorities
	        );
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    @Override
	    public String getUsername() {
	        return username;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public Collection getAuthorities() {
	        return authorities;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        
	        JwtUserDetails user = (JwtUserDetails) o;
	        return Objects.equals(id, user.id);
	    }
	}

