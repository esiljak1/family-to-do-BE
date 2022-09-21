package com.esiljak1.familytodo.family;

import com.esiljak1.familytodo.exceptions.NonExistentUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/family")
public class FamilyController {
    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping
    public List<Family> getFamilies(){
        return familyService.getFamilies();
    }

    @PostMapping
    public void createFamily(@RequestBody FamilyDTO familyDTO) throws NonExistentUserException {
        familyService.createFamily(familyDTO);
    }
}
