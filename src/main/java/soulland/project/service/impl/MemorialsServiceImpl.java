package soulland.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import soulland.project.entity.Memorials;
import soulland.project.entity.Placetimes;
import soulland.project.entity.User;
import soulland.project.repository.MemorialsRepository;
import soulland.project.repository.PlacetimesRepository;
import soulland.project.repository.UserRepository;
import soulland.project.request.CreateMemorialForm;
import soulland.project.response.MemoHomeResponse;
import soulland.project.response.PagedResponse;
import soulland.project.service.MemorialsService;

@Transactional
@Service
public class MemorialsServiceImpl implements MemorialsService{
	
	@Autowired
	private MemorialsRepository memorialsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlacetimesRepository placetimesRepository;

	@Override
	public Memorials save(CreateMemorialForm memorials,String avatar) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	            .getPrincipal();
		Memorials memorials2 = new Memorials();
		memorials2.setAvatar(avatar);
		memorials2.setFullname(memorials.getFullName());
		memorials2.setCauseOfDeath(memorials.getCauseOfDeath());
		memorials2.setGender(memorials.getGender());
		memorials2.setUser(new User(userRepository.findByUsername(userDetails.getUsername()).get().getId()));
		memorials2.setPrivacyType(memorials.getPrivacyType());
		memorials2.setBiology(memorials.getBiography());
		Placetimes placetimes = placetimesRepository.save(new Placetimes());
		memorials2.setPlacetime(placetimes);
		return memorialsRepository.save(memorials2);
	}

	@Override
	public Optional<Memorials> findById(Long id) {
		
		return null;
	}

	@Override
	public void remove(Long id) {
		
	}

	@Override
	public PagedResponse<MemoHomeResponse> findAllByOrderByIdDescAndPrivacyType(int page,int size, int privacy) {
		Pageable pageable = PageRequest.of(page, size,Direction.DESC, "Id");
		Page<Memorials> posts =  memorialsRepository.findByPrivacyType((int)1,pageable);
		
		List<MemoHomeResponse> postResponses = new ArrayList<>(posts.getContent().size());
		
		for(Memorials memorial : posts) {
			postResponses.add(new MemoHomeResponse(memorial.getId(),memorial.getAvatar() ,
					memorial.getCauseOfDeath(), memorial.getFullname(), memorial.getGender(),memorial.getBiology()));
		}
		
		return new PagedResponse<>(postResponses, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
				posts.getTotalPages(), posts.isLast());
	}

}
