package system.service;

import java.util.List;

import system.model.DTO.MaterialDTO;

public interface MaterialService {
	
	public void save(MaterialDTO material);
	   public  void update(MaterialDTO material);
	   public  MaterialDTO findById(Integer id);
	   public  List<MaterialDTO> findAll();
	   public void delete(Integer id);
	   public List<MaterialDTO> findMaterialsByCourseId(Integer courseId);
}
