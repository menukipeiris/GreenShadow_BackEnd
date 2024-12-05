package lk.ijse.aad.greenShadow.dao;

import lk.ijse.aad.greenShadow.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);

}
