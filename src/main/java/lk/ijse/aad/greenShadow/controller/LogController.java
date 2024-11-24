package lk.ijse.aad.greenShadow.controller;

import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dto.MonitoringLogStatus;
import lk.ijse.aad.greenShadow.dto.impl.CropDTO;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.dto.impl.MonitoringLogDTO;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.LogNotFoundException;
import lk.ijse.aad.greenShadow.service.LogService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/log")
@CrossOrigin
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestParam("logDate") String logDate,
                                        @RequestParam ("logDetails") String logDetails,
                                        @RequestPart ("observedImage") MultipartFile observedImage,
                                        @RequestPart (value = "fields[]",required = false) List<FieldDTO> fields,
                                        @RequestPart (value = "crops[]",required = false) List<CropDTO> crops,
                                        @RequestPart (value = "staff[]",required = false) List<StaffDTO> staff
    ) {
        String base64ObservedImage = "";
        try {
            byte[] byteObservedImage = observedImage.getBytes();
            base64ObservedImage = AppUtil.observedImageOneToBase64(byteObservedImage);

            String log_code = AppUtil.generateLogId();

            MonitoringLogDTO buildMonitoringLogDTO = new MonitoringLogDTO();
            buildMonitoringLogDTO.setLogCode(log_code);
            buildMonitoringLogDTO.setLogDate(logDate);
            buildMonitoringLogDTO.setLogDetails(logDetails);
            buildMonitoringLogDTO.setObservedImage(base64ObservedImage);
            buildMonitoringLogDTO.setFields(fields);
            buildMonitoringLogDTO.setCrops(crops);
            buildMonitoringLogDTO.setStaff(staff);
            logService.saveLog(buildMonitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{logCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogStatus getSelectedLog(@PathVariable ("logCode") String logCode){
        if(!RegexProcess.logCodeMatcher(logCode)){
            return new SelectedErrorStatus(1,"Log code not match");
        }
        return logService.getLog(logCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllLogs(){
        return logService.getAllLogs();
    }
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable ("logCode") String logCode){
        try {
            if(!RegexProcess.logCodeMatcher(logCode)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.deleteLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{logCode}")
    public ResponseEntity<Void> updateLog(@PathVariable ("logCode") String logCode,
                                          @RequestBody MonitoringLogDTO monitoringLogDTO){

        try {
            if(!RegexProcess.logCodeMatcher(logCode) || monitoringLogDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.updateLog(logCode,monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
