package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.StaffDAO;
import lk.ijse.aad.greenShadow.dto.StaffStatus;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lk.ijse.aad.greenShadow.entity.impl.VehicleEntity;
import lk.ijse.aad.greenShadow.exception.StaffNotFoundException;
import lk.ijse.aad.greenShadow.service.StaffService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class StaffServiceIMPL implements StaffService {

    @Autowired
    private Mapping staffMapping;
    @Autowired
    private StaffDAO staffDao;


    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.generateStaffId());
        StaffEntity staffEntity = staffDao.save(staffMapping.toStaffEntity(staffDTO));
        if(staffEntity == null) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffMapping.toStaffDTOList(staffDao.findAll());
    }

    @Override
    public StaffStatus getStaff(String id) {
        if(staffDao.existsById(id)){
            var selectedStaff = staffDao.getReferenceById(id);
            return staffMapping.toStaffDTO(selectedStaff);
        }else {
            return new SelectedErrorStatus(2,"Selected Staff Member Not Found");
        }
    }

    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> foundStaff = staffDao.findById(id);
        if(foundStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else {
            staffDao.deleteById(id);
        }
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<StaffEntity> tmpStaff = staffDao.findById(id);
        if(!tmpStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else{
            tmpStaff.get().setFirstName(staffDTO.getFirstName());
            tmpStaff.get().setLastName(staffDTO.getLastName());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(staffDTO.getGender());
            tmpStaff.get().setJoinedDate(staffDTO.getJoinedDate());
            tmpStaff.get().setDob(staffDTO.getDob());
            tmpStaff.get().setAddress(staffDTO.getAddress());
            tmpStaff.get().setContactNo(staffDTO.getContactNo());
            tmpStaff.get().setEmail(staffDTO.getEmail());
            tmpStaff.get().setRole(staffDTO.getRole());
            List<FieldEntity> fieldEntityList = staffMapping.toFieldEntityList(staffDTO.getFields());
            tmpStaff.get().setFields(fieldEntityList);
            List<VehicleEntity> vehicleEntityList = staffMapping.toVehicleEntityList(staffDTO.getVehicles());
            tmpStaff.get().setVehicles(vehicleEntityList);
        }
    }
}
