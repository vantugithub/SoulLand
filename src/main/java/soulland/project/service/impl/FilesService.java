package soulland.project.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {
	private final Path root = Paths.get("uploads");
	
	@SuppressWarnings("unused")
	private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "_" + multiPart.getOriginalFilename().replace(" ", "_");
    }

	  public void init() {
	    try {
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  public String save(MultipartFile file) {
	    try {
	    	String fileName = generateFileName(file);
	    	String location = new File("src\\main\\resources\\static\\uploads").getAbsolutePath()+"\\"+fileName;
	    	FileOutputStream fileOutputStream = new FileOutputStream(location);
	    	fileOutputStream.write(file.getBytes());
	    	fileOutputStream.close();
	    	return "http://localhost:8080/uploads/"+fileName;
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }

	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
}

