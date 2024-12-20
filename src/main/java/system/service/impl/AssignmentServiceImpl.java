package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import system.model.DTO.AssignmentDTO;
import system.model.DTO.AssignmentFileDTO;
import system.model.entity.Assignment;
import system.model.entity.Course;
import system.model.entity.User;
import system.model.mapper.AssignmentMapper;
import system.model.repo.AssignmentRepository;
import system.model.repo.CourseRepository;
import system.model.repo.UserRepository;
import system.service.AssignmentFileService;
import system.service.AssignmentService;
import system.service.CloudinaryService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private AssignmentMapper assignmentMapper;
    
    @Autowired
    private AssignmentFileService assignmentFileService;
    @Autowired
	private CloudinaryService cloudinaryService;
    @Override
    public void save(AssignmentDTO dto) {
        User createTeacher = userRepository.findById(dto.getCreateTeacherID());
        Course course = courseRepository.findById(dto.getCourseID());

        // Handle nullable fields
        if (dto.getDueDate() == null) {
            dto.setDueDate(null);
        }
        if (dto.getPoint() == null || dto.getPoint().isEmpty()) {
            dto.setPoint(null);
        }

        // Map DTO to Entity
        dto.setCourse(course);
        dto.setCreateTeacher(createTeacher);
        Assignment assignment = assignmentMapper.toEntity(dto);

        // Save assignment
        assignmentRepository.save(assignment);

        // Handle file uploads
        if (dto.getFiles() != null && !dto.getFiles().isEmpty()) {
            for (MultipartFile file : dto.getFiles()) {
                try {
                    String fileUrl = cloudinaryService.rawFileUpload(file);
                    AssignmentFileDTO assignmentFileDTO = new AssignmentFileDTO();
                    assignmentFileDTO.setFileUrl(fileUrl);
                    assignmentFileDTO.setAssignmentID(assignment.getId());
                    assignmentFileService.saveAssignmentFile(assignmentFileDTO);
                } catch (Exception e) {
                    System.out.println("Failed to upload file: " + file.getOriginalFilename());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(AssignmentDTO dto) {
        Assignment assignment = assignmentMapper.toEntity(dto);
        assignment.setId(dto.getId());
        assignmentRepository.update(assignment);
    }

    @Override
    public AssignmentDTO findById(Integer id) {
        Assignment assignment = assignmentRepository.findById(id);
        if (assignment != null) {
            return assignmentMapper.toDTO(assignment);
        }
        return null;
    }

    @Override
    public List<AssignmentDTO> findAll() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
    	 Assignment assignment = assignmentRepository.findById(id);
    	 assignment.setStatus(Assignment.Status.DROP);
        assignmentRepository.update(assignment);
    }

    @Override
    public List<AssignmentDTO> findAssignmentsByCourseId(Integer courseId) {
        List<Assignment> assignments = assignmentRepository.findAssignmentsByCourseId(courseId);
        return assignments.stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}