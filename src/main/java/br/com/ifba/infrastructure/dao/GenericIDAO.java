/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 * @param <ID>
 */
public interface GenericIDAO<T, ID extends Serializable> {
    void save(T entity);        // Create
    T update(T entity);         // Update
    void delete(ID id);         // Delete
    T findById(ID id);          // Read (um)
    List<T> findAll();          // Read (todos)   
}
