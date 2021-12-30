package soulland.project.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulland.project.entity.User;
import soulland.project.repository.UserRepository;
import soulland.project.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void updateImageProfile(String urlImage, String username) {
		String url = "https://spring-api-test.s3.ap-southeast-1.amazonaws.com/";
		Optional<User> user = userRepository.findByUsername(username);
		user.get().setImage(url + urlImage);
		userRepository.save(user.get());
	}

	
	
}
