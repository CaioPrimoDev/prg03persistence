/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.persistence.app;
import javax.swing.SwingUtilities;
import br.com.ifba.persistence.view.TelaCursos;

/**
 *
 * @author User
 */


public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja criada na thread correta (EDT)
        SwingUtilities.invokeLater(() -> {
            new TelaCursos(); // Cria e exibe a janela principal
        });
    }
}
