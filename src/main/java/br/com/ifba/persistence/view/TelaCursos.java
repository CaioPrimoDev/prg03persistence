/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.persistence.view;

import br.com.ifba.persistence.dao.CursoDAO;
import br.com.ifba.persistence.entity.Curso;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author User
 */

public class TelaCursos extends JFrame {

    private JPanel painelCursos;
    private CursoDAO cursoDAO = new CursoDAO();

    public TelaCursos() {
        setTitle("Gerenciador de Cursos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        painelCursos = new JPanel();
        painelCursos.setLayout(new BoxLayout(painelCursos, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(painelCursos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdicionar = new JButton("Adicionar Curso");
        JButton btnPesquisar = new JButton("Pesquisar Curso");
        painelTopo.add(btnAdicionar);
        painelTopo.add(btnPesquisar);
        add(painelTopo, BorderLayout.NORTH);

        btnAdicionar.addActionListener(e -> new AdicionarCurso(this, cursoDAO));
        btnPesquisar.addActionListener(e -> new PesquisarCurso(this));

        carregarCursosDoBanco();
        setVisible(true);
    }

    public void carregarCursosDoBanco() {
        painelCursos.removeAll();
        List<Curso> cursos = cursoDAO.listarTodos();

        for (Curso curso : cursos) {
            JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel label = new JLabel("[" + curso.getCodigo() + "] " + curso.getNome()
                    + " - " + curso.getCargaHoraria() + "h - Prof: " + curso.getProfessor());
            JButton btnEditar = new JButton("Editar");
            JButton btnRemover = new JButton("Remover");

            btnRemover.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover o curso?",
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cursoDAO.remover(curso);
                    carregarCursosDoBanco();
                }
            });

            btnEditar.addActionListener(e -> new EditarCurso(this, curso, cursoDAO));

            linha.add(label);
            linha.add(btnEditar);
            linha.add(btnRemover);
            painelCursos.add(linha);
        }

        painelCursos.revalidate();
        painelCursos.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCursos());
    }
}


