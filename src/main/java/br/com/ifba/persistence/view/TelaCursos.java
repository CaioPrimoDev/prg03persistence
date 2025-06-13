/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.persistence.view;

import br.com.ifba.persistence.entity.Curso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

/**
 *
 * @author User
 */

public class TelaCursos extends JFrame {

    private java.util.List<Curso> listaCursos = new ArrayList<>();
    private JPanel painelPrincipal;
    private JPanel painelTopo;

    public TelaCursos() {
        setTitle("Gerenciador de Cursos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdicionar = new JButton("Adicionar Curso");
        JButton btnPesquisar = new JButton("Pesquisar Curso");

        painelTopo.add(btnAdicionar);
        painelTopo.add(btnPesquisar);
        add(painelTopo, BorderLayout.NORTH);        

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(painelPrincipal);
        add(scrollPane, BorderLayout.CENTER);

        listaCursos.add(new Curso(101, "Java", 40, "Maria"));
        listaCursos.add(new Curso(102, "Python", 60, "João"));
        listaCursos.add(new Curso(103, "Sistemas Operacionais", 60, "Cacio"));

        atualizarTela();

        // Botão Adicionar
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int proximoCodigo = 1;

                if (!listaCursos.isEmpty()) {
                    int maiorCodigo = 0;
                    for (Curso c : listaCursos) {
                        if (c.getCodigo() > maiorCodigo) {
                            maiorCodigo = c.getCodigo();
                        }
                    }
                    proximoCodigo = maiorCodigo + 1;
                }

                AdicionarCurso dialog = new AdicionarCurso(TelaCursos.this, proximoCodigo);
                dialog.setVisible(true);

                if (dialog.foiSalvo()) {
                    Curso novo = dialog.getCurso();
                    listaCursos.add(novo);
                    atualizarTela();
                }
            }
        });

        // Botão Pesquisar
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PesquisarCurso dialog = new PesquisarCurso(TelaCursos.this, listaCursos);
                dialog.setVisible(true);
            }
        });

        setVisible(true);
    }

    private void atualizarTela() {
        painelPrincipal.removeAll();

        JPanel cabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        cabecalho.setAlignmentX(Component.LEFT_ALIGNMENT);
        cabecalho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setPreferredSize(new Dimension(60, 20));

        JLabel lblNome = new JLabel("Nome");
        lblNome.setPreferredSize(new Dimension(150, 20));

        JLabel lblCarga = new JLabel("Carga Horária");
        lblCarga.setPreferredSize(new Dimension(90, 20));

        JLabel lblProfessor = new JLabel("Professor");
        lblProfessor.setPreferredSize(new Dimension(100, 20));

        JLabel lblRemover = new JLabel("    Remover");
        lblRemover.setPreferredSize(new Dimension(80, 20));

        JLabel lblEditar = new JLabel("      Editar");
        lblEditar.setPreferredSize(new Dimension(80, 20));

        cabecalho.add(lblCodigo);
        cabecalho.add(lblNome);
        cabecalho.add(lblCarga);
        cabecalho.add(lblProfessor);
        cabecalho.add(lblRemover);
        cabecalho.add(lblEditar);

        painelPrincipal.add(cabecalho);

        for (int i = 0; i < listaCursos.size(); i++) {
            final int index = i;
            Curso curso = listaCursos.get(i);

            JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
            linha.setAlignmentX(Component.LEFT_ALIGNMENT);
            linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            linha.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

            JTextField campoCodigo = new JTextField(String.valueOf(curso.getCodigo()));
            campoCodigo.setPreferredSize(new Dimension(60, 20));
            campoCodigo.setEditable(false);

            JTextField campoNome = new JTextField(curso.getNome());
            campoNome.setPreferredSize(new Dimension(150, 20));
            campoNome.setEditable(false);

            JTextField campoCarga = new JTextField(String.valueOf(curso.getCargaHoraria()));
            campoCarga.setPreferredSize(new Dimension(90, 20));
            campoCarga.setEditable(false);

            JTextField campoProfessor = new JTextField(curso.getProfessor());
            campoProfessor.setPreferredSize(new Dimension(100, 20));
            campoProfessor.setEditable(false);

            JButton btnRemover = criarBotaoComIcone("/br/com/ifba/persistence/images/removeIcon.png", "Remover");
            btnRemover.setPreferredSize(new Dimension(80, 20));

            JButton btnEditar = criarBotaoComIcone("/br/com/ifba/persistence/images/editIcon.png", "Editar");
            btnEditar.setPreferredSize(new Dimension(80, 20));

            btnEditar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Curso cursoOriginal = listaCursos.get(index);
                    EditarCurso dialog = new EditarCurso(TelaCursos.this, cursoOriginal);
                    dialog.setVisible(true);

                    if (dialog.foiAlterado()) {
                        Curso alterado = dialog.getCursoEditado();
                        listaCursos.set(index, alterado);
                        atualizarTela();
                    }
                }
            });

            btnRemover.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Curso cursoParaRemover = listaCursos.get(index);
                    RemoverCurso dialog = new RemoverCurso(TelaCursos.this, cursoParaRemover);
                    dialog.setVisible(true);

                    if (dialog.foiConfirmado()) {
                        listaCursos.remove(index);
                        atualizarTela();
                    }
                }
            });

            linha.add(campoCodigo);
            linha.add(campoNome);
            linha.add(campoCarga);
            linha.add(campoProfessor);
            linha.add(btnRemover);
            linha.add(btnEditar);

            painelPrincipal.add(linha);
        }

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    private JButton criarBotaoComIcone(String caminhoIcone, String tooltip) {
        JButton botao = new JButton();
        botao.setToolTipText(tooltip);

        URL imageUrl = getClass().getResource(caminhoIcone);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            botao.setIcon(icon);
        } else {
            System.err.println("⚠️ Imagem não encontrada: " + caminhoIcone);
            botao.setText(tooltip);
        }

        return botao;
    }
}


