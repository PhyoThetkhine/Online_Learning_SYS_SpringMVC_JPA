package system.service;

import java.util.List;

import system.model.DTO.AssignmentFileDTO;

public interface AssignmentFileService {
	public void saveAssignmentFile(AssignmentFileDTO dto);
	public List<AssignmentFileDTO> getFilesByAssignmentId(Integer assignmentId);

}
