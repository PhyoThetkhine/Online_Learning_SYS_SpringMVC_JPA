package system.model.repo;

import java.util.List;

import system.model.entity.Course;
import system.model.entity.CourseHasTeacher;
import system.model.entity.CourseHasTeacher.CourseHasTeacherPK;

public interface CourseHasTeacherRepository {
	 public List<CourseHasTeacher> findActiveTeacherByCourse(Integer courseId);
	 public List<CourseHasTeacher> findDropTeacherByCourse(Integer courseId);
	 public void save(CourseHasTeacher CourseHasTeacher);
	 public CourseHasTeacher findById(CourseHasTeacher.CourseHasTeacherPK id);
	 public void deleteById(CourseHasTeacher.CourseHasTeacherPK id);
	 public void reEnrollById(CourseHasTeacherPK id);
	 public List<Course> findCoursesByTeacherId(Integer teacherId);
	 public List<CourseHasTeacher> findAll();

}
