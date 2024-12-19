package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.MaterialDTO;
import system.model.entity.Material;

@Component
public class MaterialMapper {
    public  MaterialDTO toDTO(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId());
        dto.setTitle(material.getTitle());
        dto.setDescription(material.getDescription());
        dto.setCourse(material.getCourse());
        dto.setCreateTeacher(material.getCreateTeacher());
        dto.setCreateDate(material.getCreateDate());
        dto.setUpdateDate(material.getUpdateDate());
        dto.setCourseID(material.getCourse().getId());
        dto.setCreateTeacherID(material.getCreateTeacher().getId());
        return dto;
    }
    
    public  Material toEntity(MaterialDTO dto) {
        Material material = new Material();
        material.setId(dto.getId());
        material.setTitle(dto.getTitle());
        material.setDescription(dto.getDescription());
        material.setCourse(dto.getCourse());
        material.setCreateTeacher(dto.getCreateTeacher());
        return material;
    }
}
