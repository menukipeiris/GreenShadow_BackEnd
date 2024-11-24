package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.CropDAO;
import lk.ijse.aad.greenShadow.dao.FieldDAO;
import lk.ijse.aad.greenShadow.dto.CropStatus;
import lk.ijse.aad.greenShadow.dto.impl.CropDTO;
import lk.ijse.aad.greenShadow.entity.impl.CropEntity;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.exception.CropNotFoundException;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.FieldNotFoundException;
import lk.ijse.aad.greenShadow.service.CropService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
public class CropServiceIMPL implements CropService {
    @Autowired
    private CropDAO cropDao;
    @Autowired
    private FieldDAO fieldDao;
    @Autowired
    private Mapping cropMapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(AppUtil.generateCropId());
        CropEntity saveCrop = cropDao.save(cropMapping.toCropEntity(cropDTO));
        if(saveCrop == null) {
            throw new DataPersistException("Crop not saved");
        }

    }

    @Override
    public List<CropDTO> getAllCrops() {
        return cropMapping.asCropDTOList(cropDao.findAll());

    }

    @Override
    public CropStatus getSelectedCrop(String cropCode) {
        if(cropDao.existsById(cropCode)) {
            var selectedCrop = cropDao.getReferenceById(cropCode);
            return cropMapping.toCropDTO(selectedCrop);
        }else {
            return new SelectedErrorStatus(2,"Selected crop does not exist");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> foundCrop = cropDao.findById(cropCode);
        if(foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> tmpCrop = cropDao.findById(cropCode);
        if(!tmpCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            tmpCrop.get().setCommonName(cropDTO.getCommonName());
            tmpCrop.get().setScientificName(cropDTO.getScientificName());
            tmpCrop.get().setCropImage(cropDTO.getCropImage());
            tmpCrop.get().setCategory(cropDTO.getCategory());
            tmpCrop.get().setSeason(cropDTO.getSeason());
            FieldEntity fieldEntity = cropMapping.toFieldEntity(cropDTO.getField());
            tmpCrop.get().setField(fieldEntity);
        }
    }
}