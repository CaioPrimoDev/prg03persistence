/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;

/**
 *
 * @author User
 */

@Entity
@Table(name = "curso")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Curso extends PersistenceEntity {

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;

    @Column(name = "professor", nullable = false, length = 100)
    private String professor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        // Mais segura, pois get retorna um objeto Long, e não um tipo primitivo
        return Objects.equals(getId(), curso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
