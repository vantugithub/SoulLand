package soulland.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulland.project.entity.Attachments;
import soulland.project.entity.Contributions;
import soulland.project.entity.Flowers;
import soulland.project.entity.Memorials;
import soulland.project.entity.Placetimes;
import soulland.project.entity.Story;
import soulland.project.entity.Tributes;
import soulland.project.entity.User;
import soulland.project.repository.MemorialsRepository;
import soulland.project.repository.PlacetimesRepository;
import soulland.project.repository.UserRepository;
import soulland.project.response.AttachmentsResponse;
import soulland.project.response.DetailMemorials;
import soulland.project.response.FlowerResponse;
import soulland.project.response.MemoResponse;
import soulland.project.response.StoryResponse;
import soulland.project.response.TributesResponse;
import soulland.project.response.UserContributionsResponse;
import soulland.project.service.ContributesService;

@Service
@Transactional
public class ContributesServiceImpl implements ContributesService{
	
	@Autowired
	private MemorialsRepository memorialsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlacetimesRepository placetimesRepository;

	@Override
	public MemoResponse getListContributesByMemorial(Long id) {
		MemoResponse memoResponse = new MemoResponse();
		Memorials memorials = memorialsRepository.findById(id).get();
		memoResponse.setId(memorials.getId());
		memoResponse.setUrlImage(memorials.getAvatar());
		memoResponse.setCauseOfDeath(memorials.getCauseOfDeath());
		memoResponse.setFullName(memorials.getFullname());
		memoResponse.setGender(memorials.getGender());
		memoResponse.setBiography(memorials.getBiology());
		
		Placetimes placetimes = memorials.getPlacetime();
		memoResponse.setLocation(placetimes.getLocation());
		memoResponse.setNickName(placetimes.getNickName());
		memoResponse.setBirthDate(placetimes.getBirthDate());
		memoResponse.setDeathDate(placetimes.getDeathDate());
		if(placetimes.getListImage() == null || placetimes.getListImage().isEmpty()) {
			memoResponse.setUrlListImage(null);
		}else {
			memoResponse.setUrlListImage(placetimes.getListImage().split(";"));
		}
		
		List<Contributions> listContributions = memorials.getContributions();
		List<UserContributionsResponse> list = new ArrayList<UserContributionsResponse>();
		
		for(Contributions contributions : listContributions) {
			User user = contributions.getUserContributions();
			UserContributionsResponse contributionsResponse = new UserContributionsResponse();
			contributionsResponse.setContributiontype(contributions.getContributionType());
			contributionsResponse.setFullName(user.getUsername());
			if(contributions.getContributionType()==1) {
				Flowers flowers = contributions.getFlower();
				contributionsResponse.setObject(new FlowerResponse(flowers.getId(),flowers.getMessage(),flowers.getLastUpdatedDate()));
			}else if(contributions.getContributionType()==2) {
				Story story = contributions.getStory();
				contributionsResponse.setObject(new StoryResponse(story.getId(),story.getContent(),story.getImage(),null));
			}else if(contributions.getContributionType()==3) {
				Tributes tributes = contributions.getTribute();
				contributionsResponse.setObject(new TributesResponse(tributes.getId(),tributes.getEulogy(),null));
			}else {
				Attachments attachments = contributions.getAttachment();
				contributionsResponse.setObject(new AttachmentsResponse(attachments.getId(),attachments.getTitle(),attachments.getPhoto()));
			}
			list.add(contributionsResponse);
		}
		memoResponse.setListContributions(list);
		
		
		return memoResponse;
	}

	@Override
	public DetailMemorials fetechById(Long id) {
		DetailMemorials memoResponse = new DetailMemorials();
		Memorials memorials = memorialsRepository.findById(id).get();
		memoResponse.setId(memorials.getId());
		memoResponse.setUrlImage(memorials.getAvatar());
		memoResponse.setCauseOfDeath(memorials.getCauseOfDeath());
		memoResponse.setFullName(memorials.getFullname());
		memoResponse.setGender(memorials.getGender());
		memoResponse.setBiography(memorials.getBiology());
		memoResponse.setPrivacyType(memorials.getPrivacyType());
		Placetimes placetimes = memorials.getPlacetime();
		memoResponse.setLocation(placetimes.getLocation());
		memoResponse.setNickName(placetimes.getNickName());
		memoResponse.setBirthDate(placetimes.getBirthDate());
		memoResponse.setDeathDate(placetimes.getDeathDate());
		if(placetimes.getListImage() == null || placetimes.getListImage().isEmpty()) {
			memoResponse.setUrlListImage(null);
		}else {
			memoResponse.setUrlListImage(placetimes.getListImage().split(";"));
		}
		
		return memoResponse;
	}

}
