/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import br.com.ifba.curso.service.CursoService;
import java.util.List;

/**
 *
 * @author User
 */
public class CursoController implements CursoIController {
    
    private final CursoIService service;
    
    public CursoController() {
        this.service = new CursoService();
    }

    @Override
    public boolean save(Curso curso) {
        return service.save(curso);
    }

    @Override
    public boolean update(Curso curso) {
        return service.update(curso);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Curso> findAll() {
        return service.findAll();
    }

    @Override
    public List<Curso> findByNome(String nome) {
        return service.findByNome(nome);  
    }

    @Override
    public Curso findById(Long id) {
        return service.findById(id);
    }
    
  
    
}
