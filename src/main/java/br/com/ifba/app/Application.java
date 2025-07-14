/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.app;
import br.com.ifba.curso.controller.CursoController;
import br.com.ifba.curso.controller.CursoIController;
import br.com.ifba.curso.view.TelaCursosUI;
import java.awt.EventQueue;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author User
 */

@SpringBootApplication
@EnableJpaRepositories("br.com.ifba")  // ou mais específico
@EntityScan("br.com.ifba")
@ComponentScan("br.com.ifba")
public class Application {
    public static void main(String[] args) {
        // Iniciar contexto Spring Boot
        ConfigurableApplicationContext context = 
            new SpringApplicationBuilder(Application.class)
                .headless(false)                // Permite usar Swing (desabilita headless)
                .web(WebApplicationType.NONE)    // Sem servidor Web (aplicação desktop)
                .run(args);
        
        CursoIController controller = context.getBean(CursoController.class);
        // Iniciar UI Swing na EDT
        EventQueue.invokeLater(() -> {
            // Recuperar bean da janela principal (opcional) ou instanciar normalmente
            TelaCursosUI frame = context.getBean(TelaCursosUI.class);
            frame.setVisible(true);
        });
    }
}


