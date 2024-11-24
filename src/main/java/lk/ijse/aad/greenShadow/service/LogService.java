package lk.ijse.aad.greenShadow.service;

import lk.ijse.aad.greenShadow.dto.MonitoringLogStatus;
import lk.ijse.aad.greenShadow.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface LogService {
    void saveLog(MonitoringLogDTO monitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs();

    MonitoringLogStatus getLog(String logCode);

    void deleteLog(String logCode);

    void updateLog(String logCode,MonitoringLogDTO monitoringLogDTO);
}
