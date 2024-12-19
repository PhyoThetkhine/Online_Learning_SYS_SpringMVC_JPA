package system.model.repo;

import java.util.List;


import system.model.entity.Role;

public interface RoleRepository {
	 public List<Role> findAll();
	 public Role findById(Integer id);
	 public void save(Role role);
	 public Role update(Role role);
	 public void delete(Integer id);
	 public Role findByRole(String role);
}
