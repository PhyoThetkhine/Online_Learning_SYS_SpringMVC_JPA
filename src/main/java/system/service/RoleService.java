package system.service;

import java.util.List;

import system.model.DTO.RoleDTO;


public interface RoleService {
	 List<RoleDTO> getAllRoles();
	 RoleDTO getRoleById(Integer id);
	 RoleDTO getRoleByRoleName(String role);
	    void saveRole(RoleDTO RoleDTO);
	    RoleDTO updateRole(RoleDTO role);
	    void deleteRole(Integer id);
}
