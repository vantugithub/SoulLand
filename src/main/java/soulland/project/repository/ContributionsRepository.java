package soulland.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soulland.project.entity.Contributions;

@Repository
public interface ContributionsRepository extends JpaRepository<Contributions, Long>{
	public Contributions findByContributionMemorial(long id);
}
