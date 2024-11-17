package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.dao.FieldDAO;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.FieldNotFoundException;
import lk.ijse.aad.greenShadow.service.FieldService;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDAO fieldDao;
    @Autowired
    private Mapping fieldMapping;
    @Override
    public void saveField(FieldDTO buildFieldDTO) {
        FieldEntity savedField =fieldDao.save(fieldMapping.toFieldEntity(buildFieldDTO));
        if (savedField == null) {
            throw new DataPersistException("Field is not saved!");
        }
    }

    @Override
    public void uploadImages(String fieldCode, FieldDTO buildFieldDTO) {
        Optional<FieldEntity> fieldField =fieldDao.findById(fieldCode);
        if (fieldField.isPresent()) {
            fieldField.get().setFieldImage1(buildFieldDTO.getFieldImage1());
            fieldField.get().setFieldImage2(buildFieldDTO.getFieldImage2());
        }else {
            throw new FieldNotFoundException("This Field-" + fieldCode + " is not found");
        }
    }

    @Override
    public List<FieldDTO> getAllField() {
        return fieldMapping.asFieldDTOList(fieldDao.findAll());
    }
}
