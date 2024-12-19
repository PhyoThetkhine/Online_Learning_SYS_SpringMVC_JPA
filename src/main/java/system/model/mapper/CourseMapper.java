package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.CourseDTO;
import system.model.entity.Course;
@Component
public class CourseMapper {
	public CourseDTO toDTO(Course course) {
		CourseDTO dto = new CourseDTO();
		dto.setId(course.getId());
		dto.setTitle(course.getTitle());
		dto.setDescription(course.getDescription());
		dto.setCreateDate(course.getCreateDate());
		dto.setUpdateDate(course.getUpdateDate());
		dto.setPhotoUrl(course.getPhotoUrl());
		dto.setStatus(course.getStatus().name());
		dto.setCreateAdminId(course.getCreateAdmin().getId());
		dto.setCreateAdminName(course.getCreateAdmin().getName());
		dto.setCreateAdmin(course.getCreateAdmin());
		return dto;
		
	}
	
	public Course toEntity(CourseDTO dto) {
	    Course course = new Course();
	    course.setId(dto.getId());
	    course.setTitle(dto.getTitle());
	    course.setDescription(dto.getDescription());
	    course.setPhotoUrl(dto.getPhotoUrl());
	    course.setCreateAdmin(dto.getCreateAdmin()); 
	    return course;
	}

}
