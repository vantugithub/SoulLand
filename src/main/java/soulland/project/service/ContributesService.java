package soulland.project.service;

import org.springframework.stereotype.Repository;

import soulland.project.response.MemoResponse;

@Repository
public interface ContributesService {
	public MemoResponse getListContributesByMemorial(Long id);
}
