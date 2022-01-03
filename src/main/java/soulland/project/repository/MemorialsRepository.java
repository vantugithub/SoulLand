package soulland.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soulland.project.entity.Memorials;
import soulland.project.entity.User;

@Repository
public interface MemorialsRepository extends JpaRepository<Memorials, Long>{
	
	public Optional<Memorials> findById(Long id);
	Page<Memorials> findByPrivacyType(int privacyType,Pageable pageable);
	public Page<Memorials> findMemorialsByUser(Pageable pageable,User user);
}
