package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import system.model.DTO.MaterialDTO;
import system.model.DTO.MaterialFileDTO;
import system.model.entity.Course;
import system.model.entity.Material;
import system.model.entity.User;
import system.model.mapper.MaterialMapper;
import system.model.repo.CourseRepository;
import system.model.repo.MaterialRepository;
import system.model.repo.UserRepository;
import system.service.CloudinaryService;
import system.service.MaterialFileService;
import system.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService{
	
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private MaterialFileService materialFileService;
	@Autowired
	private CloudinaryService cloudinaryService;
	

	@Override
	public void save(MaterialDTO dto) {
		User createTeacher = userRepository.findById(dto.getCreateTeacherID());
		Course course = courseRepository.findById(dto.getCourseID());
		dto.setCourse(course);
		dto.setCreateTeacher(createTeacher);
		Material material = materialMapper.toEntity(dto);
        materialRepository.save(material);
        List<MultipartFile> files = dto.getFiles();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                try {
                    String fileUrl = cloudinaryService.rawFileUpload(file);
                    MaterialFileDTO materialFileDTO = new MaterialFileDTO();
                    materialFileDTO.setFileUrl(fileUrl);
                    materialFileDTO.setMaterialID(material.getId());
                    materialFileService.saveMaterialFile(materialFileDTO);
                } catch (Exception e) {
                    System.out.println("Failed to upload file: " + file.getOriginalFilename());
                    e.printStackTrace();
                }
            }
        }
    }

	@Override
	public void update(MaterialDTO material) {
		
		
	}

	@Override
	public MaterialDTO findById(Integer id) {
		Material material = materialRepository.findById(id);
		 if (material != null) {
	            return materialMapper.toDTO(material);  
	        }
		return null;
	}

	@Override
	public List<MaterialDTO> findAll() {
		List<Material> material = materialRepository.findAll();
		 return material.stream()
                 .map(materialMapper::toDTO)
                 .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		Material material = materialRepository.findById(id);
		material.setStatus(Material.Status.DROP);
		 materialRepository.update(material);
	}

	@Override
	public List<MaterialDTO> findMaterialsByCourseId(Integer courseId) {
		List<Material> material = materialRepository.findMaterialsByCourseId(courseId);
		 return material.stream()
                 .map(materialMapper::toDTO)
                 .collect(Collectors.toList());
	}

}
