package system.service;

import java.util.List;

import system.model.DTO.SubmissionFileDTO;

public interface SubmissionFileService {
	public void saveSubmissionFile(SubmissionFileDTO dto);
	public List<SubmissionFileDTO> getFilesBySubmissionId(Integer submissionId);
	public  SubmissionFileDTO getFileById(Integer Id);
	public void saveNewFile(SubmissionFileDTO dto);

}
