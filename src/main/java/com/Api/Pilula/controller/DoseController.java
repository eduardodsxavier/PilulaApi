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

import com.Api.Pilula.model.Dose;
import com.Api.Pilula.service.DoseService;

@RequestMapping("/doses")
@RestController
public class DoseController {

    @Autowired
    private DoseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dose addDose(Dose dose) {
        return service.save(dose);
    }

    @GetMapping
    public List<Dose> getAllDoses() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Dose> getDosesById(@PathVariable("id") Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medicamentos/{id}")
    public List<Dose> getDosesByMedicamentoId(@PathVariable("id") Long medicamentoId) {
        return service.getByMedicamentoId(medicamentoId);
    }

    @PutMapping("{id}")
    public ResponseEntity<Dose> updateDose(@PathVariable("id") Long id, @RequestBody Dose dose) {
        return new ResponseEntity<Dose>(service.update(id, dose), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDose(@PathVariable("id") Long id) {
        return new ResponseEntity<String>("Dose deleted successfully!", HttpStatus.OK);
    }
}
