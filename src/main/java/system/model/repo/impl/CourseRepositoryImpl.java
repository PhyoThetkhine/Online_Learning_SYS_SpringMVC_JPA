package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import system.model.entity.Course;
import system.model.repo.CourseHasStudentRepository;
import system.model.repo.CourseHasTeacherRepository;
import system.model.repo.CourseRepository;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private CourseHasTeacherRepository courseHasTeacherRepository;
	
	@Autowired
	private CourseHasStudentRepository courseHasStudentRepository;


	@Override
	 @Transactional
	public void save(Course course) {
		entityManager.persist(course);
	}

	@Override
	 @Transactional
	public Course findById(Integer id) {
		return entityManager.find(Course.class, id);
	}

	@Override
	 @Transactional
	public List<Course> findAll() {
		return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
	}

	@Override
	 @Transactional
	public void update(Course course) {
		entityManager.merge(course);
		
	}

	@Override
	 @Transactional
	public void delete(Integer id) {
		Course course = entityManager.find(Course.class, id);
        if (course != null) {
            entityManager.remove(course);
        }
		
	}

	@Override
	public List<Course> findCoursesByTeacherId(Integer teacherId) {
		 return courseHasTeacherRepository.findCoursesByTeacherId(teacherId);
	}

	@Override
	@Transactional
	public List<Course> findCoursesByStudentId(Integer studentId) {
		 return courseHasStudentRepository.findCoursesByStudentId(studentId);
	}

}
