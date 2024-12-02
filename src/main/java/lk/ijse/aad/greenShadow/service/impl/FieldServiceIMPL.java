package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.FieldDAO;
import lk.ijse.aad.greenShadow.dao.StaffDAO;
import lk.ijse.aad.greenShadow.dto.FieldStatus;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.entity.impl.CropEntity;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lk.ijse.aad.greenShadow.exception.FieldNotFoundException;
import lk.ijse.aad.greenShadow.exception.StaffNotFoundException;
import lk.ijse.aad.greenShadow.service.FieldService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDAO fieldDao;
    @Autowired
    private Mapping fieldMapping;
    @Autowired
    private StaffDAO staffDao;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.generateFieldId());
        FieldEntity fieldEntity = fieldDao.save(fieldMapping.toFieldEntity(fieldDTO));
        if (fieldEntity == null) {
            throw new FieldNotFoundException("Field not found");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return fieldMapping.toFieldDTOList(fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldCode) {
        if(fieldDao.existsById(fieldCode)){
            var selectedField = fieldDao.getReferenceById(fieldCode);
            return fieldMapping.toFieldDTO(selectedField);
        }else {
            return new SelectedErrorStatus(2,"Selected field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> foundField = fieldDao.findById(fieldCode);
        if(foundField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> tmpField = fieldDao.findById(fieldCode);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else{
            tmpField.get().setFieldName(fieldDTO.getFieldName());
            tmpField.get().setLocation(fieldDTO.getLocation());
            tmpField.get().setExtentSize(fieldDTO.getExtentSize());
            tmpField.get().setFieldImage1(fieldDTO.getFieldImage1());
            tmpField.get().setFieldImage2(fieldDTO.getFieldImage2());
            List<CropEntity> cropEntityList = fieldMapping.toCropEntityList(fieldDTO.getCrops());
            tmpField.get().setCrops(cropEntityList);
            List<StaffEntity> staffEntityList = fieldMapping.toStaffEntityList(fieldDTO.getAllocated_staff());
            tmpField.get().setStaff(staffEntityList);
        }
    }

    @Override
    public void updateAllocatedStaff(String fieldCode,List<String> staffId) {
        Optional<FieldEntity> tmpField = fieldDao.findById(fieldCode);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }
        FieldEntity fieldEntity = tmpField.get();
        for(String staff : staffId){
            Optional<StaffEntity> tmpStaff = staffDao.findById(staff);
            if(!tmpStaff.isPresent()){
                throw new StaffNotFoundException("Staff not found");
            }else{
                StaffEntity staffEntity = tmpStaff.get();
                fieldEntity.getStaff().add(staffEntity);
                staffEntity.getFields().add(fieldEntity);
                staffDao.save(staffEntity);
            }
        }
        fieldDao.save(fieldEntity);
    }

    @Override
    public List<String> getAllFieldNames() {
        List<FieldEntity> fieldEntities = fieldDao.findAll();
        return fieldEntities.stream()
                .map(FieldEntity::getFieldName)
                .collect(Collectors.toList());
    }

    @Override
    public FieldDTO getFieldByName(String field_name) {
        Optional<FieldEntity> tmpField = fieldDao.findByFieldName(field_name);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found: " + field_name);
        }
        return fieldMapping.toFieldDTO(tmpField.get());
    }

    @Override
    public List<FieldDTO> getFieldListByName(List<String> field_name) {
        if(field_name == null || field_name.isEmpty()){
            return Collections.emptyList();
        }

        List<FieldEntity> fieldEntities = fieldDao.findByFieldNameList(field_name);

        if(fieldEntities.isEmpty()){
            throw new FieldNotFoundException("Field not found");
        }

        return fieldEntities.stream()
                .map(fieldMapping::toFieldDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FieldEntity> findByFieldName(String fieldName) {
        return Optional.empty();
    }
}