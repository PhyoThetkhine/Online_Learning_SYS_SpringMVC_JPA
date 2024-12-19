package system.model.repo;

import java.util.List;


import system.model.entity.User;


public interface UserRepository {
	public void save(User user);
	public void update(User user);
	 public User findById(Integer id);
	 public List<User> findAll();
	 public void delete(Integer id);
	 User findUserByEmail(String email);
	 List<User> findUserWhereRoleIsStudent();
	 public List<User> findUserWhereRoleIsTeacher();
	 public List<User> findStudentsNotEnrolledInCourse(Integer courseId);
	 public List<User> findTeachersNotAssignedToCourse(Integer courseId);
	 
}
