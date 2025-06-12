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
import java.util.List;
import java.util.ArrayList;

public class PesquisarCurso extends JDialog {

    private JTextField campoBusca;
    private JTextArea areaResultados;
    private List<Curso> cursos;

    public PesquisarCurso(JFrame parent, List<Curso> cursosDisponiveis) {
        super(parent, "Pesquisar Curso", true);
        this.cursos = cursosDisponiveis;

        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Campo de busca
        JPanel painelBusca = new JPanel();
        painelBusca.setLayout(new FlowLayout());

        painelBusca.add(new JLabel("Digite o nome ou código:"));
        campoBusca = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        painelBusca.add(campoBusca);
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        // Área de resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);
        add(scroll, BorderLayout.CENTER);

        // Botão fechar
        JButton btnFechar = new JButton("Fechar");
        JPanel painelFechar = new JPanel();
        painelFechar.add(btnFechar);
        add(painelFechar, BorderLayout.SOUTH);

        // Ação do botão Buscar
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCurso();
            }
        });

        // Ação do botão Fechar
        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void buscarCurso() {
        String termo = campoBusca.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            areaResultados.setText("Digite um nome ou código para buscar.");
            return;
        }

        List<Curso> encontrados = new ArrayList<>();

        for (Curso c : cursos) {
            if (c.getNome().toLowerCase().contains(termo)) {
                encontrados.add(c);
            } else {
                try {
                    int codigoBusca = Integer.parseInt(termo);
                    if (c.getCodigo() == codigoBusca) {
                        encontrados.add(c);
                    }
                } catch (NumberFormatException ex) {
                    // ignorar se não for número
                }
            }
        }

        if (encontrados.isEmpty()) {
            areaResultados.setText("Nenhum curso encontrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Curso c : encontrados) {
                sb.append("Código: ").append(c.getCodigo()).append("\n")
                  .append("Nome: ").append(c.getNome()).append("\n")
                  .append("Carga Horária: ").append(c.getCargaHoraria()).append("\n")
                  .append("Professor: ").append(c.getProfessor()).append("\n")
                  .append("-----\n");
            }
            areaResultados.setText(sb.toString());
        }
    }
}
