package system.service;

import java.util.List;

import system.model.DTO.CourseHasStudentDTO;
import system.model.entity.CourseHasStudent;

public interface CourseHasStudentService {
	 public List<CourseHasStudentDTO> findActiveStudentByCourse(Integer courseId);
	 public List<CourseHasStudentDTO> findDropStudentByCourse(Integer courseId);
	 public CourseHasStudentDTO getCourseHasStudentById(CourseHasStudent.CourseHasStudentPK id) ;
	 public void saveCourseHasStudent(CourseHasStudentDTO dto) ;
	 public void deleteCourseHasStudent(CourseHasStudent.CourseHasStudentPK id);
	 public void reEnrollCourseHasStudent(CourseHasStudent.CourseHasStudentPK id);
	 public List<CourseHasStudentDTO> getAll() ;
	 
}
