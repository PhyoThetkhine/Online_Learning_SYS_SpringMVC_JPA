package system.service;

import java.util.List;

import system.model.DTO.CourseDTO;
import system.model.entity.Course;

public interface CourseService {
	 public void save(CourseDTO course);
	 public CourseDTO findById(Integer id);
	 public List<CourseDTO> findAll();
	 public void update(CourseDTO course);
	 public void delete(CourseDTO id);
	 public List<CourseDTO> findCoursesByTeacherId(Integer teacherId);
	 List<CourseDTO> findCoursesByStudentId(Integer studentId);

}
