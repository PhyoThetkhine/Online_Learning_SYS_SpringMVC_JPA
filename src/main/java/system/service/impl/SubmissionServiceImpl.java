package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import system.model.DTO.SubmissionDTO;
import system.model.DTO.SubmissionFileDTO;
import system.model.entity.Assignment;
import system.model.entity.Submission;
import system.model.entity.User;
import system.model.mapper.SubmissionMapper;
import system.model.repo.AssignmentRepository;
import system.model.repo.SubmissionRepository;
import system.model.repo.UserRepository;
import system.service.CloudinaryService;
import system.service.SubmissionFileService;
import system.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private SubmissionFileService submissionFileService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public void save(SubmissionDTO dto) {
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentID());
        User createStudent = userRepository.findById(dto.getCreateStudentID());

        // Map DTO to Entity
        dto.setAssignment(assignment);
        dto.setCreateStudent(createStudent);
        Submission submission = submissionMapper.toEntity(dto);

        // Save submission
        submissionRepository.save(submission);

        // Handle file uploads
        if (dto.getFiles() != null && !dto.getFiles().isEmpty()) {
            for (MultipartFile file : dto.getFiles()) {
                try {
                    String fileUrl = cloudinaryService.rawFileUpload(file);
                    SubmissionFileDTO submissionFileDTO = new SubmissionFileDTO();
                    submissionFileDTO.setFileUrl(fileUrl);
                    submissionFileDTO.setSubmissionID(submission.getId());
                    submissionFileService.saveSubmissionFile(submissionFileDTO);
                } catch (Exception e) {
                    System.out.println("Failed to upload file: " + file.getOriginalFilename());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(SubmissionDTO dto) {
        Submission submission = submissionMapper.toEntity(dto);
        submission.setId(dto.getId());
        submissionRepository.update(submission);
    }

    @Override
    public SubmissionDTO findById(Integer id) {
        Submission submission = submissionRepository.findById(id);
        if (submission != null) {
            return submissionMapper.toDTO(submission);
        }
        return null;
    }

    @Override
    public List<SubmissionDTO> findAll() {
        List<Submission> submissions = submissionRepository.findAll();
        return submissions.stream()
                .map(submissionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        submissionRepository.delete(id);
    }

    @Override
    public List<SubmissionDTO> findSubmissionsByAssignmentId(Integer assignmentId) {
        List<Submission> submissions = submissionRepository.findByAssignmentId(assignmentId);
        return submissions.stream()
                .map(submissionMapper::toDTO)
                .collect(Collectors.toList());
    }
}