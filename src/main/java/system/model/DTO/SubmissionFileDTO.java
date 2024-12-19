package system.model.DTO;


import system.model.entity.Submission;

public class SubmissionFileDTO {
	
	    private Integer id;
	    private String fileUrl;
	    private Submission submission;
	    private Integer submissionID;
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
		public Submission getSubmission() {
			return submission;
		}
		public void setSubmission(Submission submission) {
			this.submission = submission;
		}
		public Integer getSubmissionID() {
			return submissionID;
		}
		public void setSubmissionID(Integer submissionID) {
			this.submissionID = submissionID;
		}
	    
}
