package lk.ijse.aad.greenShadow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.aad.greenShadow.customStatusCode.SelectedErrorStatus;
import lk.ijse.aad.greenShadow.dto.CropStatus;
import lk.ijse.aad.greenShadow.dto.impl.CropDTO;
import lk.ijse.aad.greenShadow.dto.impl.FieldDTO;
import lk.ijse.aad.greenShadow.exception.CropNotFoundException;
import lk.ijse.aad.greenShadow.exception.DataPersistException;
import lk.ijse.aad.greenShadow.service.CropService;
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
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestParam ("common_name") String commonName,
                                         @RequestParam ("scientific_name") String scientificName,
                                         @RequestPart ("crop_image") MultipartFile cropImage,
                                         @RequestParam ("category") String category,
                                         @RequestParam ("season") String season,
                                         @RequestParam ("field") String fieldDTO
    ){
        String base64CropImage = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FieldDTO field = objectMapper.readValue(fieldDTO, FieldDTO.class);
            byte[] bytesCropImage = cropImage.getBytes();
            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);

            String crop_code = AppUtil.generateCropId();

            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCropCode(crop_code);
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setCropImage(base64CropImage);
            buildCropDTO.setCategory(category);
            buildCropDTO.setSeason(season);
            buildCropDTO.setField(field);
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCrops();
    }

    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable("cropCode") String cropCode){
        if (!RegexProcess.cropCodeMatcher(cropCode)) {
            return new SelectedErrorStatus(1,"Crop Code is not valid!");
        }
        return cropService.getSelectedCrop(cropCode);
    }
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropCode") String crop_code){
        try {
            if(!RegexProcess.cropCodeMatcher(crop_code)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(crop_code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/{cropCode}")
    public ResponseEntity<Void> updateCrop(@PathVariable ("cropCode") String cropCode,
                                           @RequestBody CropDTO cropDTO){

        try {
            if(!RegexProcess.cropCodeMatcher(cropCode) || cropDTO ==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.updateCrop(cropCode, cropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
