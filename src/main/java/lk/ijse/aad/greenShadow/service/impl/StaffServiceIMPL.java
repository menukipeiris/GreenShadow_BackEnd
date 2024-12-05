package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.FieldDAO;
import lk.ijse.aad.greenShadow.dao.StaffDAO;
import lk.ijse.aad.greenShadow.dto.StaffStatus;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class StaffServiceIMPL implements StaffService {

    @Autowired
    private Mapping staffMapping;
    @Autowired
    private StaffDAO staffDao;
    @Autowired
    private FieldDAO fieldDao;


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
        List<StaffEntity> staffs = staffDao.findAll();
        return staffs.stream()
                .map(staff -> {
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setFirstName(staff.getFirstName());
                    staffDTO.setLastName(staff.getLastName());
                    staffDTO.setDesignation(staff.getDesignation());
                    staffDTO.setGender(staff.getGender());
                    staffDTO.setJoinedDate(staff.getJoinedDate());
                    staffDTO.setDob(staff.getDob());
                    staffDTO.setAddress(staff.getAddress());
                    staffDTO.setContactNo(staff.getContactNo());
                    staffDTO.setEmail(staff.getEmail());
                    staffDTO.setRole(staff.getRole());
                    List<FieldDTO> assignedFieldDTO = new ArrayList<>();
                    for (FieldEntity field : staff.getFields()) {
                        Optional<FieldEntity> fieldOpt = fieldDao.findById(field.getFieldName());
                        if (fieldOpt.isPresent()) {
                            assignedFieldDTO.add(staffMapping.toFieldDTO(fieldOpt.get()));
                        }
                    }
                    staffDTO.setFields(assignedFieldDTO);
                    return staffDTO;
                })
                .collect(Collectors.toList());    }

//    @Override
//    public StaffStatus getStaff(String id) {
//        if(staffDao.existsById(id)){
//            var selectedStaff = staffDao.getReferenceById(id);
//            return staffMapping.toStaffDTO(selectedStaff);
//        }else {
//            return new SelectedErrorStatus(2,"Selected Staff Member Not Found");
//        }
//    }

    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> foundStaff = staffDao.findById(id);
        if(!foundStaff.isPresent()) {
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
        }
    }

    @Override
    public List<String> getAllStaffNames() {
        List<StaffEntity> staffEntities = staffDao.findAll();
        return staffEntities.stream()
                .map(StaffEntity::getFirstName)
                .collect(Collectors.toList());    }

    @Override
    public List<StaffDTO> getStaffListByName(List<String> staffs) {
        if(staffs.isEmpty() || staffs == null){
            return Collections.emptyList();
        }

        List<StaffEntity> staffEntities = staffDao.findByStaffNameList(staffs);

        if(staffEntities.isEmpty()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }

        return staffEntities.stream()
                .map(staffMapping::toStaffDTO)
                .collect(Collectors.toList());
    }


    @Override
    public StaffDTO getStaffByName(String assignedStaff) {
        Optional<StaffEntity> tmpStaff = staffDao.findByStaffName(assignedStaff);
        if(!tmpStaff.isPresent()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }
        return staffMapping.toStaffDTO(tmpStaff.get());
    }

    @Override
    public Optional<StaffEntity> findByFirstName(String firstName) {
        return staffDao.findByStaffName(firstName);
    }
}
