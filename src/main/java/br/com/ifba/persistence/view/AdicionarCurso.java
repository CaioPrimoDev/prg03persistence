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

public class AdicionarCurso extends JDialog {

    private JTextField campoNome;
    private JTextField campoCargaHoraria;
    private JTextField campoProfessor;
    private JButton btnSalvar;
    private CursoDAO cursoDAO;
    private TelaCursos telaPrincipal;

    public AdicionarCurso(JFrame parent, CursoDAO cursoDAO) {
        super(parent, "Adicionar Curso", true);
        this.cursoDAO = cursoDAO;
        this.telaPrincipal = (TelaCursos) parent;

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Nome:"));
        campoNome = new JTextField();
        add(campoNome);

        add(new JLabel("Carga Horária:"));
        campoCargaHoraria = new JTextField();
        add(campoCargaHoraria);

        add(new JLabel("Professor:"));
        campoProfessor = new JTextField();
        add(campoProfessor);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarCurso());
        add(btnSalvar);

        setVisible(true);
    }

    private void salvarCurso() {
        try {
            String nome = campoNome.getText();
            int carga = Integer.parseInt(campoCargaHoraria.getText());
            String prof = campoProfessor.getText();

            Curso curso = new Curso(nome, carga, prof);
            cursoDAO.salvar(curso);
            telaPrincipal.carregarCursosDoBanco();
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Carga horária inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
