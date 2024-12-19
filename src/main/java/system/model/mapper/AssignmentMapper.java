package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.AssignmentDTO;
import system.model.entity.Assignment;

@Component
public class AssignmentMapper {
	
	public AssignmentDTO toDTO(Assignment entity) {
		AssignmentDTO dto = new AssignmentDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setCourse(entity.getCourse());
		dto.setDueDate(entity.getDueDate());
		dto.setPoint(entity.getPoint());
		dto.setStatus(entity.getStatus().name());
		dto.setCreateDate(entity.getCreateDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setCreateTeacher(entity.getCreateTeacher());
		dto.setCourseID(entity.getCourse().getId());
		dto.setCreateTeacherID(entity.getCreateTeacher().getId());
		dto.setCreateTeacherName(entity.getCreateTeacher().getName());
		return dto;
	}
	
	public Assignment toEntity(AssignmentDTO dto) {
		Assignment entity = new Assignment();
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setCourse(dto.getCourse());
		entity.setDueDate(dto.getDueDate());
		entity.setPoint(dto.getPoint());
		entity.setCreateDate(dto.getCreateDate());
		entity.setUpdateDate(dto.getUpdateDate());
		entity.setCreateTeacher(dto.getCreateTeacher());
		return entity;
	}

}
