package system.model.repo;

import java.util.List;

import system.model.entity.MaterialFile;

public interface MaterialFileRepository {
	public void save(MaterialFile materialFile);
	public void update(MaterialFile materialFile);
	public MaterialFile findById(Integer id);
	public List<MaterialFile> findAll();
	public void delete(Integer id);
	public List<MaterialFile> findByMaterialId(Integer materialId);
}
