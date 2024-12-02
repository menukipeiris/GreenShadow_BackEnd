package lk.ijse.aad.greenShadow.dao;

import lk.ijse.aad.greenShadow.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentDAO extends JpaRepository<EquipmentEntity,String> {
    @Query("SELECT e FROM EquipmentEntity e WHERE e.name = :equipmentName")
    Optional<EquipmentEntity> findByEquipmentName(@Param("equipmentName") String equipmentName);
}
