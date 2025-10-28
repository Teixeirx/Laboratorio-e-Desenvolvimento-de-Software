
package com.escola.view;

import javax.swing.*;

/**
 * Tela principal da aplicação.
 * Contém o menu para acessar as outras funcionalidades.
 */
public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        super("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza a janela

        JMenuBar menuBar = new JMenuBar();

        // Menu Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem menuSair = new JMenuItem("Sair");
        menuSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(menuSair);

        // Menu Cadastro
        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem menuCadAluno = new JMenuItem("Cadastro de Alunos");
        menuCadAluno.addActionListener(e -> new CadastrarAluno().setVisible(true));
        JMenuItem menuCadProfessor = new JMenuItem("Cadastro de Professores");
        menuCadProfessor.addActionListener(e -> new CadastrarProfessor().setVisible(true));
        menuCadastro.add(menuCadAluno);
        menuCadastro.add(menuCadProfessor);

        // Menu Relatórios
        JMenu menuRelatorio = new JMenu("Relatórios");
        JMenuItem menuRelatorioAluno = new JMenuItem("Relatório de Alunos");
        menuRelatorioAluno.addActionListener(e -> new RelatorioAlunos().setVisible(true));
        JMenuItem menuRelatorioProfessor = new JMenuItem("Relatório de Professores");
        menuRelatorioProfessor.addActionListener(e -> new RelatorioProfessores().setVisible(true));
        menuRelatorio.add(menuRelatorioAluno);
        menuRelatorio.add(menuRelatorioProfessor);

        menuBar.add(menuArquivo);
        menuBar.add(menuCadastro);
        menuBar.add(menuRelatorio);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        // Define o Look and Feel para uma aparência mais moderna
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}
