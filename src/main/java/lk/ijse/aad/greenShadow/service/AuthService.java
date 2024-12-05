package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.impl.UserDTO;
import lk.ijse.aad.greenShadow.secure.JWTAuthResponse;
import lk.ijse.aad.greenShadow.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
