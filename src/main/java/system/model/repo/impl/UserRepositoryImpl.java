package system.model.repo.impl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import system.model.entity.User;
import system.model.repo.UserRepository;
@Repository
public class UserRepositoryImpl implements UserRepository {
	@PersistenceContext
    private EntityManager entityManager;
	@Override
	@Transactional
	public void save(User user) {
		 entityManager.persist(user);
	}
	@Override
	 @Transactional
	public void update(User user) {
		entityManager.merge(user);
	}
	@Override
	 @Transactional
	public User findById(Integer id) {
		 return entityManager.find(User.class, id);
	}
	@Override
	@Transactional
	 public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
	@Override
	 @Transactional
	 public void delete(Integer id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
	@Override
	@Transactional
    public User findUserByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
        }
	@Override
    public List<User> findUserWhereRoleIsStudent() {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.role.role = :role", User.class);
        query.setParameter("role", "Student");
        return query.getResultList();
    }
	@Override
    public List<User> findUserWhereRoleIsTeacher() {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.role.role = :role", User.class);
        query.setParameter("role", "Teacher");
        return query.getResultList();
    }
	@Override
	@Transactional
	public List<User> findStudentsNotEnrolledInCourse(Integer courseId) {
	    String jpql = "SELECT u FROM User u WHERE u.role.role = 'Student' AND u.id NOT IN " +
	                  "(SELECT chs.student.id FROM CourseHasStudent chs WHERE chs.course.id = :courseId)";
	    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	    query.setParameter("courseId", courseId);
	    return query.getResultList();
	}
	@Override
	@Transactional
	public List<User> findTeachersNotAssignedToCourse(Integer courseId) {
	    String jpql = "SELECT u FROM User u WHERE u.role.role = 'Teacher' AND u.id NOT IN " +
	                  "(SELECT cht.teacher.id FROM CourseHasTeacher cht WHERE cht.course.id = :courseId)";
	    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	    query.setParameter("courseId", courseId);
	    return query.getResultList();
	}
}
