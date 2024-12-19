package system.model.repo;

import java.util.List;

import system.model.entity.Submission;

public interface SubmissionRepository {
	public void save(Submission submission);
	public void update(Submission submission);
	public Submission findById(Integer id);
	public List<Submission> findAll();
	public void delete(Integer id) ;
	 public List<Submission> findByAssignmentId(Integer assignmentId);
	 public List<Submission> findByStudentId(Integer studentId);

}
