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
import java.util.*;

public class TelaCursos extends JFrame {

    private java.util.List<Curso> listaCursos = new ArrayList<>();
    private JPanel painelPrincipal;
    private JPanel painelTopo;

    public TelaCursos() {
        setTitle("Gerenciador de Cursos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de botões gerais
        painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdicionar = new JButton("Adicionar Curso");
        JButton btnPesquisar = new JButton("Pesquisar Curso");

        painelTopo.add(btnAdicionar);
        painelTopo.add(btnPesquisar);
        add(painelTopo, BorderLayout.NORTH);

        // Painel de cursos
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(painelPrincipal);
        add(scrollPane, BorderLayout.CENTER);

        // Cursos de exemplo
        listaCursos.add(new Curso(101, "Java", 40, "Maria"));
        listaCursos.add(new Curso(102, "Python", 60, "João"));

        // Atualizar tela com cursos
        atualizarTela();

        // Ação para adicionar curso
        btnAdicionar.addActionListener((ActionEvent e) -> {
            AdicionarCurso dialog = new AdicionarCurso(TelaCursos.this);
            dialog.setVisible(true);
            
            if (dialog.foiSalvo()) {
                Curso novo = dialog.getCurso();
                listaCursos.add(novo);
                atualizarTela();
            }
        });



        // Ação para pesquisar curso
        btnPesquisar.addActionListener((ActionEvent e) -> {
            PesquisarCurso dialog = new PesquisarCurso(TelaCursos.this, listaCursos);
            dialog.setVisible(true);
        });


        setVisible(true);
    }

private void atualizarTela() {
    painelPrincipal.removeAll();

    for (int i = 0; i < listaCursos.size(); i++) {
        final int index = i; // cópia final do índice
        Curso curso = listaCursos.get(i);

        JPanel linha = new JPanel();
        linha.setLayout(new FlowLayout());

        final JTextField campoCodigo = new JTextField(String.valueOf(curso.getCodigo()), 5);
        final JTextField campoNome = new JTextField(curso.getNome(), 15);
        final JTextField campoCarga = new JTextField(String.valueOf(curso.getCargaHoraria()), 5);
        final JTextField campoProfessor = new JTextField(curso.getProfessor(), 10);

        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");

        // Botão Editar
        btnEditar.addActionListener((ActionEvent e) -> {
            Curso cursoOriginal = listaCursos.get(index);
            EditarCurso dialog = new EditarCurso(TelaCursos.this, cursoOriginal);
            dialog.setVisible(true);
            
            if (dialog.foiAlterado()) {
                Curso alterado = dialog.getCursoEditado();
                listaCursos.set(index, alterado); // substitui o curso original
                atualizarTela();
            }
        });

        // Botão Remover
        btnRemover.addActionListener((ActionEvent e) -> {
            Curso cursoParaRemover = listaCursos.get(index);
            RemoverCurso dialog = new RemoverCurso(TelaCursos.this, cursoParaRemover);
            dialog.setVisible(true);
            
            if (dialog.foiConfirmado()) {
                listaCursos.remove(index);
                atualizarTela();
            }
        });

        linha.add(new JLabel("Código:"));
        linha.add(campoCodigo);
        linha.add(new JLabel("Nome:"));
        linha.add(campoNome);
        linha.add(new JLabel("Carga:"));
        linha.add(campoCarga);
        linha.add(new JLabel("Professor:"));
        linha.add(campoProfessor);
        linha.add(btnEditar);
        linha.add(btnRemover);

        painelPrincipal.add(linha);
    }

    painelPrincipal.revalidate();
    painelPrincipal.repaint();
}


    public static void main(String[] args) {
        new TelaCursos();
    }
}

