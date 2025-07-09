/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoService;
import br.com.ifba.exception.RegraNegocioException;
import br.com.ifba.util.MensagemUtils;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class CursoController implements CursoIController {
    
    private final CursoService service;
    
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
    public List<Curso> findByNome(String nome, JFrame parent) {
        try {
            return service.findByNome(nome, parent);
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro");
            return Collections.emptyList();  // retorna lista vazia para UI continuar normal
        }   
    }

    @Override
    public Curso findById(Long id) {
        return service.findById(id);
    }
    
  
    
}
