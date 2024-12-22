package system.service;

import java.util.List;

import system.model.DTO.AssignmentFileDTO;
import system.model.DTO.MaterialFileDTO;

public interface AssignmentFileService {
	public void saveAssignmentFile(AssignmentFileDTO dto);
	public List<AssignmentFileDTO> getFilesByAssignmentId(Integer assignmentId);
	public void addnewFile(AssignmentFileDTO dto) ;
	

}
