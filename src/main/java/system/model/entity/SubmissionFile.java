package system.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "submission_file")
public class SubmissionFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_url", nullable = false, length = 100)
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

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

    
}