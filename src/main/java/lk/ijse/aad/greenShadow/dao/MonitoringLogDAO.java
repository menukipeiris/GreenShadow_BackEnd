package lk.ijse.aad.greenShadow.dao;

import lk.ijse.aad.greenShadow.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitoringLogDAO extends JpaRepository<MonitoringLogEntity,String> {
    @Query("SELECT l FROM MonitoringLogEntity l WHERE l.logDetails = :logDesc")
    Optional<MonitoringLogEntity> findByLogDesc(@Param("logDesc") String logDesc);
}
