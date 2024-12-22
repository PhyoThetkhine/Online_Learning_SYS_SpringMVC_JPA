package system.service;

import java.util.List;

import system.model.DTO.SubmissionDTO;
import system.model.entity.Submission;

public interface SubmissionService {
	public void save(SubmissionDTO dto);
	public void update(SubmissionDTO dto);
	public SubmissionDTO findById(Integer id);
	public List<SubmissionDTO> findAll();
	public void delete(Integer id);
	public List<SubmissionDTO> findSubmissionsByAssignmentId(Integer assignmentId);
	List<SubmissionDTO> getSubmissionByAssignmentIdAndStudentId(Integer assignmentId,Integer studentId);
}
