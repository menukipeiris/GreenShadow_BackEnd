package lk.ijse.aad.greenShadow.dto.impl;

import lk.ijse.aad.greenShadow.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldStatus {
    private String fieldCode;
    private String fieldName;
    private Point location;
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropDTO> crops = new ArrayList<>();
    private List<StaffDTO> allocated_staff = new ArrayList<>();
}
