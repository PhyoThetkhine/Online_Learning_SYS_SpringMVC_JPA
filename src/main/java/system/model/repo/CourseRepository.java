package system.model.repo;

import java.util.List;

import system.model.entity.Course;

public interface CourseRepository {
	 public void save(Course course);
	 public Course findById(Integer id);
	 public List<Course> findAll();
	 public void update(Course course);
	 public void delete(Integer id);
	 List<Course> findCoursesByTeacherId(Integer teacherId);
	  List<Course> findCoursesByStudentId(Integer studentId); // Add this method
	
	 
}
