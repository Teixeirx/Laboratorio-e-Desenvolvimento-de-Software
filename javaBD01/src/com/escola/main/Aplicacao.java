
package com.escola.main;

import com.escola.view.MenuPrincipal;
import javax.swing.*;

/**
 * Classe principal que inicia a aplicação.
 */
public class Aplicacao {

    public static void main(String[] args) {
        // Garante que a UI seja atualizada na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            // Define o Look and Feel para uma aparência mais moderna, correspondente ao sistema operacional
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Não foi possível definir o Look and Feel do sistema.");
                // O programa continuará com o Look and Feel padrão do Java
            }

            // Cria e exibe a tela principal
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}
