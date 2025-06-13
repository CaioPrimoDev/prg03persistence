/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.persistence.view;

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
    private JTextField campoCarga;
    private JTextField campoProfessor;
    private Curso curso;
    private boolean salvo = false;

    public AdicionarCurso(JFrame parent, int proximoCodigo) {
        super(parent, "Adicionar Curso", true);
        setSize(300, 250);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Código:"));
        JTextField campoCodigo = new JTextField(String.valueOf(proximoCodigo));
        campoCodigo.setEditable(false);
        add(campoCodigo);

        add(new JLabel("Nome:"));
        campoNome = new JTextField();
        add(campoNome);

        add(new JLabel("Carga Horária:"));
        campoCarga = new JTextField();
        add(campoCarga);

        add(new JLabel("Professor:"));
        campoProfessor = new JTextField();
        add(campoProfessor);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = campoNome.getText();
                    int carga = Integer.parseInt(campoCarga.getText());
                    String professor = campoProfessor.getText();

                    curso = new Curso(proximoCodigo, nome, carga, professor);
                    salvo = true;
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AdicionarCurso.this,
                        "Carga Horária deve ser numérica.");
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(btnSalvar);
        add(btnCancelar);
    }

    public boolean foiSalvo() {
        return salvo;
    }

    public Curso getCurso() {
        return curso;
    }
}
