package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.SubmissionFileDTO;
import system.model.entity.SubmissionFile;

@Component
public class SubmissionFileMapper {
	  public  SubmissionFile toEntity(SubmissionFileDTO dto) {
		  SubmissionFile entity = new SubmissionFile();
		  entity.setFileUrl(dto.getFileUrl());
		  entity.setSubmission(dto.getSubmission());
	        return entity;
	    }

	    public  SubmissionFileDTO toDTO(SubmissionFile entity) {
	    	SubmissionFileDTO dto = new SubmissionFileDTO();
	        dto.setId(entity.getId());
	        dto.setFileUrl(entity.getFileUrl());
	        dto.setSubmissionID(entity.getSubmission().getId());
	        dto.setSubmission(entity.getSubmission());
	        return dto;
	    }

}
