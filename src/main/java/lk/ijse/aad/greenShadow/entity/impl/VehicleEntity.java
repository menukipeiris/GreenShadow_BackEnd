package lk.ijse.aad.greenShadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.aad.greenShadow.entity.Fuel;
import lk.ijse.aad.greenShadow.entity.Status;
import lk.ijse.aad.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {

    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    @Enumerated(EnumType.STRING)
    private Fuel fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity assignedStaff;
}
