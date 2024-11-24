package lk.ijse.aad.greenShadow.customStatusCode;

import lk.ijse.aad.greenShadow.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CropStatus, FieldStatus, StaffStatus, VehicleStatus, EquipmentStatus,MonitoringLogStatus {
    private int statusCode;
    private String statusMessage;
}
