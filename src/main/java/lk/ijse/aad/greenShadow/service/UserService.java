package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(UserDTO userDTO);
    UserDetailsService userDetailsService();

}
