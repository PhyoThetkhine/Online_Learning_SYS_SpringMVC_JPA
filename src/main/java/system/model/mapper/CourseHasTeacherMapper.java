package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.CourseHasTeacherDTO;
import system.model.entity.CourseHasTeacher;

@Component
public class CourseHasTeacherMapper {
	public  CourseHasTeacherDTO toDTO(CourseHasTeacher entity) {
        if (entity == null) {
            return null;
        }

        CourseHasTeacherDTO dto = new CourseHasTeacherDTO();
        dto.setCourseId(entity.getCourse().getId()); 
        dto.setCourse(entity.getCourse());
        dto.setTeacherId(entity.getTeacher().getId()); 
        dto.setCreateAdminId(entity.getCreateAdmin().getId()); 
        dto.setCreateAdminName(entity.getCreateAdmin().getName());
        dto.setCreateAdmin(entity.getCreateAdmin());
        dto.setStatus(entity.getStatus().name()); 
        dto.setTeacher(entity.getTeacher());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());

        return dto;
    }
	
	public  CourseHasTeacher toEntity(CourseHasTeacherDTO dto) {
        if (dto == null) {
            return null;
        }

        CourseHasTeacher entity = new CourseHasTeacher();
        CourseHasTeacher.CourseHasTeacherPK id = new CourseHasTeacher.CourseHasTeacherPK();
        id.setCourseId(dto.getCourseId());
        id.setTeacherId(dto.getTeacherId());
        entity.setId(id);
        entity.setTeacher(dto.getTeacher());
        entity.setCreateAdmin(dto.getCreateAdmin());
        entity.setCourse(dto.getCourse());

        
        return entity;
    }
}