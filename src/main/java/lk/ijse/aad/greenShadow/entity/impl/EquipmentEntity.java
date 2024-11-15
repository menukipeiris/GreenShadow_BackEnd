package lk.ijse.aad.greenShadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.aad.greenShadow.entity.EquipmentType;
import lk.ijse.aad.greenShadow.entity.Status;
import lk.ijse.aad.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity assignedStaff;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity assignedField;
}
