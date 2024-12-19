package system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.model.DTO.RoleDTO;
import system.model.entity.Role;
import system.model.repo.RoleRepository;
import system.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Helper method to convert Role to RoleDTO
    private RoleDTO convertToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());
        return roleDTO;
    }

    // Helper method to convert RoleDTO to Role
    private Role convertToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRole(roleDTO.getRole());
        return role;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Integer id) {
        Role role = roleRepository.findById(id);
        return role != null ? convertToDTO(role) : null;
    }

    @Override
    public RoleDTO getRoleByRoleName(String role) {
        Role foundRole = roleRepository.findByRole(role);
        return foundRole != null ? convertToDTO(foundRole) : null;
    }

    @Override
    public void saveRole(RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        roleRepository.save(role);
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        Role updatedRole = roleRepository.update(role);
        return convertToDTO(updatedRole);
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.delete(id);
    }
}
