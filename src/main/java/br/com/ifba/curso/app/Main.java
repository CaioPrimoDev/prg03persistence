/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.app;
import br.com.ifba.curso.view.TelaCursosUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaCursosUI().setVisible(true); // ou setVisible(true), dependendo da sua classe
        });
    }
}

