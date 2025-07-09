/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericIDAO;
import java.util.List;

/**
 *
 * @author User
 */
public interface CursoIDAO extends GenericIDAO<Curso, Long> {
    List<Curso> findByNomeCurso(String nomeCurso);      
}
