package com.Api.Pilula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.service.DoseService;

@RequestMapping("/doses")
@RestController
public class DoseController {

    @Autowired
    private DoseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoseInfoDto addDose(@RequestBody DoseInfoDto doseInfo) {
        return service.save(doseInfo);
    }

    @GetMapping
    public List<DoseInfoDto> getAllDoses() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<DoseInfoDto> getDosesById(@PathVariable("id") Long id) {
        return new ResponseEntity<DoseInfoDto>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/medicamentos/{id}")
    public List<DoseInfoDto> getDosesByMedicamentoId(@PathVariable("id") Long medicamentoId) {
        return service.getByMedicamentoId(medicamentoId);
    }

    @PutMapping("{id}")
    public ResponseEntity<DoseInfoDto> updateDose(@PathVariable("id") Long id, @RequestBody DoseInfoDto doseInfo) {
        return new ResponseEntity<DoseInfoDto>(service.update(id, doseInfo), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDose(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<String>("Dose deleted successfully!", HttpStatus.OK);
    }
}
