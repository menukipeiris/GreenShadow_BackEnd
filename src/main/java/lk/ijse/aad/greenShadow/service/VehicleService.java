package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.VehicleStatus;
import lk.ijse.aad.greenShadow.dto.impl.VehicleDTO;
import lk.ijse.aad.greenShadow.entity.impl.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleStatus getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);

    Optional<VehicleEntity> findByLicenseNumber(String licenseNumber);

}
