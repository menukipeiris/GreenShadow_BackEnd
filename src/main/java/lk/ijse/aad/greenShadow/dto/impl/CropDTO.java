package lk.ijse.aad.greenShadow.dto.impl;

import lk.ijse.aad.greenShadow.dto.CropStatus;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropStatus {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String season;
    private FieldDTO field;
}
