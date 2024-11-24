package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.CropStatus;
import lk.ijse.aad.greenShadow.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO buildCropDTO);

    List<CropDTO> getAllCrops();

    CropStatus getSelectedCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode,CropDTO cropDTO);
}
