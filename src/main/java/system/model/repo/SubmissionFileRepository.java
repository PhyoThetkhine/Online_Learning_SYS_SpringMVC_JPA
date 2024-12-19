package system.model.repo;

import java.util.List;

import system.model.entity.SubmissionFile;

public interface SubmissionFileRepository {
	public void save(SubmissionFile submissionFile);
	public void update(SubmissionFile submissionFile);
	public SubmissionFile findById(Integer id);
	public List<SubmissionFile> findAll();
	public  void delete(Integer id);
	public  List<SubmissionFile> findBySubmissionId(Integer submissionId);
}
