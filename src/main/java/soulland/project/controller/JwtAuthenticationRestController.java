package soulland.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import soulland.project.entity.Role;
import soulland.project.entity.RoleName;
import soulland.project.entity.User;
import soulland.project.jwt.service.JwtInMemoryUserDetailsService;
import soulland.project.jwt.service.JwtTokenUtil;
import soulland.project.repository.RoleRepository;
import soulland.project.repository.UserRepository;
import soulland.project.request.JwtTokenRequest;
import soulland.project.request.SignUpForm;
import soulland.project.request.SignUpUserFormRequest;
import soulland.project.response.JwtTokenResponse;


@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationRestController {

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RoleRepository roleRepository; 
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtInMemoryUserDetailsService jwtInMemoryUserDetailsService;

  
  @RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {

	  Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), 
						authenticationRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest
    		.getUsername());

    final String token = jwtTokenUtil.generateToken(userDetails);
    final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
    return ResponseEntity.ok(new JwtTokenResponse(token, refreshToken, userDetails.getUsername(),"Bearer"));
  }

  @RequestMapping(value = "/api/auth/refresh", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException
  {
    String authToken = request.getHeader(tokenHeader);
    
    final String tokenn = authToken.substring(7);
    if(jwtTokenUtil.validateJwtToken(tokenn))
    {
    	String username = jwtTokenUtil.getUsernameFromToken(tokenn);
    	UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(username);
    	final String token = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return ResponseEntity.ok(new JwtTokenResponse(token, refreshToken,userDetails.getUsername(),"Bearer"));
    }
    else
    {
      return ResponseEntity.badRequest().body(null);
    }
    
  }
  

@SuppressWarnings("unchecked")
@RequestMapping(value = "/api/auth/signupforemployee", method = RequestMethod.POST)
  public ResponseEntity registerEmployee(@Validated @RequestBody SignUpForm signUpRequest) {
      if(userRepository.existsByUsername(signUpRequest.getUsername())) {
          return new ResponseEntity("Fail -> Username is already taken!",
                  HttpStatus.BAD_REQUEST);
      }

      if(userRepository.existsByEmail(signUpRequest.getEmail())) {
          return new ResponseEntity("Fail -> Email is already in use!",
                  HttpStatus.BAD_REQUEST);
      }

      // Creating user's account
      User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(), signUpRequest.getUsername(),
              signUpRequest.getEmail(), signUpRequest.getPhone() ,passwordEncoder.encode(signUpRequest.getPassword()));

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      strRoles.forEach(role -> {
      	switch(role) {
	    		case "admin":
	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);
	    			break;
//	    		case "pm":
//	            	Role pmRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//	            	roles.add(pmRole);
//	            	
//	    			break;
	    		default:
	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		roles.add(userRole);        			
      	}
      });
      
      user.setRoles(roles);
      userRepository.save(user);

      return ResponseEntity.ok().body("User registered successfully!");
  }



@SuppressWarnings("unchecked")
@RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST)
public ResponseEntity registerUser(@Validated @RequestBody SignUpUserFormRequest signUpRequest) {
    if(userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity("Fail -> Username is already taken!",
                HttpStatus.BAD_REQUEST);
    }

    if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity("Fail -> Email is already in use!",
                HttpStatus.BAD_REQUEST);
    }

    // Creating user's account
    User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(), signUpRequest.getUsername(),
            signUpRequest.getEmail(), signUpRequest.getPhone() ,passwordEncoder.encode(signUpRequest.getPassword()));

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
    roles.add(userRole);       
    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok().body("User registered successfully!");
}
  
  
  
  

  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

//  private void authenticate(String username, String password) {
//    Objects.requireNonNull(username);
//    Objects.requireNonNull(password);
//
//    try {
//      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    } catch (DisabledException e) {
//      throw new AuthenticationException("USER_DISABLED", e);
//    } catch (BadCredentialsException e) {
//      throw new AuthenticationException("INVALID_CREDENTIALS", e);
//    }
//  }
}

