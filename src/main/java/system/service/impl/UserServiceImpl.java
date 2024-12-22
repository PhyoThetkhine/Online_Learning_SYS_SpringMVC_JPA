package system.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.UserDTO;
import system.model.entity.Role;
import system.model.entity.User;
import system.model.mapper.UserMapper;
import system.model.repo.RoleRepository;
import system.model.repo.UserRepository;
import system.service.BCryptEncoder;
import system.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	 @Autowired
	    private BCryptEncoder bCryptEncoder;
	 @Autowired
	 private RoleRepository roleRepository;
	 @Autowired
	    private UserRepository userRepository;
	 @Autowired
	 private UserMapper userMapper;

	
	@Override
	@Transactional
	 public void saveUser(UserDTO userDTO) {
        User existingUser = userRepository.findUserByEmail(userDTO.getEmail());
        if (existingUser == null) {
        	Role userRole = roleRepository.findById(userDTO.getRoleId());
        	userDTO.setRole(userRole);
        	User createAdmin = userRepository.findById(userDTO.getCreateAdminId());
        	userDTO.setCreateAdmin(createAdmin);
        	String hashedPassword = bCryptEncoder.encode(userDTO.getPassword());
        	userDTO.setPassword(hashedPassword);
            User userEntity = userMapper.toEntity(userDTO);
            userRepository.save(userEntity);
        }
    }
	
	
	@Override
	public UserDTO login(String email, String password) {
		User existingUser = userRepository.findUserByEmail(email);
		
		if (existingUser == null) { 
			System.out.println("user is null");
			 return null;
		}
		if (!bCryptEncoder.match(password, existingUser.getPassword())) {
			System.out.println("password worng");
			return null;
		}
		System.out.println("all correct");
		UserDTO user = userMapper.toDTO(existingUser);
		return user;
	}

	

	@Override
	public void updateUser(UserDTO userDTO) {
		 User existingUser = userRepository.findById(userDTO.getId());
	        if (existingUser != null) {
	            existingUser.setEmail(userDTO.getEmail());
	            existingUser.setName(userDTO.getName());
	            existingUser.setStatus(User.Status.valueOf(userDTO.getStatus()));
	            existingUser.setUpdateDate(new Date(System.currentTimeMillis()));
	            userRepository.update(existingUser);
	        }
	    }
	@Override
	public List<UserDTO> findStudentsNotEnrolledInCourse(Integer courseId) {
		 List<User> users= userRepository.findStudentsNotEnrolledInCourse(courseId);
		 return users.stream()
                 .map(userMapper::toDTO)
                 .collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> findTeachersNotAssignedToCourse(Integer courseId) {
		 List<User> users= userRepository.findTeachersNotAssignedToCourse(courseId);
		 return users.stream()
                 .map(userMapper::toDTO)
                 .collect(Collectors.toList());
	}


	@Override
	public UserDTO getUserById(Integer id) {
		 User user = userRepository.findById(id);
		 if(user == null) {
			 return null;
		 }
		 UserDTO dto = userMapper.toDTO(user);
	        return dto;
	}

	@Override
	public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

	@Override
	 @Transactional
	    public void deleteUser(Integer id) {
	        userRepository.delete(id);
	    }
	 @Override
	    public List<UserDTO> findUserWhereRoleIsStudent() {
	        List<User> users = userRepository.findUserWhereRoleIsStudent();
	        return users.stream()
	                    .map(userMapper::toDTO)
	                    .collect(Collectors.toList());
	    }
	 @Override
		public List<UserDTO> findUserWhereRoleIsTeacher() {
		 List<User> users = userRepository.findUserWhereRoleIsTeacher();
		 return users.stream()
                 .map(userMapper::toDTO)
                 .collect(Collectors.toList());
		}


	 @Override
	 @Transactional
	 public void updateStatus(Integer userId, String newStatus) {
	     User existingUser = userRepository.findById(userId);
	     if (existingUser != null) {
	         existingUser.setStatus(User.Status.valueOf(newStatus)); 
	         existingUser.setUpdateDate(new Date(System.currentTimeMillis())); 
	         userRepository.update(existingUser); 
	     }
	 }

	 @Override
	    @Transactional
	    public boolean changePassword(Integer userId, String currentPassword, String newPassword) {
	        User existingUser = userRepository.findById(userId);
	        if (existingUser != null && bCryptEncoder.match(currentPassword, existingUser.getPassword())) {
	            String hashedNewPassword = bCryptEncoder.encode(newPassword);
	            existingUser.setPassword(hashedNewPassword);
	            userRepository.update(existingUser);
	            return true;
	        }
	        return false;
	    }
	
	
}
