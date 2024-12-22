package system.model.repo;

import java.util.List;

import system.model.entity.Course;
import system.model.entity.CourseHasStudent;

public interface CourseHasStudentRepository {
	 public List<CourseHasStudent> findActiveStudentByCourse(Integer courseId);
	 public List<CourseHasStudent> findDropStudentByCourse(Integer courseId);
	 public void save(CourseHasStudent courseHasStudent);
	 public CourseHasStudent findById(CourseHasStudent.CourseHasStudentPK id);
	 public void deleteById(CourseHasStudent.CourseHasStudentPK id);
	 public void reEnrollById(CourseHasStudent.CourseHasStudentPK id);
	 public List<Course> findCoursesByStudentId(Integer studentID);

}
