package lk.ijse.aad.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dao.MonitoringLogDAO;
import lk.ijse.aad.greenShadow.dto.MonitoringLogStatus;
import lk.ijse.aad.greenShadow.dto.impl.MonitoringLogDTO;
import lk.ijse.aad.greenShadow.entity.impl.CropEntity;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.MonitoringLogEntity;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.LogNotFoundException;
import lk.ijse.aad.greenShadow.service.LogService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogServiceIMPL implements LogService {
    @Autowired
    private MonitoringLogDAO monitoringLogDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveLog(MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setLogCode(AppUtil.generateLogId());
        MonitoringLogEntity saveLog = monitoringLogDao.save(mapping.toMonitoringLogEntity
                (monitoringLogDTO));
        if (saveLog == null) {
            throw new DataPersistException("Log not saved");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        return mapping.toMonitoringLogDTOList(monitoringLogDao.findAll());
    }

    @Override
    public MonitoringLogStatus getLog(String logCode) {
        if (monitoringLogDao.existsById(logCode)) {
            var selectedLog = monitoringLogDao.getReferenceById(logCode);
            return mapping.toMonitoringLogDTO(selectedLog);
        } else {
            return new SelectedErrorStatus(2, "Selected Log not found");
        }
    }

    @Override
    public void deleteLog(String logCode) {
        Optional<MonitoringLogEntity> foundLog = monitoringLogDao.findById(logCode);
        if (foundLog.isPresent()) {
            throw new LogNotFoundException("Log not found");
        } else {
            monitoringLogDao.deleteById(logCode);
        }
    }

    @Override
    public void updateLog(String logCode, MonitoringLogDTO monitoringLogDTO) {
        Optional<MonitoringLogEntity> tmpLog = monitoringLogDao.findById(logCode);
        if (!tmpLog.isPresent()) {
            throw new LogNotFoundException("Log not found");
        } else {
            tmpLog.get().setLogDate(monitoringLogDTO.getLogDate());
            tmpLog.get().setLogDetails(monitoringLogDTO.getLogDetails());
            tmpLog.get().setObservedImage(monitoringLogDTO.getObservedImage());
            List<FieldEntity> fieldEntityList = mapping.toFieldEntityList(monitoringLogDTO.getFields());
            tmpLog.get().setFields(fieldEntityList);
            List<CropEntity> cropEntityList = mapping.toCropEntityList(monitoringLogDTO.getCrops());
            tmpLog.get().setCrops(cropEntityList);
            List<StaffEntity> staffEntityList = mapping.toStaffEntityList(monitoringLogDTO.getStaff());
            tmpLog.get().setStaff(staffEntityList);
        }
    }
}
