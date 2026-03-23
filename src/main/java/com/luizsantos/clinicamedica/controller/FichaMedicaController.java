package com.luizsantos.clinicamedica.controller;

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
import org.springframework.web.server.ResponseStatusException;

import com.luizsantos.clinicamedica.entity.ficha.FichaMedica;
import com.luizsantos.clinicamedica.entity.paciente.Paciente;
import com.luizsantos.clinicamedica.service.FichaMedicaService;
import com.luizsantos.clinicamedica.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class FichaMedicaController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private FichaMedicaService fichaService;

    // Endpoints da Ficha Médica

    @PostMapping("/{id}/ficha")
    public ResponseEntity<FichaMedica> adicionarFicha(@PathVariable Long id, @RequestBody @Valid FichaMedica ficha) {
        Paciente paciente = pacienteService.findById(id); // Usa o PacienteService para validar existência
        FichaMedica novaFicha = fichaService.criarFichaParaPaciente(paciente, ficha);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFicha);
    }

    @GetMapping("/{id}/ficha")
    public FichaMedica getFicha(@PathVariable Long id) {
        Paciente paciente = pacienteService.findById(id);
        if (paciente.getFichaMedica() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não possui ficha médica");
        }
        return paciente.getFichaMedica();
    }

    @PutMapping("/{id}/ficha")
    public FichaMedica atualizarFicha(@PathVariable Long id, @RequestBody FichaMedica fichaAtualizada) {
        return fichaService.atualizarFicha(id, fichaAtualizada);
    }

    @DeleteMapping("/{id}/ficha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFicha(@PathVariable Long id) {
        fichaService.deletarFicha(id);
    }

    @GetMapping("/tipo-sanguineo/{tipo}")
    public List<Paciente> findByTipoSanguineo(@PathVariable String tipo) {
        return fichaService.findByTipoSanguineo(tipo);
    }
}