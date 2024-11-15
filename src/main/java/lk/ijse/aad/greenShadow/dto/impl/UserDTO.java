package lk.ijse.aad.greenShadow.dto.impl;


import lk.ijse.aad.greenShadow.dto.UserStatus;
import lk.ijse.aad.greenShadow.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private String userId;
    private String email;
    private String password;
    private Role role;
}
