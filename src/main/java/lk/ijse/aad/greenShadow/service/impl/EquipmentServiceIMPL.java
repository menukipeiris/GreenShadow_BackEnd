package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.EquipmentDAO;
import lk.ijse.aad.greenShadow.dao.FieldDAO;
import lk.ijse.aad.greenShadow.dao.StaffDAO;
import lk.ijse.aad.greenShadow.dto.EquipmentStatus;
import lk.ijse.aad.greenShadow.dto.impl.EquipmentDTO;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;
import lk.ijse.aad.greenShadow.entity.impl.EquipmentEntity;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.EquipmentNotFoundException;
import lk.ijse.aad.greenShadow.service.EquipmentService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffDAO staffDao;
    @Autowired
    private FieldDAO fieldDao;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.generateEquipmentId());
        EquipmentEntity saveEquipment = equipmentDao.save(mapping.toEquipmentEntity(equipmentDTO));
        if(saveEquipment == null) {
            throw new DataPersistException("Equipment not saved");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<EquipmentEntity> equipments = equipmentDao.findAll();
        return equipments.stream()
                .map(equipment -> {
                    EquipmentDTO equipmentDTO = new EquipmentDTO();
                    equipmentDTO.setName(equipment.getName());
                    equipmentDTO.setType(equipment.getType());
                    equipmentDTO.setStatus(equipment.getStatus());
                    Optional<StaffEntity> assignedStaff = staffDao.findById(equipment.getAssignedStaff().getStaffId());
                    StaffDTO assignedStaffDTO = assignedStaff.isPresent() ?
                            mapping.toStaffDTO(assignedStaff.get()) : null;
                    Optional<FieldEntity> assignedField = fieldDao.
                            findById(equipment.getAssignedField().getFieldCode());
                    FieldDTO assignedFieldDTO = assignedField.isPresent() ?
                            mapping.toFieldDTO(assignedField.get()) : null;
                    equipmentDTO.setAssignedStaff(assignedStaffDTO);
                    equipmentDTO.setAssignedField(assignedFieldDTO);
                    return equipmentDTO;
                })
                .collect(Collectors.toList());    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) {
        if(equipmentDao.existsById(equipmentId)) {
            var selectedEquipment = equipmentDao.getReferenceById(equipmentId);
            return mapping.toEquipmentDTO(selectedEquipment);
        }else{
            return new SelectedErrorStatus(2,"Selected Equipment not found");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> foundEquipment = equipmentDao.findById(equipmentId);
        if(!foundEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        }else{
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> tmpEquipment = equipmentDao.findById(equipmentId);
        if(!tmpEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        }else{
            tmpEquipment.get().setName(equipmentDTO.getName());
            tmpEquipment.get().setType(equipmentDTO.getType());
            tmpEquipment.get().setStatus(equipmentDTO.getStatus());
            StaffEntity staffEntity = mapping.toStaffEntity(equipmentDTO.getAssignedStaff());
            tmpEquipment.get().setAssignedStaff(staffEntity);
            FieldEntity fieldEntity = mapping.toFieldEntity(equipmentDTO.getAssignedField());
            tmpEquipment.get().setAssignedField(fieldEntity);
        }
    }

    @Override
    public Optional<EquipmentEntity> findByEquipName(String equipmentName) {
        return equipmentDao.findByEquipmentName(equipmentName);

    }
}
