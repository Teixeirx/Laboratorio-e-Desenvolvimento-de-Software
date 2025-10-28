
package com.escola.view;

import com.escola.DAO.AlunoDAO;
import com.escola.model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RelatorioAlunos extends JFrame {

    private final JTextField txtNome = new JTextField(20);
    private final JTable tabela;
    private final DefaultTableModel modeloTabela;

    public RelatorioAlunos() {
        super("Relatório de Alunos");

        // --- Configuração da Tabela ---
        String[] colunas = {"ID", "Nome", "Idade", "Curso"};
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
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> alunos = alunoDAO.buscarPorNome(txtNome.getText());

        for (Aluno aluno : alunos) {
            modeloTabela.addRow(new Object[]{
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getIdade(),
                    aluno.getCurso()
            });
        }
    }
}
