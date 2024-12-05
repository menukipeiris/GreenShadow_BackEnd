package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.StaffStatus;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    void deleteStaff(String id);

    void updateStaff(String id,StaffDTO staffDTO);

    List<String> getAllStaffNames();

    List<StaffDTO> getStaffListByName(List<String> staffs);

    StaffDTO getStaffByName(String assignedStaff);

    Optional<StaffEntity> findByFirstName(String firstName);
}
