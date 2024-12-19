package system.model.DTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import system.model.entity.Course;
import system.model.entity.User;

public class AssignmentDTO {
	    private Integer id;
	    private String title;
	    private String description;
	    private Course course;
	    private Integer courseID;
	    private LocalDateTime dueDate;
	    private String point;
	    private Date createDate;
	    private Date updateDate;
	    private User createTeacher;
	    private Integer createTeacherID;
	    private String createTeacherName;
	    private String status;
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
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getCourseID() {
			return courseID;
		}

		public void setCourseID(Integer courseID) {
			this.courseID = courseID;
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
		public LocalDateTime getDueDate() {
			return dueDate;
		}
		public void setDueDate(LocalDateTime dueDate) {
			this.dueDate = dueDate;
		}
		public String getPoint() {
			return point;
		}
		public void setPoint(String point) {
			this.point = point;
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
		public User getCreateTeacher() {
			return createTeacher;
		}
		public void setCreateTeacher(User createTeacher) {
			this.createTeacher = createTeacher;
		}
		public Integer getCreateTeacherID() {
			return createTeacherID;
		}
		public void setCreateTeacherID(Integer createTeacherID) {
			this.createTeacherID = createTeacherID;
		}
		public String getCreateTeacherName() {
			return createTeacherName;
		}
		public void setCreateTeacherName(String createTeacherName) {
			this.createTeacherName = createTeacherName;
		}
	    
}
