package lk.ijse.aad.greenShadow.dao;

import lk.ijse.aad.greenShadow.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropDAO extends JpaRepository<CropEntity,String> {
    @Query("SELECT c FROM CropEntity c WHERE c.commonName = :common_name")
    Optional<CropEntity> findByCropName(@Param("common_name") String common_name);

    @Query("SELECT c FROM CropEntity c WHERE c.commonName IN :crops")
    List<CropEntity> findByCropNameList(@Param("crops") List<String> crops);
}
