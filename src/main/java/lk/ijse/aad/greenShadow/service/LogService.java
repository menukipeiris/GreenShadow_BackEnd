package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.MonitoringLogStatus;
import lk.ijse.aad.greenShadow.dto.impl.MonitoringLogDTO;
import lk.ijse.aad.greenShadow.entity.impl.MonitoringLogEntity;

import java.util.List;
import java.util.Optional;

public interface LogService {
    void saveLog(MonitoringLogDTO monitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs();

    MonitoringLogStatus getLog(String logCode);

    void deleteLog(String logCode);

    void updateLog(String logCode,MonitoringLogDTO monitoringLogDTO);

    Optional<MonitoringLogEntity> findByLogDesc(String logDesc);

}
