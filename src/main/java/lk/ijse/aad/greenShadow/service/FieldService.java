package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO buildFieldDTO);

    void uploadImages(String fieldCode, FieldDTO buildFieldDTO);

    List<FieldDTO> getAllField();
}
