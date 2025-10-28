package com.escola.view;

import com.escola.DAO.ProfessorDAO;
import com.escola.model.Professor;

import javax.swing.*;

public class CadastrarProfessor extends JFrame {

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtIdade = new JTextField(5);
    private final JTextField txtDisciplina = new JTextField(20);

    public CadastrarProfessor() {
        super("Cadastrar Professor");

        // --- Configuração do Layout ---
        JPanel painel = new JPanel();
        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblTitulo = new JLabel("Cadastrar Professor");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(24f));

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblIdade = new JLabel("Idade:");
        JLabel lblDisciplina = new JLabel("Disciplina:");
        JButton btnSalvar = new JButton("Salvar");

        // --- Layout Horizontal ---
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitulo)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNome)
                                .addComponent(lblIdade)
                                .addComponent(lblDisciplina))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome)
                                .addComponent(txtIdade)
                                .addComponent(txtDisciplina)))
                .addComponent(btnSalvar)
        );

        // --- Layout Vertical ---
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(txtNome))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIdade)
                        .addComponent(txtIdade))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDisciplina)
                        .addComponent(txtDisciplina))
                .addComponent(btnSalvar)
        );

        // --- Ação do Botão ---
        btnSalvar.addActionListener(e -> salvarProfessor());

        // --- Configurações da Janela ---
        this.add(painel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void salvarProfessor() {
        if (txtNome.getText().isBlank() || txtIdade.getText().isBlank() || txtDisciplina.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String disciplina = txtDisciplina.getText();

            Professor professor = new Professor(nome, idade, disciplina);
            ProfessorDAO professorDAO = new ProfessorDAO();
            professorDAO.inserir(professor);

            JOptionPane.showMessageDialog(this, "Professor salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo 'Idade' deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o professor:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtDisciplina.setText("");
        txtNome.requestFocus();
    }
}