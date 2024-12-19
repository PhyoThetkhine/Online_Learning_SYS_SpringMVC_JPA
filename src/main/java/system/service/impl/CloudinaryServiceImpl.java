package system.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import system.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
	 @Autowired
	    private  Cloudinary cloudinary;

	@Override
	public String imageUpload(MultipartFile photo) {
		Map uploadResult = null;
		try {
			uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.asMap("resource_type", "image"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        String imageUrl = (String) uploadResult.get("secure_url");
		return imageUrl;
	}

	@Override
	public String rawFileUpload(MultipartFile file) {
	    Map uploadResult = null;
	    try {
	        String fileName = file.getOriginalFilename();
	        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
	        uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
	                "resource_type", "raw",
	                "format", fileExtension
	        ));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    String fileUrl = (String) uploadResult.get("secure_url");
	    return fileUrl;
	}
}
