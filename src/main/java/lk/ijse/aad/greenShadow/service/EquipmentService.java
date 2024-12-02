package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.EquipmentStatus;
import lk.ijse.aad.greenShadow.dto.impl.EquipmentDTO;
import lk.ijse.aad.greenShadow.entity.impl.EquipmentEntity;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipment();

    EquipmentStatus getEquipment(String equipmentId);

    void deleteEquipment(String equipmentId);

    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);

    Optional<EquipmentEntity> findByEquipName(String equipmentName);

}
