package com.luizsantos.clinicamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luizsantos.clinicamedica.entity.paciente.Paciente;
import com.luizsantos.clinicamedica.entity.paciente.Status;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Carrega paciente com ficha em um único SELECT — evita query extra
    @Query("SELECT p FROM Paciente p LEFT JOIN FETCH p.fichaMedica WHERE p.id = :id")
    Optional<Paciente> findByIdComFicha(@Param("id") Long id);

    // Busca por CPF
    Optional<Paciente> findByCpf(String cpf);

    // Busca por nome ou e-mail
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :t, '%')) OR LOWER(p.email) LIKE LOWER(CONCAT('%', :t, '%'))")
    List<Paciente> buscarPorTermo(@Param("t") String termo);

    // Pacientes por tipo sanguíneo
    @Query("SELECT p FROM Paciente p JOIN p.fichaMedica f WHERE f.tipoSanguineo = :tipo")
    List<Paciente> findByTipoSanguineo(@Param("tipo") String tipo);

    // Busca por email
    Optional<Paciente> findByEmail(String email);

    // Busca por status
    Optional<List<Paciente>> findByStatusPaciente(Status status);

    // Busca pacientes onde a ficha não é nula
    List<Paciente> findByFichaMedicaIsNotNull();

    // Busca pacientes onde a ficha é nula
    List<Paciente> findByFichaMedicaIsNull();
}
