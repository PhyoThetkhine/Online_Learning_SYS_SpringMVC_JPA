package system.model.DTO;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import system.model.entity.Assignment;

public class AssignmentFileDTO {
	    private Integer id;
	    private String fileUrl;
	    private Assignment assignment;
	    private Integer assignmentID;
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
		public String getFileUrl() {
			return fileUrl;
		}
		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}
		public Assignment getAssignment() {
			return assignment;
		}
		public void setAssignment(Assignment assignment) {
			this.assignment = assignment;
		}
		public Integer getAssignmentID() {
			return assignmentID;
		}
		public void setAssignmentID(Integer assignmentID) {
			this.assignmentID = assignmentID;
		}
}
