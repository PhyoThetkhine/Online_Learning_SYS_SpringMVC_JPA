package system.model.DTO;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import system.model.entity.Assignment;
import system.model.entity.User;

public class SubmissionDTO {
	
    private Integer id;
    private Assignment assignment;
    private Integer assignmentID;
    private Integer createStudentID;
    private String createStudentName;
    private User createStudent;
    private Date createDate;
    private Date updateDate;
    private Integer mark;
    private List<MultipartFile> files;
    
	public Integer getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(Integer assignmentID) {
		this.assignmentID = assignmentID;
	}
	public Integer getCreateStudentID() {
		return createStudentID;
	}
	public void setCreateStudentID(Integer createStudentID) {
		this.createStudentID = createStudentID;
	}
	public String getCreateStudentName() {
		return createStudentName;
	}
	public void setCreateStudentName(String createStudentName) {
		this.createStudentName = createStudentName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	public User getCreateStudent() {
		return createStudent;
	}
	public void setCreateStudent(User createStudent) {
		this.createStudent = createStudent;
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
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
    
    
}
