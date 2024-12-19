package system.model.repo;

import java.util.List;

import system.model.entity.AssignmentFile;

public interface AssignmentFileRepository {

	public void save(AssignmentFile assignmentFile);

	  public  void update(AssignmentFile assignmentFile);

	  public   AssignmentFile findById(Integer id);

	  public  List<AssignmentFile> findAll();

	   public void delete(Integer id);

	   public List<AssignmentFile> findByAssignmentId(Integer assignmentId);
}
