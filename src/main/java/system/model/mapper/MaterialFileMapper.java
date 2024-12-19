package system.model.mapper;

import org.springframework.stereotype.Component;

import system.model.DTO.MaterialFileDTO;
import system.model.entity.MaterialFile;

@Component
public class MaterialFileMapper {
	 
    public  MaterialFile toEntity(MaterialFileDTO dto) {
        MaterialFile materialFile = new MaterialFile();
        materialFile.setFileUrl(dto.getFileUrl());
        materialFile.setMaterial(dto.getMaterial());
        return materialFile;
    }

    public  MaterialFileDTO toDTO(MaterialFile entity) {
        MaterialFileDTO dto = new MaterialFileDTO();
        dto.setId(entity.getId());
        dto.setFileUrl(entity.getFileUrl());
        dto.setMaterialID(entity.getMaterial().getId());
        dto.setMaterial(entity.getMaterial());
        return dto;
    }

   
}