package com.luizsantos.clinicamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizsantos.clinicamedica.entity.ficha.FichaMedica;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {}
