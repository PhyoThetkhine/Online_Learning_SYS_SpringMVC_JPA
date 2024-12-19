package system.service;

import java.util.List;

import system.model.DTO.AssignmentDTO;

public interface AssignmentService {
	public void save(AssignmentDTO dto) ;
    public void update(AssignmentDTO dto) ;
    public AssignmentDTO findById(Integer id) ;
    public List<AssignmentDTO> findAll();
    public void delete(Integer id) ;
    public List<AssignmentDTO> findAssignmentsByCourseId(Integer courseId) ;
}
