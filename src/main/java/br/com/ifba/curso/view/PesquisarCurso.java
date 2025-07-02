/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view;

import br.com.ifba.curso.infrastructure.entity.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author User
 */

public class PesquisarCurso extends JDialog {

    private JTextField campoPesquisa;
    private JTextArea resultadoArea;
    private JButton btnPesquisar;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("prg03persistence");

    public PesquisarCurso(JFrame parent) {
        super(parent, "Pesquisar Curso", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new BorderLayout(5, 5));
        campoPesquisa = new JTextField();
        btnPesquisar = new JButton("Pesquisar");
        painelTopo.add(new JLabel("Nome do curso:"), BorderLayout.WEST);
        painelTopo.add(campoPesquisa, BorderLayout.CENTER);
        painelTopo.add(btnPesquisar, BorderLayout.EAST);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        add(painelTopo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnPesquisar.addActionListener(e -> buscarCursos());
        setVisible(true);
    }

    private void buscarCursos() {
        String termo = campoPesquisa.getText();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Curso> query = em.createQuery(
                "SELECT c FROM Curso c WHERE c.nome LIKE :nome", Curso.class);
            query.setParameter("nome", "%" + termo + "%");
            List<Curso> resultados = query.getResultList();

            resultadoArea.setText("");
            if (resultados.isEmpty()) {
                resultadoArea.setText("Nenhum curso encontrado.");
            } else {
                for (Curso c : resultados) {
                    resultadoArea.append("[" + c.getCodigo() + "] " + c.getNome()
                            + " - " + c.getCargaHoraria() + "h - Prof: " + c.getProfessor() + "\n");
                }
            }
        } finally {
            em.close();
        }
    }
}

