package lk.ijse.aad.greenShadow.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
@ToString(exclude = {"crops", "allocated_staff"})

public class FieldEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point location;
    private Double extentSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<CropEntity> crops=new ArrayList<>();
    @ManyToMany(mappedBy = "fields")
    private List<StaffEntity> staff=new ArrayList<>();

}
