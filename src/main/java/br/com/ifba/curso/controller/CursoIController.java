/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.awt.Window;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public interface CursoIController {
    
    boolean save(Window parent, Curso curso);
    boolean update(Window parent, Curso curso);
    void delete (JFrame parent, Long id);
    List<Curso> findAll(JFrame parent);
    List<Curso> findByNome(String nome, JFrame parent);
    Curso findById(JFrame parent, Long id);
}
