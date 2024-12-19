package system.model.repo;

import java.util.List;

import system.model.entity.Assignment;

public interface AssignmentRepository {
	 public void save(Assignment assignment);
	 public void update(Assignment assignment);
	 public Assignment findById(Integer id);
	 public List<Assignment> findAll();
	 public void delete(Integer id);
	 public List<Assignment> findAssignmentsByCourseId(Integer courseId);

}
