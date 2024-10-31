package lk.ijse.aad.greenShadow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    private String  equipmentId;
    private String  equipmentName;
    private String  equipmentType;
    private String  equipmentStatus;
    private Staff staffDetails;
    private Field fieldDetails;
}
