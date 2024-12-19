package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.CourseHasStudent;
import system.model.entity.CourseHasStudent.CourseHasStudentPK;
import system.model.repo.CourseHasStudentRepository;
@Repository
public class CourseHasStudentRepositoryImpl implements CourseHasStudentRepository {

	  @PersistenceContext
	    private EntityManager entityManager;
	  
	@Override
	 @Transactional
	public List<CourseHasStudent> findActiveStudentByCourse(Integer courseId) {
		String jpql = "SELECT chs FROM CourseHasStudent chs WHERE chs.course.id = :courseId AND chs.status = 'ACTIVE'";
        TypedQuery<CourseHasStudent> query = entityManager.createQuery(jpql, CourseHasStudent.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
	}
	
	@Override
	 @Transactional
	public List<CourseHasStudent> findDropStudentByCourse(Integer courseId) {
		String jpql = "SELECT chs FROM CourseHasStudent chs WHERE chs.course.id = :courseId AND chs.status = 'DROP'";
		 TypedQuery<CourseHasStudent> query = entityManager.createQuery(jpql, CourseHasStudent.class);
	        query.setParameter("courseId", courseId);
	        return query.getResultList();
	}

	@Override
	 @Transactional
	public void save(CourseHasStudent courseHasStudent) {
		if (courseHasStudent.getId() == null) {
            entityManager.persist(courseHasStudent);
        } else {
            entityManager.merge(courseHasStudent);
        }
	}

	@Override
	 @Transactional
	public CourseHasStudent findById(CourseHasStudentPK id) {
		 return entityManager.find(CourseHasStudent.class, id);
	}

	@Override
	 @Transactional
	public void deleteById(CourseHasStudentPK id) {
		 CourseHasStudent entity = entityManager.find(CourseHasStudent.class, id);
	        if (entity != null) {
	        	 entity.setStatus(CourseHasStudent.Status.DROP); 
	             entityManager.merge(entity);
	        }
		
	}

	@Override
	 @Transactional
	public void reEnrollById(CourseHasStudentPK id) {
		CourseHasStudent entity = entityManager.find(CourseHasStudent.class, id);
        if (entity != null) {
        	 entity.setStatus(CourseHasStudent.Status.ACTIVE); 
             entityManager.merge(entity);
        }
	}

	

}
