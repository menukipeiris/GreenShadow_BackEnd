package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.dao.UserDAO;
import lk.ijse.aad.greenShadow.dto.impl.UserDTO;
import lk.ijse.aad.greenShadow.entity.impl.UserEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.service.UserService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.generateUserId());
        UserEntity saveUser = userDao.save(mapping.toUserEntity(userDTO));
        if(saveUser == null) {
            throw new DataPersistException("User not saved");
        }
    }
}
