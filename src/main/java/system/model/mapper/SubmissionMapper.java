package system.model.mapper;


import org.springframework.stereotype.Component;

import system.model.DTO.SubmissionDTO;
import system.model.entity.Submission;

@Component
public class SubmissionMapper {
	    public  SubmissionDTO toDTO(Submission entity) {
	    	SubmissionDTO dto = new SubmissionDTO();
	        dto.setId(entity.getId());
	        dto.setAssignment(entity.getAssignment());
	        dto.setCreateDate(entity.getCreateDate());
	        dto.setUpdateDate(entity.getUpdateDate());
	        dto.setCreateStudent(entity.getCreateStudent());
	        return dto;
	    }
	    
	    public  Submission toEntity(SubmissionDTO dto) {
	    	Submission entity = new Submission();
	    	entity.setId(dto.getId());
	    	entity.setAssignment(dto.getAssignment());
	    	entity.setCreateDate(dto.getCreateDate());
	    	entity.setUpdateDate(dto.getUpdateDate());
	    	entity.setCreateStudent(dto.getCreateStudent());
	        return entity;
	    }

}
