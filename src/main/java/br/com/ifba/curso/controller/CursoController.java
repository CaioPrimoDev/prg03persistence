/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoService;
import br.com.ifba.exception.RegraNegocioException;
import br.com.ifba.util.MensagemUtils;
import java.awt.Window;
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
    public boolean save(Window parent, Curso curso) {
        try {
            return service.save(curso);
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro ao salvar curso");
            return false;
        }
    }

    @Override
    public boolean update(Window parent, Curso curso) {
        try {
            return service.update(curso);
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro ao atualizar curso");
            return false;
        }
    }

    @Override
    public void delete(JFrame parent, Long id) {
        try {
            service.delete(id);
            MensagemUtils.info(parent, "Curso excluído com sucesso.", "Sucesso");
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro ao excluir curso");
        }
    }

    @Override
    public List<Curso> findAll(JFrame parent) {
        try {
            return service.findAll();
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro ao buscar cursos");
            return Collections.emptyList();
        }
    }

    @Override
    public List<Curso> findByNome(String nome, JFrame parent) {
        try {
            return service.findByNome(nome);
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro ao buscar por nome");
            return Collections.emptyList();
        }   
    }

    @Override
    public Curso findById(JFrame parent, Long id) {
        try {
            return service.findById(id);
        } catch (RegraNegocioException e) {
            MensagemUtils.erro(parent, e.getMessage(), "Erro ao buscar por ID");
            return null;
        }
    }
    
  
    
}
