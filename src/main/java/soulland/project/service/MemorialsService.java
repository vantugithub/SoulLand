package soulland.project.service;

import java.util.Optional;

import soulland.project.entity.Memorials;
import soulland.project.request.CommentFlowerRquest;
import soulland.project.request.CreateMemorialForm;
import soulland.project.response.CommentFlowerResponse;
import soulland.project.response.MemoHomeResponse;
import soulland.project.response.PagedResponse;

public interface MemorialsService {
	public Memorials save(CreateMemorialForm memorials,String avatar);
	
	public Optional<Memorials> findById(Long id);
	
	public void remove(Long id);
	
	public PagedResponse<MemoHomeResponse> findAllByOrderByIdDescAndPrivacyType(int page,int size,int privacy);
	
	public CommentFlowerResponse commentFlowers(CommentFlowerRquest commentFlowerRquest);
	
	public PagedResponse<MemoHomeResponse> findAllByOrderByIdDescAndUser(int page,int size);
	
	public void updateMemorialsById(CreateMemorialForm createMemorialForm, Long idMemo);
}
