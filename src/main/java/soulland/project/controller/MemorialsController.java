package soulland.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import soulland.project.response.MemoHomeResponse;
import soulland.project.response.MemoResponse;
import soulland.project.response.PagedResponse;
import soulland.project.service.ContributesService;
import soulland.project.service.MemorialsService;
import soulland.project.utils.AppConstants;

@RestController
@RequestMapping("/api/memos")
@CrossOrigin(origins = "*")
public class MemorialsController {
	
	@Autowired
	private MemorialsService memorialsService;
	
	@Autowired
	private ContributesService contributesService;
	
	@GetMapping
	public PagedResponse<MemoHomeResponse> getAllMemo(
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
		return memorialsService.findAllByOrderByIdDescAndPrivacyType(page, size,1);
	}
	
	@GetMapping("/api/memos/{id}")
	public ResponseEntity<MemoResponse> findMemoById(
			@RequestParam(name = "id", required = true) long id
			)
	{
		return ResponseEntity.ok(contributesService.getListContributesByMemorial(id));
	}
}
