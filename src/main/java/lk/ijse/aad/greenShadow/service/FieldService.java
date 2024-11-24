package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.FieldStatus;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldStatus getField(String fieldCode);

    void deleteField(String fieldCode);

    void updateField(String fieldCode,FieldDTO fieldDTO);

    void updateAllocatedStaff(String fieldCode, List<String> staffDTOList);
}
