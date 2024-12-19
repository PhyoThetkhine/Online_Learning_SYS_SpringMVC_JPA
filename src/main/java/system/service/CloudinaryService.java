package system.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	public String imageUpload(MultipartFile photo);
	public String rawFileUpload(MultipartFile file) throws IOException;

}
