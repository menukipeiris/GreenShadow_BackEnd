package lk.ijse.aad.greenShadow.controller;

import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dto.FieldStatus;
import lk.ijse.aad.greenShadow.dto.impl.CropDTO;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.dto.impl.StaffDTO;
import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.exception.FieldNotFoundException;
import lk.ijse.aad.greenShadow.service.FieldService;
import lk.ijse.aad.greenShadow.util.AppUtil;
import lk.ijse.aad.greenShadow.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/fields")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(@RequestParam ("field_name") String fieldName,
                                          @RequestParam ("x") int x,
                                          @RequestParam ("y") int y,
                                          @RequestParam ("extent_size") Double extentSize,
                                          @RequestPart ("field_image1") MultipartFile fieldImage1,
                                          @RequestPart ("field_image2") MultipartFile fieldImage2,
                                          @RequestPart (value = "crops[]",required = false) List<CropDTO> crops,
                                          @RequestPart (value = "staff[]",required = false) List<StaffDTO> staff
    ) {
        String base64FieldImage1 = "";
        String base64FieldImage2 = "";
        Point location = new Point(x,y);

        try {
            byte[] bytesFieldImage1 = fieldImage1.getBytes();
            base64FieldImage1 = AppUtil.fieldImageOneToBase64(bytesFieldImage1);

            byte[] bytesFieldImage2 = fieldImage2.getBytes();
            base64FieldImage2 = AppUtil.fieldImageTwoToBase64(bytesFieldImage2);

            String field_code = AppUtil.generateFieldId();

            FieldDTO buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(field_code);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setLocation(location);
            buildFieldDTO.setExtentSize(extentSize);
            buildFieldDTO.setFieldImage1(base64FieldImage1);
            buildFieldDTO.setFieldImage2(base64FieldImage2);
            buildFieldDTO.setCrops(crops);
            buildFieldDTO.setAllocated_staff(staff);
            fieldService.saveField(buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedField(@PathVariable ("fieldCode") String fieldCode) {
        if(!RegexProcess.fieldCodeMatcher(fieldCode)){
            return new SelectedErrorStatus(1,"Field Code Not Matched");
        }
        return fieldService.getField(fieldCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        try {
            if(!RegexProcess.fieldCodeMatcher(fieldCode)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> updateField(@PathVariable ("fieldCode") String fieldCode,
                                            @RequestBody FieldDTO fieldDTO) {

        try {
            if(!RegexProcess.fieldCodeMatcher(fieldCode) || fieldDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.updateField(fieldCode, fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = {"updatestaff","/{fieldCode}"})
    public ResponseEntity<Void> updateAllocatedStaff(@PathVariable ("fieldCode") String fieldCode,
                                                     @RequestBody List<String> staffId) {


        try {
            if(!RegexProcess.fieldCodeMatcher(fieldCode) || staffId == null || staffId.isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.updateAllocatedStaff(fieldCode, staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "getallfieldnames", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllFieldName(){
        List<String> fieldNames = fieldService.getAllFieldNames();
        return ResponseEntity.ok(fieldNames);
    }

    @GetMapping(value = "/getfieldcode/{fieldName}")
    public ResponseEntity<String> getFieldCode(@PathVariable("fieldName") String fieldName) {
        try {
            Optional<FieldEntity> fieldEntity = fieldService.findByFieldName(fieldName);
            return ResponseEntity.ok(fieldEntity.get().getFieldCode());
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
