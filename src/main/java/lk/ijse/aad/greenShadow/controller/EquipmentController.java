package lk.ijse.aad.greenShadow.controller;

import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dto.EquipmentStatus;
import lk.ijse.aad.greenShadow.dto.impl.EquipmentDTO;
import lk.ijse.aad.greenShadow.entity.impl.EquipmentEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.EquipmentNotFoundException;
import lk.ijse.aad.greenShadow.service.EquipmentService;
import lk.ijse.aad.greenShadow.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{equipmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getSelectedEquipment(@PathVariable ("equipmentId") String equipmentId) {
        if (!RegexProcess.equipIdMatcher(equipmentId)) {
            return new SelectedErrorStatus(1, "Equipment id does not match");
        }
        return equipmentService.getEquipment(equipmentId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments() {
        return equipmentService.getAllEquipment();
    }
    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        try {
            if(!RegexProcess.equipIdMatcher(equipmentId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> updateEquipment(@PathVariable ("equipmentId") String equipmentId,
                                                @RequestBody EquipmentDTO equipmentDTO) {

        try {
            if(!RegexProcess.equipIdMatcher(equipmentId) || equipmentDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentId, equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getequipId/{equipmentName}")
    public ResponseEntity<String> getEquipId(@PathVariable("equipmentName") String equipmentName) {
        try {
            Optional<EquipmentEntity> equipmentEntity = equipmentService.findByEquipName(equipmentName);
            return ResponseEntity.ok(equipmentEntity.get().getEquipmentId());
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
