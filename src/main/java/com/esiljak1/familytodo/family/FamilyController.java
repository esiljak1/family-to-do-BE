package com.esiljak1.familytodo.family;

import com.esiljak1.familytodo.exceptions.NonExistentUserException;
import com.esiljak1.familytodo.interfaces.CRUDInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/family")
public class FamilyController implements CRUDInterface<Long, FamilyDTO> {
    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> post(@RequestBody FamilyDTO familyDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try{
            responseMap.put("Data", familyService.createFamily(familyDTO));
        }catch (NonExistentUserException exception){
            return ResponseEntity.status(400).body(exception.getMessage());
        }
        return ResponseEntity.ok(responseMap);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> get(){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", familyService.getFamilies());
        return ResponseEntity.ok(responseMap);
    }

    @Override
    @PutMapping(path = "{familyId}")
    public ResponseEntity<?> update(@PathVariable("familyId") Long familyId, @RequestBody FamilyDTO familyDTO){
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("Data", familyService.updateFamily(familyId, familyDTO));
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
        return ResponseEntity.ok(responseMap);
    }

    @Override
    @DeleteMapping(path = "{familyId}")
    public ResponseEntity<?> delete(@PathVariable("familyId") Long familyId) {
        familyService.deleteFamily(familyId);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", "Success");
        return ResponseEntity.ok(responseMap);
    }
}
