/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.curso.view;

import br.com.ifba.curso.controller.CursoIController;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.exception.RegraNegocioException;
import br.com.ifba.util.MensagemUtils;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */

@Component
public class TelaCursosUI extends JFrame {
    
    private final CursoIController controller;
    
    @Autowired
    private ApplicationContext context;

    /**
     * Creates new form TelaCursosUI
     * @param controller
     */
    

    @Autowired
    public TelaCursosUI(CursoIController controller) {
        this.controller = controller;
        initComponents();
        
        scrollCursos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        // Define layout vertical para o painel interno
        painelInternoCursos.setLayout(new BoxLayout(painelInternoCursos, BoxLayout.Y_AXIS));
        painelInternoCursos.setPreferredSize(new Dimension(650, painelInternoCursos.getPreferredSize().height));

        
        // força o painel de linhas a usar alinhamento à esquerda
        painelInternoCursos.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        
        // Altera o Layout para FlowLayout
        painelCabecalho.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        // Redimensiona cada JLabel (dimensões identicas as linhas)
        lblCabecalhoCodigo.setPreferredSize(new Dimension(60, 20));
        lblCabecalhoNome.setPreferredSize(new Dimension(120, 20));
        lblCabecalhoCarga.setPreferredSize(new Dimension(40, 20));
        lblCabecalhoProfessor.setPreferredSize(new Dimension(140, 20));
        lblEditar.setPreferredSize(new Dimension(50, 20));
        lblRemover.setPreferredSize(new Dimension(60, 20));
        
        txtBusca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarCursos();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarCursos();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarCursos();
            }
        });


        
        carregarCursos();
    }
    
    private void filtrarCursos() {
        String termo = txtBusca.getText().trim().toLowerCase();

        List<Curso> cursos;
        if (termo.isEmpty() || termo.equals("procurar...")) {
            cursos = controller.findAll();
        } else {
            cursos = controller.findByNome(termo);
        }

        exibirCursosNaTela(cursos);
    }

    
    public void carregarCursos() {
        List<Curso> cursos = controller.findAll();
        exibirCursosNaTela(cursos);
    }

    
    private void exibirCursosNaTela(List<Curso> cursos) {
        painelInternoCursos.removeAll();

        for (Curso c : cursos) {
            LinhaCursoPanel linha = new LinhaCursoPanel();
            linha.setCurso(c);
            linha.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

            linha.adicionarAcaoEditar(e -> {
                EditarCursoUI dialog = context.getBean(EditarCursoUI.class);
                dialog.abrir(this, c, true);
                carregarCursos();
            });

            linha.adicionarAcaoRemover(e -> {
                if (MensagemUtils.confirmar(this,
                        "Tem certeza que deseja remover o curso?",
                        "Confirmação")) {
                    try {
                        controller.delete(c.getId());
                        MensagemUtils.info(this, "Curso excluído com sucesso.", "Sucesso");
                        carregarCursos();
                    } catch (RegraNegocioException ex) {
                        MensagemUtils.erro(this, ex.getMessage(), "Erro ao excluir curso");
                    } catch (Exception ex) {
                        MensagemUtils.erro(this, "Erro inesperado ao excluir curso.", "Erro");
                        ex.printStackTrace();
                    }
                }
            });

            painelInternoCursos.add(linha);
        }

        painelInternoCursos.revalidate();
        painelInternoCursos.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBotoes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        scrollCursos = new javax.swing.JScrollPane();
        painelInternoCursos = new javax.swing.JPanel();
        painelCabecalho = new javax.swing.JPanel();
        lblCabecalhoCodigo = new javax.swing.JLabel();
        lblCabecalhoNome = new javax.swing.JLabel();
        lblCabecalhoCarga = new javax.swing.JLabel();
        lblCabecalhoProfessor = new javax.swing.JLabel();
        lblEditar = new javax.swing.JLabel();
        lblRemover = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GERENCIADOR DE CURSOS");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ifba/persistence/images/search.png"))); // NOI18N
        painelBotoes.add(jLabel1);

        txtBusca.setPreferredSize(new java.awt.Dimension(155, 25));
        txtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaActionPerformed(evt);
            }
        });
        painelBotoes.add(txtBusca);

        btnAdicionar.setText("Adicionar Curso");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        painelBotoes.add(btnAdicionar);

        scrollCursos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        painelInternoCursos.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout painelInternoCursosLayout = new javax.swing.GroupLayout(painelInternoCursos);
        painelInternoCursos.setLayout(painelInternoCursosLayout);
        painelInternoCursosLayout.setHorizontalGroup(
            painelInternoCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );
        painelInternoCursosLayout.setVerticalGroup(
            painelInternoCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        scrollCursos.setViewportView(painelInternoCursos);

        lblCabecalhoCodigo.setText("Codigo");

        lblCabecalhoNome.setText("Nome");

        lblCabecalhoCarga.setText("Horas");

        lblCabecalhoProfessor.setText("Professor");

        lblEditar.setText("Editar");

        lblRemover.setText("Remover");

        javax.swing.GroupLayout painelCabecalhoLayout = new javax.swing.GroupLayout(painelCabecalho);
        painelCabecalho.setLayout(painelCabecalhoLayout);
        painelCabecalhoLayout.setHorizontalGroup(
            painelCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCabecalhoCodigo)
                .addGap(26, 26, 26)
                .addComponent(lblCabecalhoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCabecalhoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCabecalhoProfessor)
                .addGap(28, 28, 28)
                .addComponent(lblEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRemover)
                .addGap(31, 31, 31))
        );
        painelCabecalhoLayout.setVerticalGroup(
            painelCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCabecalhoCodigo)
                    .addComponent(lblCabecalhoNome)
                    .addComponent(lblCabecalhoCarga)
                    .addComponent(lblCabecalhoProfessor)
                    .addComponent(lblEditar)
                    .addComponent(lblRemover))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollCursos, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        AdicionarCursoUI dialog = context.getBean(AdicionarCursoUI.class);
        dialog.abrir(this, true);
        carregarCursos();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCabecalhoCarga;
    private javax.swing.JLabel lblCabecalhoCodigo;
    private javax.swing.JLabel lblCabecalhoNome;
    private javax.swing.JLabel lblCabecalhoProfessor;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblRemover;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCabecalho;
    private javax.swing.JPanel painelInternoCursos;
    private javax.swing.JScrollPane scrollCursos;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
