package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.CropStatus;
import lk.ijse.aad.greenShadow.dto.impl.CropDTO;
import lk.ijse.aad.greenShadow.entity.impl.CropEntity;

import java.util.List;
import java.util.Optional;

public interface CropService {
    void saveCrop(CropDTO buildCropDTO);

    List<CropDTO> getAllCrops();

    CropStatus getSelectedCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode,CropDTO cropDTO);

    List<String> getAllCropNames();

    List<CropDTO> getCropListByNames(List<String> crops);

    Optional<CropEntity> findByCommonName(String commonName);

}
