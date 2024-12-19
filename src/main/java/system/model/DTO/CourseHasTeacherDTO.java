package system.model.DTO;

import java.sql.Date;

import system.model.entity.Course;
import system.model.entity.User;

public class CourseHasTeacherDTO {
	private Integer courseId;
	private Course course;
    private Integer teacherId;
    private Integer createAdminId;
    private String createAdminName;
    private User createAdmin;
    private User teacher;
    private String status;
    private Date createDate;
    private Date updateDate;
    
	public User getCreateAdmin() {
		return createAdmin;
	}
	public void setCreateAdmin(User createAdmin) {
		this.createAdmin = createAdmin;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
    
    

}
