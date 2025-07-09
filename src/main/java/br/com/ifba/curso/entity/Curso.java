/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;

/**
 *
 * @author User
 */

@Entity
@Table(name = "curso")
public class Curso extends PersistenceEntity {

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;

    @Column(name = "professor", nullable = false, length = 100)
    private String professor;

    public Curso() {}

    public Curso(String nome, int cargaHoraria, String professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    //public Long getCodigo() { return getId(); }
    public String getNome() { return nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public String getProfessor() { return professor; }

    //public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public void setProfessor(String professor) { this.professor = professor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return getId() == curso.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Curso{" +
               "codigo=" + getId() +
               ", nome='" + nome + '\'' +
               ", cargaHoraria=" + cargaHoraria +
               ", professor='" + professor + '\'' +
               '}';
    }
}
