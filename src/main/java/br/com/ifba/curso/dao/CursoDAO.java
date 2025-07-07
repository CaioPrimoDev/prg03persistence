/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDAO;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author User
 */
public class CursoDAO extends GenericDAO<Curso, Integer> 
            implements CursoIDAO {

    public CursoDAO() {
        super(Curso.class);
    }

    @Override
    public List<Curso> findByNomeCurso(String termo) {
        try (jakarta.persistence.EntityManager em = em()) {
          TypedQuery<Curso> q = em.createQuery (
          "SELECT c FROM Curso c WHERE c.nome LIKE :nome", Curso.class);
          q.setParameter("nome", "%" + termo.trim() + "%");
          return q.getResultList();
        }
    } 
    

    
}
