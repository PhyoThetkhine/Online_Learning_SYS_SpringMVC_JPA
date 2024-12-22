package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import system.model.DTO.MaterialFileDTO;
import system.model.entity.Material;
import system.model.entity.MaterialFile;
import system.model.mapper.MaterialFileMapper;
import system.model.repo.MaterialFileRepository;
import system.model.repo.MaterialRepository;
import system.service.CloudinaryService;
import system.service.MaterialFileService;

@Service
public class MaterialFileServiceImpl implements MaterialFileService{
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private MaterialFileRepository materialFileRepository;
	@Autowired
	private MaterialFileMapper materialFileMapper;
	@Autowired
	private CloudinaryService cloudinaryService;

	@Override
	public void saveMaterialFile(MaterialFileDTO dto) {
		Material material = materialRepository.findById(dto.getMaterialID());
		dto.setMaterial(material);
		MaterialFile materialFile = materialFileMapper.toEntity(dto);
		materialFileRepository.save(materialFile);
	}


	@Override
	public void addnewFile(MaterialFileDTO dto) {
		Material material = materialRepository.findById(dto.getMaterialID());
		
		List<MultipartFile> files = dto.getFiles();
		 if (files != null && !files.isEmpty()) {
	            for (MultipartFile file : files) {
	                try {
	                    String fileUrl = cloudinaryService.rawFileUpload(file);
	                    dto.setFileUrl(fileUrl);
	                    dto.setMaterial(material);
	                    MaterialFile materialFile = materialFileMapper.toEntity(dto);
	                    materialFileRepository.save(materialFile);
	                } catch (Exception e) {
	                    System.out.println("Failed to upload file: " + file.getOriginalFilename());
	                    e.printStackTrace();
	                }
	            }
	        }
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
	    MaterialFile materialFile = materialFileRepository.findById(materialFileDTO.getId());
	    if (materialFile != null) {
	        MaterialFile updatedMaterialFile = materialFileMapper.toEntity(materialFileDTO);
	        materialFileRepository.update(updatedMaterialFile);
	    }
	}

	@Override
	public MaterialFileDTO getMaterialFileById(Integer id) {
		MaterialFile materialFile = materialFileRepository.findById(id);
	    return materialFile != null ? materialFileMapper.toDTO(materialFile) : null;
	}

	@Override
	public List<MaterialFileDTO> getAllMaterialFiles() {
	    List<MaterialFile> materialFiles = materialFileRepository.findAll();
	    return materialFiles.stream()
	                        .map(materialFileMapper::toDTO)
	                        .collect(Collectors.toList());
	}

	@Override
	public void deleteMaterialFile(Integer id) {
	    materialFileRepository.delete(id);
	}





}
