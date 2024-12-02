package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.FieldStatus;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldStatus getField(String fieldCode);

    void deleteField(String fieldCode);

    void updateField(String fieldCode,FieldDTO fieldDTO);

    void updateAllocatedStaff(String fieldCode, List<String> staffDTOList);

    List<String> getAllFieldNames();

    FieldDTO getFieldByName(String field_name);

    List<FieldDTO> getFieldListByName(List<String> field_name);

    Optional<FieldEntity> findByFieldName(String fieldName);
}
