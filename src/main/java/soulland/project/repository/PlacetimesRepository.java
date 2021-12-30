package soulland.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soulland.project.entity.Placetimes;

@Repository
public interface PlacetimesRepository extends JpaRepository<Placetimes, Long>{

}
