package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import system.model.DTO.SubmissionFileDTO;
import system.model.entity.Assignment;
import system.model.entity.AssignmentFile;
import system.model.entity.Submission;
import system.model.entity.SubmissionFile;
import system.model.mapper.SubmissionFileMapper;
import system.model.repo.SubmissionFileRepository;
import system.model.repo.SubmissionRepository;
import system.service.CloudinaryService;
import system.service.SubmissionFileService;
@Service
public class SubmissionFileServiceImpl implements SubmissionFileService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionFileRepository submissionFileRepository;

    @Autowired
    private SubmissionFileMapper submissionFileMapper;
    @Autowired
	private CloudinaryService cloudinaryService;

    @Override
    public void saveSubmissionFile(SubmissionFileDTO dto) {
        Submission submission = submissionRepository.findById(dto.getSubmissionID());
        dto.setSubmission(submission);
        SubmissionFile submissionFile = submissionFileMapper.toEntity(dto);
        submissionFileRepository.save(submissionFile);
    }

    @Override
    public List<SubmissionFileDTO> getFilesBySubmissionId(Integer submissionId) {
        List<SubmissionFile> submissionFiles = submissionFileRepository.findBySubmissionId(submissionId);
        return submissionFiles.stream()
                              .map(submissionFileMapper::toDTO)
                              .collect(Collectors.toList());
    }

	@Override
	public SubmissionFileDTO getFileById(Integer Id) {
		SubmissionFile submissionFile = submissionFileRepository.findById(Id);
		return submissionFileMapper.toDTO(submissionFile);
	}

	@Override
	public void saveNewFile(SubmissionFileDTO dto) {
		  Submission submission = submissionRepository.findById(dto.getSubmissionID());
		
		  List<MultipartFile> files = dto.getFiles();
			 if (files != null && !files.isEmpty()) {
		            for (MultipartFile file : files) {
		                try {
		                    String fileUrl = cloudinaryService.rawFileUpload(file);
		                    dto.setFileUrl(fileUrl);
		                    dto.setSubmission(submission);
		                    SubmissionFile submissionFile = submissionFileMapper.toEntity(dto);
		                    submissionFileRepository.save(submissionFile);
		                } catch (Exception e) {
		                    System.out.println("Failed to upload file: " + file.getOriginalFilename());
		                    e.printStackTrace();
		                }
		            }
		        }
		
	}
}