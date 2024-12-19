package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.Submission;
import system.model.repo.SubmissionRepository;

@Repository
public class SubmissionRepositoryImpl implements SubmissionRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Submission submission) {
        entityManager.persist(submission);
    }

    @Override
    @Transactional
    public void update(Submission submission) {
        entityManager.merge(submission);
    }

    @Override
    @Transactional
    public Submission findById(Integer id) {
        return entityManager.find(Submission.class, id);
    }

    @Override
    @Transactional
    public List<Submission> findAll() {
        return entityManager.createQuery("SELECT s FROM Submission s", Submission.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Submission submission = entityManager.find(Submission.class, id);
        if (submission != null) {
            entityManager.remove(submission);
        }
    }

    @Override
    @Transactional
    public List<Submission> findByAssignmentId(Integer assignmentId) {
        String jpql = "SELECT s FROM Submission s WHERE s.assignment.id = :assignmentId";
        TypedQuery<Submission> query = entityManager.createQuery(jpql, Submission.class);
        query.setParameter("assignmentId", assignmentId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Submission> findByStudentId(Integer studentId) {
        String jpql = "SELECT s FROM Submission s WHERE s.createStudent.id = :studentId";
        TypedQuery<Submission> query = entityManager.createQuery(jpql, Submission.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }
}