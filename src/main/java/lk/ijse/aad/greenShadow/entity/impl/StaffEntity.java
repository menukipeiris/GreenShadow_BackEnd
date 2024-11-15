package lk.ijse.aad.greenShadow.entity.impl;


import jakarta.persistence.*;
import lk.ijse.aad.greenShadow.entity.Gender;
import lk.ijse.aad.greenShadow.entity.Role;
import lk.ijse.aad.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joinedDate;
    private String dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(name = "FieldStaffDetails",joinColumns = @JoinColumn(name = "staffId"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode"))
    private List<FieldEntity> fields;
    @OneToMany(mappedBy = "assignedStaff", cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles;

}
