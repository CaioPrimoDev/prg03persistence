/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.dao.CursoIDAO;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.exception.RegraNegocioException;
import jakarta.persistence.PersistenceException;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class CursoService implements CursoIService {
    
    private CursoIDAO dao;

    public CursoService() {
        this.dao = new CursoDAO();
    }
    
    public CursoService(CursoIDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean save(Curso curso) {
        if (curso == null) return false;
        
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) return false;
        if (curso.getProfessor() == null || curso.getProfessor().trim().isEmpty()) return false;
        if (curso.getCargaHoraria() <= 0) return false;

        dao.save(curso);  // persiste no banco
        return true;
    }

    @Override
    public boolean update(Curso curso) {
        if (curso == null) return false;
        
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) return false;
        if (curso.getProfessor() == null || curso.getProfessor().trim().isEmpty()) return false;
        if (curso.getCargaHoraria() <= 0) return false;
        
        dao.update(curso);
        return true;
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Curso> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Curso> findByNome(String nome, JFrame parent) {
        try {
            return dao.findByNomeCurso(nome);
        } catch (PersistenceException e) {
            throw new RegraNegocioException("Erro ao buscar cursos no banco de dados.", e);
        }
    }

    @Override
    public Curso findById(Long id) {
        return dao.findById(id);
    }
    
    
    
    
}
