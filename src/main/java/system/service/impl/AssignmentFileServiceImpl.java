package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import system.model.DTO.AssignmentFileDTO;
import system.model.entity.Assignment;
import system.model.entity.AssignmentFile;
import system.model.entity.MaterialFile;
import system.model.mapper.AssignmentFileMapper;
import system.model.repo.AssignmentFileRepository;
import system.model.repo.AssignmentRepository;
import system.service.AssignmentFileService;
import system.service.CloudinaryService;

@Service
public class AssignmentFileServiceImpl implements AssignmentFileService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentFileRepository assignmentFileRepository;

    @Autowired
    private AssignmentFileMapper assignmentFileMapper;
    @Autowired
	private CloudinaryService cloudinaryService;

    @Override
    public void saveAssignmentFile(AssignmentFileDTO dto) {
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentID());
        dto.setAssignment(assignment);
        AssignmentFile assignmentFile = assignmentFileMapper.toEntity(dto);
        assignmentFileRepository.save(assignmentFile);
    }

    @Override
    public List<AssignmentFileDTO> getFilesByAssignmentId(Integer assignmentId) {
        List<AssignmentFile> assignmentFiles = assignmentFileRepository.findByAssignmentId(assignmentId);
        return assignmentFiles.stream()
                              .map(assignmentFileMapper::toDTO)
                              .collect(Collectors.toList());
    }

	@Override
	public void addnewFile(AssignmentFileDTO dto) {
		  Assignment assignment = assignmentRepository.findById(dto.getAssignmentID());
		
		  List<MultipartFile> files = dto.getFiles();
			 if (files != null && !files.isEmpty()) {
		            for (MultipartFile file : files) {
		                try {
		                    String fileUrl = cloudinaryService.rawFileUpload(file);
		                    dto.setFileUrl(fileUrl);
		                    dto.setAssignment(assignment);
		                    AssignmentFile assignmentFiles = assignmentFileMapper.toEntity(dto);
		                    assignmentFileRepository.save(assignmentFiles);
		                } catch (Exception e) {
		                    System.out.println("Failed to upload file: " + file.getOriginalFilename());
		                    e.printStackTrace();
		                }
		            }
		        }
		
	}

	
}