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

public class RemoverCurso extends JDialog {

    private boolean confirmado = false;

    public RemoverCurso(JFrame parent, Curso curso) {
        super(parent, "Remover Curso", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Mensagem
        JLabel lblMensagem = new JLabel("<html>Deseja remover o seguinte curso?<br><br>" +
                "Código: " + curso.getCodigo() + "<br>" +
                "Nome: " + curso.getNome() + "<br>" +
                "Professor: " + curso.getProfessor() + "</html>");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblMensagem, BorderLayout.CENTER);

        // Botões
        JPanel painelBotoes = new JPanel();
        JButton btnSim = new JButton("Sim");
        JButton btnNao = new JButton("Não");
        painelBotoes.add(btnSim);
        painelBotoes.add(btnNao);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações
        btnSim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmado = true;
                dispose();
            }
        });

        btnNao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // apenas fecha
            }
        });
    }

    public boolean foiConfirmado() {
        return confirmado;
    }
}

