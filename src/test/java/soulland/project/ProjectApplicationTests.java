package soulland.project;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import soulland.project.entity.Contributions;
import soulland.project.entity.Memorials;
import soulland.project.entity.Story;
import soulland.project.repository.ContributionsRepository;
import soulland.project.repository.MemorialsRepository;
import soulland.project.repository.StoryRepository;



@SpringBootTest
class ProjectApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MemorialsRepository memorialsRepository;
	
	@Autowired
	ContributionsRepository contributionsRepository;
	
	@Autowired
	StoryRepository storyRepository;
	
	@Test
	@Transactional
	public void testUpdateUserRoles() {
		Optional<Memorials> memorials = memorialsRepository.findById(9L);
		List<Contributions> listContributes =  memorials.get().getContributions();
		listContributes.forEach((item)->logger.info(item.getContributionType() + ""));
	}
	
	@Test
	@Transactional
	public void testComments() {
		Story story = storyRepository.save(new Story("url","i miss you"));
		logger.info("ok =. {}", story.toString());
	}
}
