package com.luizsantos.clinicamedica.entity.ficha;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luizsantos.clinicamedica.entity.paciente.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

// Imports para a Fase 2
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_FICHA_MEDICA")
public class FichaMedica {

    @Id
    // Retirar para Fase 3
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sanguineo")
    private TipoSanguineo tipoSanguineo;

    @Size(max = 500)
    @Column(name = "alergias")
    private String alergias;

    @Size(max = 500)
    @Column(name = "medicamentos_em_uso")
    private String medicamentosUso;

    @Size(max = 1000)
    @Column(name = "historico_de_doencas")
    private String historicoDoencas;

    @Size(max = 1000)
    @Column(name = "observacoes_clinicas")
    private String observacoesClinicas;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // Fase 1 - sem referência bidirecional

    // Fase 2
    // @OneToOne(mappedBy = "fichaMedica")
    // @JsonBackReference
    // private Paciente paciente;

    // Fase 3
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Paciente paciente;

    public FichaMedica() {
    }

    public FichaMedica(Long id, @NotNull TipoSanguineo tipoSanguineo, @Size(max = 500) String alergias,
                       @Size(max = 500) String medicamentosUso, @Size(max = 1000) String historicoDoencas,
                       @Size(max = 1000) String observacoesClinicas, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.tipoSanguineo = tipoSanguineo;
        this.alergias = alergias;
        this.medicamentosUso = medicamentosUso;
        this.historicoDoencas = historicoDoencas;
        this.observacoesClinicas = observacoesClinicas;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getMedicamentosUso() {
        return medicamentosUso;
    }

    public void setMedicamentosUso(String medicamentosUso) {
        this.medicamentosUso = medicamentosUso;
    }

    public String getHistoricoDoencas() {
        return historicoDoencas;
    }

    public void setHistoricoDoencas(String historicoDoencas) {
        this.historicoDoencas = historicoDoencas;
    }

    public String getObservacoesClinicas() {
        return observacoesClinicas;
    }

    public void setObservacoesClinicas(String observacoesClinicas) {
        this.observacoesClinicas = observacoesClinicas;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    // Fase 2
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, tipoSanguineo, alergias, medicamentosUso, historicoDoencas, observacoesClinicas,
                dataAtualizacao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        FichaMedica other = (FichaMedica) obj;
        return java.util.Objects.equals(id, other.id) && tipoSanguineo == other.tipoSanguineo
                && java.util.Objects.equals(alergias, other.alergias)
                && java.util.Objects.equals(medicamentosUso, other.medicamentosUso)
                && java.util.Objects.equals(historicoDoencas, other.historicoDoencas)
                && java.util.Objects.equals(observacoesClinicas, other.observacoesClinicas)
                && java.util.Objects.equals(dataAtualizacao, other.dataAtualizacao)
                && java.util.Objects.equals(paciente, other.paciente);
    }

    @Override
    public String toString() {
        return "FichaMedica{" +
                "id=" + id +
                ", tipoSanguineo=" + tipoSanguineo +
                ", alergias='" + alergias + '\'' +
                ", medicamentosUso='" + medicamentosUso + '\'' +
                ", historicoDoencas='" + historicoDoencas + '\'' +
                ", observacoesClinicas='" + observacoesClinicas + '\'' +
                ", dataAtualizacao=" + dataAtualizacao +
                ", paciente=" + paciente +
                '}';
    }
}
