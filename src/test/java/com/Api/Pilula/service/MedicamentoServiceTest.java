package com.Api.Pilula.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import com.Api.Pilula.model.Medicamento;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.enums.Administracao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicamentoServiceTest {

    @Autowired
    private MedicamentoService service;

    @Test
    @DisplayName("Test 1")
    @Order(1)
    @Rollback(value = false)
    public void saveMedicamentoTest(){
        Medicamento medicamento = new Medicamento(1L, new Usuario(), "dipirona", "2mg", Administracao.Injecao, "3x", Date.valueOf("2025-05-10"), Date.valueOf("2025-06-10"), true, "none");
        service.save(medicamento);

        System.out.println(medicamento);
        Assertions.assertThat(medicamento.id()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 2")
    @Order(2)
    public void getMedicamentoTest(){
        Medicamento medicamento = service.getById(1L).get();

        System.out.println(medicamento);
        Assertions.assertThat(medicamento.id()).isEqualTo(1L);
        Assertions.assertThat(medicamento.nome()).isEqualTo("dipirona");
    }

    @Test
    @DisplayName("Test 3")
    @Order(3)
    public void getListOfMedicamentosTest(){
        List<Medicamento> medicamentos = service.getAll();

        System.out.println(medicamentos);
        Assertions.assertThat(medicamentos.size()).isGreaterThan(0);

    }

    @Test
    @DisplayName("Test 4")
    @Order(4)
    @Rollback(value = false)
    public void updateMedicamentoTest(){
        Medicamento medicamento = service.getById(1L).get();
        medicamento.setDosagem("3mg");
        medicamento.setFrequencia("4x");
        Medicamento medicamentoUpdated = service.save(medicamento);

        System.out.println(medicamentoUpdated);
        Assertions.assertThat(medicamento.frequencia()).isEqualTo("4x");
        Assertions.assertThat(medicamento.dosagem()).isEqualTo("3mg");

    }

    @Test
    @DisplayName("Test 5")
    @Order(5)
    @Rollback(value = false)
    public void deleteMedicamentoTest(){
        service.delete(1L);
        Optional<Medicamento> medicamentoOptional = service.getById(1L);

        Assertions.assertThat(medicamentoOptional).isEmpty();
    }

}
