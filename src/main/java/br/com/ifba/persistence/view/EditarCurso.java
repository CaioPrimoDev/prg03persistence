/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.persistence.view;

import br.com.ifba.persistence.dao.CursoDAO;
import br.com.ifba.persistence.entity.Curso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author User
 */

public class EditarCurso extends JDialog {

    private JTextField campoNome;
    private JTextField campoCargaHoraria;
    private JTextField campoProfessor;
    private JButton btnSalvar;
    private Curso curso;
    private CursoDAO cursoDAO;
    private TelaCursos telaPrincipal;

    public EditarCurso(JFrame parent, Curso curso, CursoDAO cursoDAO) {
        super(parent, "Editar Curso", true);
        this.curso = curso;
        this.cursoDAO = cursoDAO;
        this.telaPrincipal = (TelaCursos) parent;

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Nome:"));
        campoNome = new JTextField(curso.getNome());
        add(campoNome);

        add(new JLabel("Carga Horária:"));
        campoCargaHoraria = new JTextField(String.valueOf(curso.getCargaHoraria()));
        add(campoCargaHoraria);

        add(new JLabel("Professor:"));
        campoProfessor = new JTextField(curso.getProfessor());
        add(campoProfessor);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        add(btnSalvar);

        setVisible(true);
    }

    private void salvarAlteracoes() {
        try {
            curso.setNome(campoNome.getText());
            curso.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText()));
            curso.setProfessor(campoProfessor.getText());

            cursoDAO.atualizar(curso);
            telaPrincipal.carregarCursosDoBanco();
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Carga horária inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
} 



