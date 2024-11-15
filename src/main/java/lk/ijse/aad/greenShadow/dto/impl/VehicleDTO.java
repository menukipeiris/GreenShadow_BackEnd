package lk.ijse.aad.greenShadow.dto.impl;

import lk.ijse.aad.greenShadow.dto.VehicleStatus;
import lk.ijse.aad.greenShadow.entity.Fuel;
import lk.ijse.aad.greenShadow.entity.Status;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private Fuel fuelType;
    private Status status;
    private String remarks;
    private StaffEntity assignedStaff;
}
