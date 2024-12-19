package system.model.DTO;

import java.sql.Date;

import system.model.entity.User;


public class CourseDTO {
	 private Integer id;
	    private String title;
	    private String description;
	    private String status;
	    private Date createDate;
	    private Date updateDate;
	    private String photoUrl;
	    private String createAdminName;
	    private Integer createAdminId;
	    private User createAdmin;
	    
		public User getCreateAdmin() {
			return createAdmin;
		}
		public void setCreateAdmin(User createAdmin) {
			this.createAdmin = createAdmin;
		}
		public Integer getCreateAdminId() {
			return createAdminId;
		}
		public void setCreateAdminId(Integer createAdminId) {
			this.createAdminId = createAdminId;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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
		public String getPhotoUrl() {
			return photoUrl;
		}
		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}
		public String getCreateAdminName() {
			return createAdminName;
		}
		public void setCreateAdminName(String createAdminName) {
			this.createAdminName = createAdminName;
		}

}
