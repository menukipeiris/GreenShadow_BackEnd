package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.StaffStatus;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus getStaff(String id);

    void deleteStaff(String id);

    void updateStaff(String id,StaffDTO staffDTO);
}
