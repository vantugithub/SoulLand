package soulland.project.s3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import soulland.project.service.UserService;

@Service
public class AmazonClient {

	@Autowired
    private AmazonS3 s3Client;
    
    @Autowired
    private UserService userService;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${application.bucket.name}")
    private String bucketName;
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    
//    @SuppressWarnings("deprecation")
//	@PostConstruct
//    private void initializeAmazon() {
//       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
//       this.s3client = new AmazonS3Client(credentials);
//    	}
    
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "_" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    
    private void uploadFileTos3bucket(String fileName, File file) {
    	s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }
    
    public String uploadFile(MultipartFile multipartFile) {

        String fileUrl = "";
        try {
        	
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            if(!fileName.contains("jpg")) {
            	fileName+=".jpg";
            }
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        		String username = userDetails.getUsername();
            // Upload url of image to DB
            userService.updateImageProfile(fileName, username);
            
            fileUrl = endpointUrl + "/" + fileName;
            
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
    
    public String upLoadAvatarMemorials(MultipartFile multipartFile) {

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
    
    
    
    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName).withKeys(fileName);
        s3Client.deleteObjects(delObjReq);
        return "Successfully deleted";
    }
    
    
}
