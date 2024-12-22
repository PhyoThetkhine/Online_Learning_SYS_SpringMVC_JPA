package system.service;

import java.util.List;

import system.model.DTO.UserDTO;
import system.model.entity.User;

public interface UserService {
	 public void saveUser(UserDTO userDTO);
	 public void updateUser(UserDTO userDTO);
	 public UserDTO getUserById(Integer id);
	 public List<UserDTO> getAllUsers();
	 public void deleteUser(Integer id) ;
	 public List<UserDTO> findUserWhereRoleIsStudent();
	 public List<UserDTO> findUserWhereRoleIsTeacher();
	 public void updateStatus(Integer userId, String newStatus);
	 public UserDTO login(String email,String password);
	 public List<UserDTO> findStudentsNotEnrolledInCourse(Integer courseId);
	 public List<UserDTO> findTeachersNotAssignedToCourse(Integer courseId);
	 public boolean changePassword(Integer userId, String currentPassword, String newPassword);
	 
}
