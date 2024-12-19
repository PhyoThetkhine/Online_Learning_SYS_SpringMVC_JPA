package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.MaterialFile;
import system.model.repo.MaterialFileRepository;

@Repository
public class MaterialFileRepositoryImpl implements MaterialFileRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(MaterialFile materialFile) {
        entityManager.persist(materialFile);
    }

    @Override
    @Transactional
    public void update(MaterialFile materialFile) {
        entityManager.merge(materialFile);
    }

    @Override
    public MaterialFile findById(Integer id) {
        return entityManager.find(MaterialFile.class, id);
    }

    @Override
    public List<MaterialFile> findAll() {
        return entityManager.createQuery("SELECT mf FROM MaterialFile mf", MaterialFile.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        MaterialFile materialFile = entityManager.find(MaterialFile.class, id);
        if (materialFile != null) {
            entityManager.remove(materialFile);
        }
    }

    @Override
    public List<MaterialFile> findByMaterialId(Integer materialId) {
        String jpql = "SELECT mf FROM MaterialFile mf WHERE mf.material.id = :materialId";
        TypedQuery<MaterialFile> query = entityManager.createQuery(jpql, MaterialFile.class);
        query.setParameter("materialId", materialId);
        return query.getResultList();
    }

   

}


