package lk.ijse.aad.greenShadow.dto.impl;

import lk.ijse.aad.greenShadow.dto.StaffStatus;
import lk.ijse.aad.greenShadow.entity.Gender;
import lk.ijse.aad.greenShadow.entity.Role;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private String joinedDate;
    private String dob;
    private String address;
    private String contactNo;
    private String email;
    private Role role;
    private List<FieldDTO> fields;
    private List<VehicleDTO> vehicles;
}
