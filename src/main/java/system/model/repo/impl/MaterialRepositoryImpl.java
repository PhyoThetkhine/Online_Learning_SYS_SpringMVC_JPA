package system.model.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import system.model.entity.Material;
import system.model.repo.MaterialRepository;

@Repository
public class MaterialRepositoryImpl implements MaterialRepository {
	@PersistenceContext
    private EntityManager entityManager;
	 @Override
	    @Transactional
	    public void save(Material material) {
	        entityManager.persist(material);
	    }

	    @Override
	    @Transactional
	    public void update(Material material) {
	        entityManager.merge(material);
	    }

	    @Override
	    @Transactional
	    public Material findById(Integer id) {
	        return entityManager.find(Material.class, id);
	    }

	    @Override
	    @Transactional
	    public List<Material> findAll() {
	        return entityManager.createQuery("SELECT m FROM Material m", Material.class).getResultList();
	    }

	    @Override
	    @Transactional
	    public void delete(Integer id) {
	        Material material = entityManager.find(Material.class, id);
	        if (material != null) {
	            entityManager.remove(material);
	        }
	    }

	    @Override
	    @Transactional
	    public List<Material> findMaterialsByCourseId(Integer courseId) {
	        String jpql = "SELECT m FROM Material m WHERE m.course.id = :courseId AND m.status = 'ACTIVE'";
	        TypedQuery<Material> query = entityManager.createQuery(jpql, Material.class);
	        query.setParameter("courseId", courseId);
	        return query.getResultList();
	    }
}
