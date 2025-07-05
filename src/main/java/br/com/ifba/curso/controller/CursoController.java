/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoService;
import java.util.List;

/**
 *
 * @author User
 */
public class CursoController {
    
    private final CursoService service;
    
    public CursoController() {
        this.service = new CursoService();
    }
    
    public boolean adicionarCurso(Curso curso) {
        return service.adicionarCurso(curso);
    }

    public boolean atualizarCurso(Curso curso) {
        return service.atualizarCurso(curso);
    }

    public void removerCurso(int id) {
        service.removerCurso(id);
    }

    public List<Curso> listarCursos() {
        return service.listarTodos();
    }

    public List<Curso> buscarPorNomeCurso(String nome) {
        return service.buscarPorNomeCurso(nome);
    }

    public Curso buscarPorId(int id) {
        return service.buscarPorId(id);
    }   
    
}
