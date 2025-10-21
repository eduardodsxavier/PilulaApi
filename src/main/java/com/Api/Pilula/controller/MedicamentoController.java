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

import com.Api.Pilula.model.Medicamento;
import com.Api.Pilula.service.MedicamentoService;

@RequestMapping("/medicamentos")
@RestController
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medicamento addMedicamento(Medicamento medicamento) {
        return service.save(medicamento);
    }

    @GetMapping
    public List<Medicamento> getAllMecicamentos() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Medicamento> getMecicamentoById(@PathVariable("id") Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{id}")
    public List<Medicamento> getMecicamentoByUsuario(@PathVariable("id") String usuarioCpf) {
        return service.getByUsuarioCpf(usuarioCpf);
    }

    @PutMapping("{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable("id") Long id,
            @RequestBody Medicamento medicamento) {
        return new ResponseEntity<Medicamento>(service.update(id, medicamento), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMedicamento(@PathVariable("id") Long id) {
        return new ResponseEntity<String>("medicamento deleted successfully!", HttpStatus.OK);
    }
}
