package lk.ijse.aad.greenShadow.dto.impl;

import lk.ijse.aad.greenShadow.dto.EquipmentStatus;
import lk.ijse.aad.greenShadow.entity.EquipmentType;
import lk.ijse.aad.greenShadow.entity.Status;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private Status status;
    private StaffDTO assignedStaff;
    private FieldDTO assignedField;
}
