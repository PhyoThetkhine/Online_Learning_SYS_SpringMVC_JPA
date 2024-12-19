package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.UserDTO;
import system.model.entity.User;

@Component
public class UserMapper {
	
	public UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus().name());
		dto.setRoleId(entity.getRole().getId());
		dto.setRoleName(entity.getRole().getRole());
		dto.setPassword(entity.getPassword());
		dto.setCreateAdminId(entity.getCreateAdmin().getId());
		dto.setCreateAdminName(entity.getCreateAdmin().getName());
		dto.setCreateAdmin(entity.getCreateAdmin());
		dto.setRole(entity.getRole());
		dto.setCreateDate(entity.getCreateDate());
		dto.setUpdateDate(entity.getUpdateDate());
		return dto;
	}
	public User toEntity(UserDTO dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		entity.setCreateAdmin(dto.getCreateAdmin());
		entity.setRole(dto.getRole());
		return entity;
	}


}
