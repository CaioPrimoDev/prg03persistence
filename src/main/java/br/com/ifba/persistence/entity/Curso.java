/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.ifba.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;

/**
 *
 * @author User
 */

@Entity
public class Curso {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Só quando criar as operações
    private int codigo;
    private String nome;
    private int cargaHoraria;
    private String professor;
    
    // Construtor padrão (obrigatório para JPA)
    public Curso() {}

    public Curso(int codigo, String nome, int cargaHoraria, String professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    // Getters e Setters
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public String getProfessor() { return professor; }

    public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public void setProfessor(String professor) { this.professor = professor; }
}
