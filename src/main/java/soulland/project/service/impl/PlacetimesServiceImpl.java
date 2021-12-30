package soulland.project.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import soulland.project.entity.Memorials;
import soulland.project.entity.Placetimes;
import soulland.project.repository.MemorialsRepository;
import soulland.project.repository.PlacetimesRepository;
import soulland.project.request.PlacetimeRequest;
import soulland.project.s3.service.AmazonClient;
import soulland.project.service.PlacetimesService;

@Transactional
@Service
public class PlacetimesServiceImpl implements PlacetimesService {
	
	private AmazonClient amazonClient;
	
	@Autowired
	PlacetimesServiceImpl(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
	
	@Autowired
	private PlacetimesRepository placetimesRepository;
	
	@Autowired
	private MemorialsRepository memorialsRepository;

	@Override
	public Placetimes findById(Long id) {
		return null;
	}

	@Override
	public void save(PlacetimeRequest placetimeRequest) {
		
		Memorials memorials = memorialsRepository.findById(placetimeRequest.getIdMemorials()).get();
		
		String listImage = memorials.getPlacetime().getListImage();
		
		if(listImage == null || listImage.isEmpty()) {
			listImage = "";
		}
		for(MultipartFile file : placetimeRequest.getFiles()) 
		{
			listImage+=(this.amazonClient.upLoadAvatarMemorials(file)+";");
		}
		
		Placetimes placetimes = new Placetimes();
		placetimes.setId(memorials.getPlacetime().getId());
		placetimes.setBirthDate(placetimeRequest.getBirthDate());
		placetimes.setDeathDate(placetimeRequest.getDeathDate());
		placetimes.setListImage(listImage);
		placetimes.setLocation(placetimeRequest.getLocation());
		placetimes.setNickName(placetimeRequest.getNickName());
		placetimesRepository.save(placetimes);
		
	}
	
}
