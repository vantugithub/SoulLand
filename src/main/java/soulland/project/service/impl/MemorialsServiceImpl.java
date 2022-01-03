package soulland.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import soulland.project.entity.Contributions;
import soulland.project.entity.Flowers;
import soulland.project.entity.Memorials;
import soulland.project.entity.Placetimes;
import soulland.project.entity.User;
import soulland.project.repository.ContributionsRepository;
import soulland.project.repository.FlowersRepository;
import soulland.project.repository.MemorialsRepository;
import soulland.project.repository.PlacetimesRepository;
import soulland.project.repository.UserRepository;
import soulland.project.request.CommentFlowerRquest;
import soulland.project.request.CreateMemorialForm;
import soulland.project.response.CommentFlowerResponse;
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
	
	@Autowired
	private ContributionsRepository contributionsRepository;
	
	@Autowired
	private FlowersRepository flowersRepository;

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

	@Override
	public CommentFlowerResponse commentFlowers(CommentFlowerRquest commentFlowerRquest) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		Contributions contributions = new Contributions();
		contributions.setContributionType(1);
		contributions.setContributionMemorial(memorialsRepository.findById(commentFlowerRquest.getIdMemo()).get());
		Flowers flowers = flowersRepository.save(new Flowers(commentFlowerRquest.getTextComment()));
		contributions.setFlower(flowers);
		contributions.setUserContributions(userRepository.findByUsername(userDetails.getUsername()).get());
		contributionsRepository.save(contributions);
		CommentFlowerResponse commentFlowerResponse = new CommentFlowerResponse(userDetails.getUsername(), commentFlowerRquest.getTextComment());
		return commentFlowerResponse;
	}

	@Override
	public PagedResponse<MemoHomeResponse> findAllByOrderByIdDescAndUser(int page, int size) {
		Pageable pageable = PageRequest.of(page, size,Direction.DESC, "Id");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	            .getPrincipal();
		
		Page<Memorials> posts =  memorialsRepository.findMemorialsByUser(
				pageable,
				userRepository.findByUsername(userDetails.getUsername()).get(
				));
		
		List<MemoHomeResponse> postResponses = new ArrayList<>(posts.getContent().size());
		
		for(Memorials memorial : posts) {
			postResponses.add(new MemoHomeResponse(memorial.getId(),memorial.getAvatar() ,
					memorial.getCauseOfDeath(), memorial.getFullname(), memorial.getGender(),memorial.getBiology()));
		}
		Collections.reverse(postResponses);
		return new PagedResponse<>(postResponses, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
				posts.getTotalPages(), posts.isLast());
	}

	@Override
	public void updateMemorialsById(CreateMemorialForm createMemorialForm,Long idMemo) {
		Memorials memorials = memorialsRepository.findById(idMemo).get();
		memorials.setFullname(createMemorialForm.getFullName());
		memorials.setGender(createMemorialForm.getGender());
		memorials.setCauseOfDeath(createMemorialForm.getCauseOfDeath());
		memorials.setBiology(createMemorialForm.getBiography());
		memorials.setPrivacyType(createMemorialForm.getPrivacyType());
		memorialsRepository.save(memorials);
		
	}

}
