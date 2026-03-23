package com.luizsantos.clinicamedica.entity.paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luizsantos.clinicamedica.entity.ficha.FichaMedica;

@Entity
@Table(name = "TB_PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 120)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(min = 11, max = 14)
    @Column(name = "cpf", unique = true)
    private String cpf;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Size(max = 20)
    @Column(name = "telefone")
    private String telefone;

    @NotNull
    @Past
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_paciente")
    private Status statusPaciente = Status.ATIVO;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    // Fase 1
    // @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "ficha_id", unique = true)
    // private FichaMedica fichaMedica;

    // Fase 2
    // @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch =
    // FetchType.LAZY)
    // @JoinColumn(name = "ficha_medica", unique = true)
    // @JsonManagedReference
    // private FichaMedica fichaMedica;

    // Fase 3
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private FichaMedica fichaMedica;

    // Contrutor Vazio
    public Paciente() {
    }

    public Paciente(Long id, @NotBlank @Size(min = 3, max = 120) String nome,
                    @NotBlank @Size(min = 11, max = 14) String cpf, @NotBlank @Email String email,
                    @Size(max = 20) String telefone,
                    @NotNull @Past LocalDate dataNascimento, @NotNull Sexo sexo, @NotNull Status statusPaciente,
                    LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.statusPaciente = statusPaciente;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Status getStatusPaciente() {
        return statusPaciente;
    }

    public void setStatusPaciente(Status statusPaciente) {
        this.statusPaciente = statusPaciente;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public FichaMedica getFichaMedica() {
        return fichaMedica;
    }

    public void setFichaMedica(FichaMedica fichaMedica) {
        this.fichaMedica = fichaMedica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id)
                && Objects.equals(nome, paciente.nome)
                && Objects.equals(cpf, paciente.cpf)
                && Objects.equals(email, paciente.email)
                && Objects.equals(telefone, paciente.telefone)
                && Objects.equals(dataNascimento, paciente.dataNascimento)
                && Objects.equals(sexo, paciente.sexo)
                && Objects.equals(statusPaciente, paciente.statusPaciente)
                && Objects.equals(dataCadastro, paciente.dataCadastro)
                && Objects.equals(fichaMedica, paciente.fichaMedica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, email, telefone, dataNascimento, sexo, statusPaciente, dataCadastro,
                fichaMedica);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", statusPaciente=" + statusPaciente +
                ", dataCadastro=" + dataCadastro +
                ", fichaMedica=" + fichaMedica +
                '}';
    }
}
