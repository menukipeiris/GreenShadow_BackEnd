package lk.ijse.aad.greenShadow.dto.impl;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lk.ijse.aad.greenShadow.dto.MonitoringLogStatus;
import lk.ijse.aad.greenShadow.entity.impl.CropEntity;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements MonitoringLogStatus {
    private String logCode;
    private String logDate;
    private String logDetails;
    private String observedImage;
    @JsonManagedReference
    private List<FieldDTO> fields;
    @JsonManagedReference
    private List<CropDTO> crops;
    @JsonManagedReference
    private List<StaffDTO> staff;
}
