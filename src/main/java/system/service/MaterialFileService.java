package system.service;

import java.util.List;


import system.model.DTO.MaterialFileDTO;

public interface MaterialFileService {
	 public void saveMaterialFile(MaterialFileDTO materialFileDTO);
	 public void updateMaterialFile(MaterialFileDTO materialFileDTO);
	 public MaterialFileDTO getMaterialFileById(Integer id);
	 public List<MaterialFileDTO> getAllMaterialFiles();
	 public void deleteMaterialFile(Integer id);
	 public List<MaterialFileDTO> getFilesByMaterialId(Integer materialId);
}
