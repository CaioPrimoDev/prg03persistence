/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;
import br.com.ifba.exception.RegraNegocioException;
import br.com.ifba.infrastructure.util.StringUtil;
import jakarta.persistence.PersistenceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository repo;

    public boolean save(Curso curso) {
        validarCurso(curso);
        try {
            repo.save(curso);
            return true;
        } catch (PersistenceException e) {
            throw new RegraNegocioException("Erro ao salvar curso no banco de dados.", e);
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new RegraNegocioException("ID de curso inválido para exclusão.");
        }

        try {
            repo.deleteById(id);
        } catch (PersistenceException e) {
            throw new RegraNegocioException("Erro ao excluir curso no banco de dados.", e);
        }
    }

    public List<Curso> findAll() {
        try {
            return repo.findAll();
        } catch (PersistenceException e) {
            throw new RegraNegocioException("Erro ao buscar todos os cursos.", e);
        }
    }

    public List<Curso> findByNome(String nome) {
        if (StringUtil.isNullOrEmpty(nome)) {
            throw new RegraNegocioException("Informe um nome válido para a busca.");
        }

        try {
            return repo.findByNomeContainingIgnoreCase(nome);
        } catch (PersistenceException e) {
            throw new RegraNegocioException("Erro ao buscar cursos por nome.", e);
        }
    }

    public Curso findById(Long id) {
        if (id == null || id <= 0) {
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return repo.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Curso não encontrado."));
        } catch (PersistenceException e) {
            throw new RegraNegocioException("Erro ao buscar curso por ID.", e);
        }
    }
    
    // Validação de regra de negócio
    private void validarCurso(Curso curso) {
        if (curso == null) {
            throw new RegraNegocioException("O curso não pode ser nulo.");
        }

        String nome = curso.getNome();
        String professor = curso.getProfessor();
        
        if(curso.getCargaHoraria() == -1) {
            throw new RegraNegocioException("Carga Horario deve ser inteiro e não vazio.");
        }

        if (StringUtil.isNullOrEmpty(nome)) {
            throw new RegraNegocioException("O nome do curso é obrigatório.");
        }

        if (!StringUtil.hasValidLength(nome, 3, 100)) {
            throw new RegraNegocioException("O nome do curso deve ter entre 3 e 100 caracteres.");
        }

        if (curso.getCargaHoraria() <= 0) {
            throw new RegraNegocioException("A carga horária deve ser maior que zero.");
        }

        if (StringUtil.isNullOrEmpty(professor)) {
            throw new RegraNegocioException("O nome do professor é obrigatório.");
        }

        if (!StringUtil.hasValidLength(professor, 3, 100)) {
            throw new RegraNegocioException("O nome do professor deve ter entre 3 e 100 caracteres.");
        }
    }
    
    
}
