/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDAO<T, ID extends Serializable>
        implements GenericIDAO<T, ID>{
    
    private EntityManagerFactory EMF = Persistence.createEntityManagerFactory("prg03persistence");
    
    protected Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected EntityManager em() {
        return EMF.createEntityManager();
    }

    @Override
    public void save(T entity) {
        EntityManager em = em();
        try {
          em.getTransaction().begin();
          em.persist(entity);
          em.getTransaction().commit();
        } catch (Exception e) {       
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // desfaz alterações
            }
            throw e;
        }finally {
          em.close();
        }
    }

    @Override
    public T update(T entity) {
        EntityManager em = em();
        try {
            em.getTransaction().begin();
            T merged = em.merge(entity);
            em.getTransaction().commit();
            return merged;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(ID id) {
        EntityManager em = em();
        try {
            em.getTransaction().begin();
            T obj = em.find(entityClass, id);
            if (obj != null) em.remove(obj);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public T findById(ID id) {
        EntityManager em = em();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override   
    public List<T> findAll() {
        EntityManager em = em();
        try {
            CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
            cq.from(entityClass); // define a raiz da query
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
    
}
