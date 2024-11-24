package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.EquipmentStatus;
import lk.ijse.aad.greenShadow.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipment();

    EquipmentStatus getEquipment(String equipmentId);

    void deleteEquipment(String equipmentId);

    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);
}
