package ru.dahhwe.lab6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dahhwe.lab6.models.Corporation;
import ru.dahhwe.lab6.services.CorporationService;

import java.util.List;

@RestController
@RequestMapping("/api/corporations")
public class CorporationController {

    @Autowired
    private CorporationService corporationService;

    @GetMapping
    public List<Corporation> getAllCorporations() {
        System.out.println("Here");

        return corporationService.getAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Corporation> getCorporationById(@PathVariable Integer id) {
//        Corporation corporation = companyDao.findById(id);
//        if (corporation == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(corporation, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Corporation> createCorporation(@RequestBody Corporation corporation) {
//        companyDao.save(corporation);
//        return new ResponseEntity<>(corporation, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Corporation> updateCorporation(@PathVariable Integer id, @RequestBody Corporation corporation) {
//        Corporation existingCorporation = companyDao.findById(id);
//        if (existingCorporation == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        existingCorporation.setName(corporation.getName());
//        existingCorporation.setIndustry(corporation.getIndustry());
//        companyDao.update(existingCorporation);
//        return new ResponseEntity<>(existingCorporation, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCorporation(@PathVariable Integer id) {
//        Corporation corporation = companyDao.findById(id);
//        if (corporation == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        companyDao.delete(corporation);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
