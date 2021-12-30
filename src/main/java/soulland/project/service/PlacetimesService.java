package soulland.project.service;

import soulland.project.entity.Placetimes;
import soulland.project.request.PlacetimeRequest;

public interface PlacetimesService {
	public Placetimes findById(Long id);
	public void save(PlacetimeRequest placetimeRequest);
}
