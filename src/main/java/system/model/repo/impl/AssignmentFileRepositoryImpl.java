package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.AssignmentFile;
import system.model.repo.AssignmentFileRepository;

@Repository
public class AssignmentFileRepositoryImpl implements AssignmentFileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(AssignmentFile assignmentFile) {
        entityManager.persist(assignmentFile);
    }

    @Override
    @Transactional
    public void update(AssignmentFile assignmentFile) {
        entityManager.merge(assignmentFile);
    }

    @Override
    public AssignmentFile findById(Integer id) {
        return entityManager.find(AssignmentFile.class, id);
    }

    @Override
    public List<AssignmentFile> findAll() {
        return entityManager.createQuery("SELECT af FROM AssignmentFile af", AssignmentFile.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        AssignmentFile assignmentFile = entityManager.find(AssignmentFile.class, id);
        if (assignmentFile != null) {
            entityManager.remove(assignmentFile);
        }
    }

    @Override
    public List<AssignmentFile> findByAssignmentId(Integer assignmentId) {
        String jpql = "SELECT af FROM AssignmentFile af WHERE af.assignment.id = :assignmentId";
        TypedQuery<AssignmentFile> query = entityManager.createQuery(jpql, AssignmentFile.class);
        query.setParameter("assignmentId", assignmentId);
        return query.getResultList();
    }

	
}