package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.VehicleStatus;
import lk.ijse.aad.greenShadow.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleStatus getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);
}
