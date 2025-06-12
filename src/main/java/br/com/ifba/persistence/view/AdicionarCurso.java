/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.persistence.view;

/**
 *
 * @author User
 */
import br.com.ifba.persistence.entity.Curso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdicionarCurso extends JDialog {

    private JTextField campoCodigo;
    private JTextField campoNome;
    private JTextField campoCarga;
    private JTextField campoProfessor;
    private boolean cursoSalvo = false;
    private Curso novoCurso;

    public AdicionarCurso(JFrame parent) {
        super(parent, "Adicionar Curso", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Campos
        add(new JLabel("Código:"));
        campoCodigo = new JTextField();
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

                    novoCurso = new Curso(codigo, nome, carga, professor);
                    cursoSalvo = true;
                    dispose(); // fecha o diálogo

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AdicionarCurso.this,
                        "Código e Carga Horária devem ser números inteiros.");
                }
            }
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // fecha o diálogo sem salvar
            }
        });
    }

    public boolean foiSalvo() {
        return cursoSalvo;
    }

    public Curso getCurso() {
        return novoCurso;
    }
}

