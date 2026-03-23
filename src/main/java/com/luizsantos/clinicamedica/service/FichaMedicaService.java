package com.luizsantos.clinicamedica.service;

import com.luizsantos.clinicamedica.entity.ficha.FichaMedica;
import com.luizsantos.clinicamedica.entity.ficha.TipoSanguineo;
import com.luizsantos.clinicamedica.entity.paciente.Paciente;
import com.luizsantos.clinicamedica.entity.paciente.Status;
import com.luizsantos.clinicamedica.repository.FichaMedicaRepository;
import com.luizsantos.clinicamedica.repository.PacienteRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FichaMedicaService {

    private final FichaMedicaRepository repository;
    private final PacienteRepository pacienteRepository;

    public FichaMedicaService(FichaMedicaRepository repository, PacienteRepository pacienteRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
    }

    // CREATE - cria e vincula ficha a paciente existente
    @Transactional
    public FichaMedica criarFichaParaPaciente(Paciente paciente, FichaMedica ficha) {
        // Verfica se já existe ficha
        if (paciente.getFichaMedica() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Paciente já possui ficha médica");
        }

        // Valida o status do paciente
        if (paciente.getStatusPaciente() != Status.ATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ficha só pode ser criada para paciente ativo");
        }

        ficha.setDataAtualizacao(LocalDateTime.now());

        // Fase 2 e 3
        ficha.setPaciente(paciente);
        paciente.setFichaMedica(ficha);

        pacienteRepository.save(paciente);

        return paciente.getFichaMedica();
    }

    @Transactional
    public FichaMedica atualizarFicha(Long pacienteId, FichaMedica fichaAtualizada) {
        // Busca o paciente para encontrar a ficha dele
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        FichaMedica fichaExistente = paciente.getFichaMedica();
        if (fichaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ficha médica não encontrada para este paciente");
        }

        // Ficha não pode ser atualizada para paciente inativo
        if (paciente.getStatusPaciente() == Status.INATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é permitido atualizar ficha de paciente inativo");
        }

        // Atualiza apenas os campos permitidos
        fichaExistente.setTipoSanguineo(fichaAtualizada.getTipoSanguineo());
        fichaExistente.setAlergias(fichaAtualizada.getAlergias());
        fichaExistente.setMedicamentosUso(fichaAtualizada.getMedicamentosUso());
        fichaExistente.setHistoricoDoencas(fichaAtualizada.getHistoricoDoencas());
        fichaExistente.setObservacoesClinicas(fichaAtualizada.getObservacoesClinicas());

        // Data de atualização automática
        fichaExistente.setDataAtualizacao(LocalDateTime.now());

        return repository.save(fichaExistente);
    }

    // DELETA A FICHA
    @Transactional
    public void deletarFicha(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
        FichaMedica ficha = paciente.getFichaMedica();
        if (ficha == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ficha médica não encontrada para este paciente");
        }
        paciente.setFichaMedica(null);
        pacienteRepository.save(paciente);
        repository.delete(ficha);
    }

    // FILTRA PACIENTES POR TIPO SANGUÍNEO
    public List<Paciente> findByTipoSanguineo(String tipo) {
        String tipoSanguineo;
        try {
            tipoSanguineo = TipoSanguineo.valueOf(tipo.toUpperCase()).name();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo sanguíneo inválido: " + tipo);
        }
        return pacienteRepository.findByTipoSanguineo(tipoSanguineo);
    }
}
