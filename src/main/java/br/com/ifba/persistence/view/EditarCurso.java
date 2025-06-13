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

public class EditarCurso extends JDialog {

    private JTextField campoCodigo;
    private JTextField campoNome;
    private JTextField campoCarga;
    private JTextField campoProfessor;
    private boolean alterado = false;
    private Curso cursoEditado;

    public EditarCurso(JFrame parent, Curso cursoOriginal) {
        super(parent, "Editar Curso", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Campos preenchidos com dados do curso
        add(new JLabel("Código:"));
        campoCodigo = new JTextField(String.valueOf(cursoOriginal.getCodigo()));
        campoCodigo.setEditable(false);
        add(campoCodigo);

        add(new JLabel("Nome:"));
        campoNome = new JTextField(cursoOriginal.getNome());
        add(campoNome);

        add(new JLabel("Carga Horária:"));
        campoCarga = new JTextField(String.valueOf(cursoOriginal.getCargaHoraria()));
        add(campoCarga);

        add(new JLabel("Professor:"));
        campoProfessor = new JTextField(cursoOriginal.getProfessor());
        add(campoProfessor);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnSalvar);
        add(btnCancelar);

        // Ação do botão Salvar
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(campoCodigo.getText());
                    String nome = campoNome.getText();
                    int carga = Integer.parseInt(campoCarga.getText());
                    String professor = campoProfessor.getText();

                    cursoEditado = new Curso(codigo, nome, carga, professor);
                    alterado = true;
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditarCurso.this,
                        "Código e Carga Horária devem ser números inteiros.");
                }
            }
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // fecha sem alterar
            }
        });
    }

    public boolean foiAlterado() {
        return alterado;
    }

    public Curso getCursoEditado() {
        return cursoEditado;
    }
}

