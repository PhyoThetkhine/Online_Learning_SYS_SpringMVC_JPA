package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.AssignmentFileDTO;
import system.model.entity.Assignment;
import system.model.entity.AssignmentFile;
import system.model.mapper.AssignmentFileMapper;
import system.model.repo.AssignmentFileRepository;
import system.model.repo.AssignmentRepository;
import system.service.AssignmentFileService;

@Service
public class AssignmentFileServiceImpl implements AssignmentFileService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentFileRepository assignmentFileRepository;

    @Autowired
    private AssignmentFileMapper assignmentFileMapper;

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

	
}