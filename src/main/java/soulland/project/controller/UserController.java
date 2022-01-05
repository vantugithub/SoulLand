package soulland.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soulland.project.entity.Memorials;
import soulland.project.request.CommentFlowerRquest;
import soulland.project.request.CreateMemorialForm;
import soulland.project.request.PlacetimeRequest;
import soulland.project.response.CommentFlowerResponse;
import soulland.project.response.DetailMemorials;
import soulland.project.response.MemoHomeResponse;
import soulland.project.response.PagedResponse;
import soulland.project.s3.service.AmazonClient;
import soulland.project.service.ContributesService;
import soulland.project.service.MemorialsService;
import soulland.project.service.PlacetimesService;
import soulland.project.utils.AppConstants;


@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "*")
public class UserController {
	
	private AmazonClient amazonClient;
	
	
	@Autowired
	private PlacetimesService placetimesService;
	
	@Autowired
	private MemorialsService memorialsService;
	
	@Autowired
	private ContributesService contributesService;
	
    @Autowired
    UserController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }
    
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/creatememorial", method = RequestMethod.POST)
	public ResponseEntity createMemorials(@ModelAttribute CreateMemorialForm createMemorialForm) {
		MultipartFile[] fileDatas = createMemorialForm.getFiles();
			@SuppressWarnings("unused")
			Memorials memorials = memorialsService.save(createMemorialForm,
					this.amazonClient.uploadFile(fileDatas[0]));
	    return ResponseEntity.ok().body("User created successfully!");
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/saveplacetime")
	public ResponseEntity savePlacetimes(@Validated @ModelAttribute PlacetimeRequest placetimeRequest) {
		placetimesService.save(placetimeRequest);
		return ResponseEntity.ok().body("User updated successfully!");
	}
	
	@PostMapping("/commentflower")
	public ResponseEntity<CommentFlowerResponse> commentFlower(@ModelAttribute CommentFlowerRquest commentFlowerRquest)  {
		return ResponseEntity.ok(memorialsService.commentFlowers(commentFlowerRquest));
	}
	
	@GetMapping("/posts")
	public PagedResponse<MemoHomeResponse> fetechPosts(
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size
			) {
		return memorialsService.findAllByOrderByIdDescAndUser(page, size);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<DetailMemorials> fetechById(@RequestParam(name = "id", required = true) long id) {
		return ResponseEntity.ok(contributesService.fetechById(id));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/posts/updatememo/{idMemo}")
	public ResponseEntity updateMemoById(@ModelAttribute CreateMemorialForm createMemorialForm,
			@RequestParam(name = "id", required =  true) Long id
			) {
		memorialsService.updateMemorialsById(createMemorialForm, id);
		return ResponseEntity.ok().body("User updated successfully!");
	}

	
}