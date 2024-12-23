package system.model.repo.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.Course;
import system.model.entity.CourseHasTeacher;
import system.model.entity.CourseHasTeacher.CourseHasTeacherPK;
import system.model.repo.CourseHasTeacherRepository;

@Repository
public class CourseHasTeacherRepositoryImpl implements CourseHasTeacherRepository {

	 @PersistenceContext
	    private EntityManager entityManager;
	@Override
	 @Transactional
	public List<CourseHasTeacher> findActiveTeacherByCourse(Integer courseId) {
		String jpql = "SELECT cht FROM CourseHasTeacher cht WHERE cht.course.id = :courseId AND cht.status = 'ACTIVE'";
		TypedQuery<CourseHasTeacher> query = entityManager.createQuery(jpql, CourseHasTeacher.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
	}
	@Override
	 @Transactional
	public List<CourseHasTeacher> findDropTeacherByCourse(Integer courseId) {
		String jpql = "SELECT cht FROM CourseHasTeacher cht WHERE cht.course.id = :courseId AND cht.status = 'DROP'";
		TypedQuery<CourseHasTeacher> query = entityManager.createQuery(jpql, CourseHasTeacher.class);
       query.setParameter("courseId", courseId);
       return query.getResultList();
	}

	@Override
	 @Transactional
	public void save(CourseHasTeacher CourseHasTeacher) {
		if (CourseHasTeacher.getId() == null) {
            entityManager.persist(CourseHasTeacher);
        } else {
            entityManager.merge(CourseHasTeacher);
        }
		
	}
	@Override
	@Transactional
	public List<CourseHasTeacher> findAll() {
	    String jpql = "SELECT cht FROM CourseHasTeacher cht";
	    TypedQuery<CourseHasTeacher> query = entityManager.createQuery(jpql, CourseHasTeacher.class);
	    return query.getResultList();
	}

	@Override
	 @Transactional
	public CourseHasTeacher findById(CourseHasTeacherPK id) {
		 return entityManager.find(CourseHasTeacher.class, id);
	}

	@Override
	 @Transactional
	public void deleteById(CourseHasTeacherPK id) {
		CourseHasTeacher entity = entityManager.find(CourseHasTeacher.class, id);
		if (entity != null) {
	        entity.setStatus(CourseHasTeacher.Status.DROP);
	        entity.setUpdateDate(new Date(System.currentTimeMillis()));// Set the status to INACTIVE for soft delete
	        entityManager.merge(entity); // Persist the updated status
	   
		}
		
	}
	@Override
	 @Transactional
	public void reEnrollById(CourseHasTeacherPK id) {
		CourseHasTeacher entity = entityManager.find(CourseHasTeacher.class, id);
		if (entity != null) {
	        entity.setStatus(CourseHasTeacher.Status.ACTIVE);
	        entity.setUpdateDate(new Date(System.currentTimeMillis()));
	        entityManager.merge(entity);
	   
		}
		
	}
	@Override
	@Transactional
	public List<Course> findCoursesByTeacherId(Integer teacherId) {
	    String jpql = "SELECT cht.course FROM CourseHasTeacher cht WHERE cht.teacher.id = :teacherId";
	    TypedQuery<Course> query = entityManager.createQuery(jpql, Course.class);
	    query.setParameter("teacherId", teacherId);
	    return query.getResultList();
	}

}
