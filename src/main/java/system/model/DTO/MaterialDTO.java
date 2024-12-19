package system.model.DTO;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import system.model.entity.Course;
import system.model.entity.User;

public class MaterialDTO {
	
	    private Integer id;
	    private String title;
	    private String description;
	    private Course course;
	    private User createTeacher;
	    private Date createDate;
	    private Date updateDate;
	    private Integer courseID;
	    private Integer createTeacherID;
	    private List<MultipartFile> files;
		public List<MultipartFile> getFiles() {
			return files;
		}
		public void setFiles(List<MultipartFile> files) {
			this.files = files;
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
		public Course getCourse() {
			return course;
		}
		public void setCourse(Course course) {
			this.course = course;
		}
		public User getCreateTeacher() {
			return createTeacher;
		}
		public void setCreateTeacher(User createTeacher) {
			this.createTeacher = createTeacher;
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
		public Integer getCourseID() {
			return courseID;
		}
		public void setCourseID(Integer courseID) {
			this.courseID = courseID;
		}
		public Integer getCreateTeacherID() {
			return createTeacherID;
		}
		public void setCreateTeacherID(Integer createTeacherID) {
			this.createTeacherID = createTeacherID;
		}

	    
}
