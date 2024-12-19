package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.CourseHasStudentDTO;
import system.model.entity.CourseHasStudent;

@Component
public class CourseHasStudentMapper {
	
	public  CourseHasStudentDTO toDTO(CourseHasStudent entity) {
        if (entity == null) {
            return null;
        }

        CourseHasStudentDTO dto = new CourseHasStudentDTO();
        dto.setCourseId(entity.getCourse().getId()); 
        dto.setCourse(entity.getCourse());
        dto.setStudentId(entity.getStudent().getId()); 
        dto.setCreateAdminId(entity.getCreateAdmin().getId()); 
        dto.setCreateAdminName(entity.getCreateAdmin().getName()); 
        dto.setCreateAdmin(entity.getCreateAdmin());
        dto.setStatus(entity.getStatus().name()); 
        dto.setStudent(entity.getStudent());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());

        return dto;
    }
	
	public  CourseHasStudent toEntity(CourseHasStudentDTO dto) {
        if (dto == null) {
            return null;
        }

        CourseHasStudent entity = new CourseHasStudent();

        CourseHasStudent.CourseHasStudentPK id = new CourseHasStudent.CourseHasStudentPK();
        id.setCourseId(dto.getCourseId());
        id.setStudentId(dto.getStudentId());
        entity.setId(id);
        entity.setStudent(dto.getStudent());
        entity.setCreateAdmin(dto.getCreateAdmin());
        entity.setCourse(dto.getCourse());
        return entity;
    }
}