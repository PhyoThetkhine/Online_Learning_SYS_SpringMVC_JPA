package system.service;

import java.util.List;

import system.model.DTO.CourseHasTeacherDTO;
import system.model.entity.CourseHasTeacher;

public interface CourseHasTeacherService {
	 public List<CourseHasTeacherDTO> findAssignedTeacherByCourse(Integer courseId);
	 public List<CourseHasTeacherDTO> findDropTeacherByCourse(Integer courseId);
	 public CourseHasTeacherDTO getCourseHasTeacherById(CourseHasTeacher.CourseHasTeacherPK id) ;
	 public void saveCourseHasTeacher(CourseHasTeacherDTO dto) ;
	 public void deleteCourseHasTeacher(CourseHasTeacher.CourseHasTeacherPK id);
	 public void reEnrollCourseHasTeacher(CourseHasTeacher.CourseHasTeacherPK id);
	 
	 
}
