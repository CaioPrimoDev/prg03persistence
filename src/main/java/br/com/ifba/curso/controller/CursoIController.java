/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public interface CursoIController {
    
    boolean save(Curso curso);
    boolean update(Curso curso);
    void delete (Long id);
    List<Curso> findAll();
    List<Curso> findByNome(String nome, JFrame parent);
    Curso findById(Long id);
}
