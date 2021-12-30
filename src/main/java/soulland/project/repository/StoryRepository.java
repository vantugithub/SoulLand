package soulland.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soulland.project.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Long>{

}
