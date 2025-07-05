/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.dao.CursoIDAO;
import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 *
 * @author User
 */
public class CursoService {
    
    private CursoIDAO dao;

    public CursoService() {
        this.dao = new CursoDAO();
    }
    
    public CursoService(CursoIDAO dao) {
        this.dao = dao;
    }
    
    public boolean adicionarCurso(Curso curso) {
        
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            return false;
        }

        if (curso.getProfessor() == null || curso.getProfessor().trim().isEmpty()) {
            return false;
        }

        if (curso.getCargaHoraria() <= 0) {
            return false;
        }

        dao.save(curso);  // persiste no banco
        return true;
    }

    public boolean atualizarCurso(Curso curso) {
        if (curso == null) return false;
        
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) return false;
        if (curso.getProfessor() == null || curso.getProfessor().trim().isEmpty()) return false;
        if (curso.getCargaHoraria() <= 0) return false;
        
        dao.update(curso);
        return true;
    }

    public void removerCurso(int id) {
        dao.delete(id);  
    }

    public List<Curso> listarTodos() {
        return dao.findAll();
    }

    public List<Curso> buscarPorNomeCurso(String nome) {
        return dao.findByNomeCurso(nome);
    }

    public Curso buscarPorId(int id) {
        return dao.findById(id);
    }   
    
    
}
