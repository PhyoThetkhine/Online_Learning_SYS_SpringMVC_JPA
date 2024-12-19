package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.AssignmentFileDTO;
import system.model.entity.AssignmentFile;

@Component
public class AssignmentFileMapper {
	
	public AssignmentFileDTO toDTO(AssignmentFile entity) {
		AssignmentFileDTO dto = new AssignmentFileDTO();
		dto.setAssignmentID(entity.getAssignment().getId());
		dto.setId(entity.getId());
		dto.setFileUrl(entity.getFileUrl());
		dto.setAssignment(entity.getAssignment());
		return dto;
	}
	
	public AssignmentFile toEntity(AssignmentFileDTO dto) {
		AssignmentFile entity = new AssignmentFile();
		entity.setFileUrl(dto.getFileUrl());
		entity.setAssignment(dto.getAssignment());
		return entity;
	}

}
