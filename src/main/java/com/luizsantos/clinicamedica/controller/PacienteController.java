package com.luizsantos.clinicamedica.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luizsantos.clinicamedica.entity.paciente.Paciente;
import com.luizsantos.clinicamedica.entity.paciente.Status;

import com.luizsantos.clinicamedica.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Endpoints do Paciente
    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody @Valid Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.salvar(paciente));
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listar();
    }

    @GetMapping("/{id}")
    public Paciente findById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }

    @GetMapping("/{id}/completo")
    public Paciente findByIdComFicha(@PathVariable Long id) {
        return pacienteService.findByIdComFicha(id);
    }

    @GetMapping("/cpf/{cpf}")
    public Paciente findByCpf(@PathVariable String cpf) {
        return pacienteService.findByCpf(cpf);
    }

    @PutMapping("/{id}")
    public Paciente update(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.atualizar(id, paciente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pacienteService.deletar(id);
    }

    // Filtros e Buscas
    @GetMapping("/status/{status}")
    public List<Paciente> findByStatus(@PathVariable Status status) {
        return pacienteService.findByStatus(status);
    }

    @GetMapping("/buscar?termo={termo}")
    public List<Paciente> buscar(@RequestParam String termo) {
        return pacienteService.buscar(termo);
    }

    @GetMapping("/com-ficha")
    public List<Paciente> pacientesComFicha() {
        return pacienteService.pacientesComFicha();
    }

    @GetMapping("/sem-ficha")
    public List<Paciente> pacientesSemFicha() {
        return pacienteService.pacientesSemFicha();
    }

    // Ações

    @PatchMapping("/{id}/ativar")
    public Paciente ativar(@PathVariable Long id) {
        return pacienteService.ativar(id);
    }

    @PatchMapping("/{id}/inativar")
    public Paciente inativar(@PathVariable Long id) {
        return pacienteService.inativar(id);
    }

    @PatchMapping("/{id}/suspender")
    public Paciente suspender(@PathVariable Long id) {
        return pacienteService.suspender(id);
    }

    // Fase 2
    // @PostMapping("/{id}/detalhes")
    // public ResponseEntity<Reserva> adicionarDetalhes(@PathVariable Long id,
    // @RequestBody @Valid DetalhesEstadia detalhes) {
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(service.vincularDetalhes(id,
    // detalhes));
    // }

    // Fase 3
    // @PostMapping("/{id}/detalhes")
    // public ResponseEntity<Paciente> adicionarDetalhes(@PathVariable Long id,
    // @RequestBody @Valid FichaMedica detalhes) {
    // // Usa o service que já tem a lógica de vincular
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(service.vincularDetalhes(id,
    // detalhes));
    // }
}
