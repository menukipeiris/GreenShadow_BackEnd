package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.VehicleDAO;
import lk.ijse.aad.greenShadow.dto.VehicleStatus;
import lk.ijse.aad.greenShadow.dto.impl.VehicleDTO;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lk.ijse.aad.greenShadow.entity.impl.VehicleEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.VehicleNotFoundException;
import lk.ijse.aad.greenShadow.service.VehicleService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(AppUtil.generateVehicleId());
        VehicleEntity saveVehicle = vehicleDao.save(mapping.toVehicleEntity(vehicleDTO));
        if(saveVehicle == null) {
            throw new DataPersistException("Vehicle not saved");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.toVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public VehicleStatus getVehicle(String vehicleCode) {
        if(vehicleDao.existsById(vehicleCode)) {
            var selectedVehicle = vehicleDao.getReferenceById(vehicleCode);
            return mapping.toVehicleDTO(selectedVehicle);
        }else {
            return new SelectedErrorStatus(2,"Selected Vehicle Not Found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> foundVehicle = vehicleDao.findById(vehicleCode);
        if(foundVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle Not Found");
        }else{
            vehicleDao.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> tmpVehicle = vehicleDao.findById(vehicleCode);
        if(!tmpVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle Not Found");
        }else {
            tmpVehicle.get().setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            tmpVehicle.get().setVehicleCode(vehicleDTO.getVehicleCode());
            tmpVehicle.get().setFuelType(vehicleDTO.getFuelType());
            tmpVehicle.get().setStatus(vehicleDTO.getStatus());
            tmpVehicle.get().setRemarks(vehicleDTO.getRemarks());
            StaffEntity staffEntity = mapping.toStaffEntity(vehicleDTO.getAssignedStaff());
            tmpVehicle.get().setAssignedStaff(staffEntity);
        }
    }
}