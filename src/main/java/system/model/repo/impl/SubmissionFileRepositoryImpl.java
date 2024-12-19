package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.SubmissionFile;
import system.model.repo.SubmissionFileRepository;

@Repository
public class SubmissionFileRepositoryImpl implements SubmissionFileRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(SubmissionFile submissionFile) {
        entityManager.persist(submissionFile);
    }

    @Override
    @Transactional
    public void update(SubmissionFile submissionFile) {
        entityManager.merge(submissionFile);
    }

    @Override
    @Transactional
    public SubmissionFile findById(Integer id) {
        return entityManager.find(SubmissionFile.class, id);
    }

    @Override
    @Transactional
    public List<SubmissionFile> findAll() {
        return entityManager.createQuery("SELECT sf FROM SubmissionFile sf", SubmissionFile.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        SubmissionFile submissionFile = entityManager.find(SubmissionFile.class, id);
        if (submissionFile != null) {
            entityManager.remove(submissionFile);
        }
    }

    @Override
    @Transactional
    public List<SubmissionFile> findBySubmissionId(Integer submissionId) {
        String jpql = "SELECT sf FROM SubmissionFile sf WHERE sf.submission.id = :submissionId";
        TypedQuery<SubmissionFile> query = entityManager.createQuery(jpql, SubmissionFile.class);
        query.setParameter("submissionId", submissionId);
        return query.getResultList();
    }
}