package system.model.repo.impl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import system.model.entity.Role;
import system.model.repo.RoleRepository;
@Repository
public class RoleRepositoryImpl implements RoleRepository {
	@PersistenceContext
    private EntityManager entityManager;
	@Override
	 @Transactional
	public List<Role> findAll() {
		 TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
	        return query.getResultList();
	}
	@Override
	 @Transactional
	public Role findById(Integer id) {
		return entityManager.find(Role.class, id);
	}
	@Override
	   @Transactional
	public void save(Role role) {
		 entityManager.persist(role);
	}
	@Override
	   @Transactional
	public Role update(Role role) {
		return entityManager.merge(role);
	}
	@Override
	   @Transactional
	public void delete(Integer id) {
		 Role role = findById(id);
	        if (role != null) {
	            entityManager.remove(role);
	        }
	}
	@Override
	   @Transactional
	public Role findByRole(String role) {
		 TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class);
	        query.setParameter("role", role);
	        return query.getResultList().stream().findFirst().orElse(null);
	    }
}
