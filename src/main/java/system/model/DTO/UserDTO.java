package system.model.DTO;

import java.sql.Date;

import system.model.entity.Role;
import system.model.entity.User;

public class UserDTO {
	 private Integer id;
	    private String email;
	    private String name;
	    private String status;
	    private Integer roleId;
	    private String roleName;
	    private String password;
	    private Integer createAdminId;
	    private String createAdminName;
	    private User createAdmin;
	    private Role role;
	    private Date createDate;
	    private Date updateDate;
	    
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		public User getCreateAdmin() {
			return createAdmin;
		}
		public void setCreateAdmin(User createAdmin) {
			this.createAdmin = createAdmin;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public Integer getCreateAdminId() {
			return createAdminId;
		}
		public void setCreateAdminId(Integer createAdminId) {
			this.createAdminId = createAdminId;
		}
		public String getCreateAdminName() {
			return createAdminName;
		}
		public void setCreateAdminName(String createAdminName) {
			this.createAdminName = createAdminName;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
	    
}
