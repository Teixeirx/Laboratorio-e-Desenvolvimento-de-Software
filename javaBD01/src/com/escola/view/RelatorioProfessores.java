
package com.escola.view;

import com.escola.DAO.ProfessorDAO;
import com.escola.model.Professor;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RelatorioProfessores extends JFrame {

    private final JTextField txtNome = new JTextField(20);
    private final JTable tabela;
    private final DefaultTableModel modeloTabela;

    public RelatorioProfessores() {
        super("Relatório de Professores");

        // --- Configuração da Tabela ---
        String[] colunas = {"ID", "Nome", "Idade", "Disciplina"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        // --- Configuração do Layout ---
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBusca.add(new JLabel("Buscar por Nome:"));
        painelBusca.add(txtNome);

        painel.add(painelBusca, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);

        // --- Ação de Busca ---
        txtNome.getDocument().addDocumentListener((javax.swing.event.DocumentListener) new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { carregarTabela(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { carregarTabela(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { carregarTabela(); }
        });

        // --- Configurações da Janela ---
        this.add(painel);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Carrega os dados iniciais
        carregarTabela();
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<Professor> professores = professorDAO.buscarPorNome(txtNome.getText());

        for (Professor professor : professores) {
            modeloTabela.addRow(new Object[]{
                    professor.getId(),
                    professor.getNome(),
                    professor.getIdade(),
                    professor.getDisciplina()
            });
        }
    }
}
