package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.Assignment;
import system.model.repo.AssignmentRepository;

@Repository
public class AssignmentRepositoryImpl implements AssignmentRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Assignment assignment) {
        entityManager.persist(assignment);
    }

    @Override
    @Transactional
    public void update(Assignment assignment) {
        entityManager.merge(assignment);
    }

    @Override
    @Transactional
    public Assignment findById(Integer id) {
        return entityManager.find(Assignment.class, id);
    }

    @Override
    @Transactional
    public List<Assignment> findAll() {
        return entityManager.createQuery("SELECT a FROM Assignment a", Assignment.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Assignment assignment = entityManager.find(Assignment.class, id);
        if (assignment != null) {
            entityManager.remove(assignment);
        }
    }

    @Override
    @Transactional
    public List<Assignment> findAssignmentsByCourseId(Integer courseId) {
        String jpql = "SELECT a FROM Assignment a WHERE a.course.id = :courseId AND a.status = 'ACTIVE'";
        TypedQuery<Assignment> query = entityManager.createQuery(jpql, Assignment.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

}