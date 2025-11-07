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

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/doses")
@RestController
public class DoseController {

    @Autowired
    private DoseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoseInfoDto addDose(@RequestBody DoseInfoDto doseInfo, HttpServletRequest request) {
        return service.save(doseInfo, request);
    }

    @GetMapping("{id}")
    public ResponseEntity<DoseInfoDto> getDosesById(@PathVariable("id") Long id, HttpServletRequest request) {
        return new ResponseEntity<DoseInfoDto>(service.getById(id, request), HttpStatus.OK);
    }

    @GetMapping("/medicamentos/{id}")
    public List<DoseInfoDto> getDosesByMedicamentoId(@PathVariable("id") Long medicamentoId, HttpServletRequest request) {
        return service.getByMedicamentoId(medicamentoId, request);
    }

    @PutMapping("{id}")
    public ResponseEntity<DoseInfoDto> updateDose(@PathVariable("id") Long id, @RequestBody DoseInfoDto doseInfo, HttpServletRequest request) {
        return new ResponseEntity<DoseInfoDto>(service.update(id, doseInfo, request), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDose(@PathVariable("id") Long id, HttpServletRequest request) {
        service.delete(id, request);
        return new ResponseEntity<String>("Dose deleted successfully!", HttpStatus.OK);
    }
}
