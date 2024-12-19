package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.MaterialFileDTO;
import system.model.entity.Material;
import system.model.entity.MaterialFile;
import system.model.mapper.MaterialFileMapper;
import system.model.repo.MaterialFileRepository;
import system.model.repo.MaterialRepository;
import system.service.MaterialFileService;

@Service
public class MaterialFileServiceImpl implements MaterialFileService{
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private MaterialFileRepository materialFileRepository;
	@Autowired
	private MaterialFileMapper materialFileMapper;

	@Override
	public void saveMaterialFile(MaterialFileDTO dto) {
		Material material = materialRepository.findById(dto.getMaterialID());
		dto.setMaterial(material);
		MaterialFile materialFile = materialFileMapper.toEntity(dto);
		materialFileRepository.save(materialFile);
	}

	

	@Override
	public List<MaterialFileDTO> getFilesByMaterialId(Integer materialId) {
	    List<MaterialFile> materialFiles = materialFileRepository.findByMaterialId(materialId);
	    return materialFiles.stream()
	                        .map(materialFileMapper::toDTO)
	                        .collect(Collectors.toList());
	}
	@Override
	public void updateMaterialFile(MaterialFileDTO materialFileDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MaterialFileDTO getMaterialFileById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MaterialFileDTO> getAllMaterialFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMaterialFile(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
