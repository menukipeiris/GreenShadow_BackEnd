package lk.ijse.aad.greenShadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.aad.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String category;
    private String season;
    @ManyToOne
    @JoinColumn(name = "field_code")
    private FieldEntity field;

}
