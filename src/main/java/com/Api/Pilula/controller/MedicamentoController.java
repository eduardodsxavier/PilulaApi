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

import com.Api.Pilula.dtos.MedicamentoInfoDto;
import com.Api.Pilula.service.MedicamentoService;

@RequestMapping("/medicamentos")
@RestController
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicamentoInfoDto addMedicamento(@RequestBody MedicamentoInfoDto medicamentoInfo) {
        return service.save(medicamentoInfo);
    }

    @GetMapping
    public List<MedicamentoInfoDto> getAllMecicamentos() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<MedicamentoInfoDto> getMecicamentoById(@PathVariable("id") Long id) {
        return new ResponseEntity<MedicamentoInfoDto>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/usuario/{cpf}")
    public List<MedicamentoInfoDto> getMecicamentoByUsuario(@PathVariable("cpf") String usuarioCpf) {
        return service.getByUsuarioCpf(usuarioCpf);
    }

    @PutMapping("{id}")
    public ResponseEntity<MedicamentoInfoDto> updateMedicamento(@PathVariable("id") Long id, @RequestBody MedicamentoInfoDto medicamentoInfo) {
        return new ResponseEntity<MedicamentoInfoDto>(service.update(id, medicamentoInfo), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMedicamento(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<String>("medicamento deleted successfully!", HttpStatus.OK);
    }
}
