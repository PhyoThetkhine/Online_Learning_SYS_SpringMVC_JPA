package system.model.repo;

import java.util.List;

import system.model.entity.Material;

public interface MaterialRepository {
	public void save(Material material);
   public  void update(Material material);
   public  Material findById(Integer id);
   public  List<Material> findAll();
   public void delete(Integer id);
   public List<Material> findMaterialsByCourseId(Integer courseId);
}
