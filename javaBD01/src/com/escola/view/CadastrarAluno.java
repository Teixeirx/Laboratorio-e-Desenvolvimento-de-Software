package com.escola.view;

import com.escola.DAO.AlunoDAO;
import com.escola.model.Aluno;

import javax.swing.*;

public class CadastrarAluno extends JFrame {

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtIdade = new JTextField(5);
    private final JTextField txtCurso = new JTextField(20);

    public CadastrarAluno() {
        super("Cadastrar Aluno");

        // --- Configuração do Layout ---
        JPanel painel = new JPanel();
        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblTitulo = new JLabel("Cadastrar Aluno");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(24f));

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblIdade = new JLabel("Idade:");
        JLabel lblCurso = new JLabel("Curso:");
        JButton btnSalvar = new JButton("Salvar");

        // --- Layout Horizontal ---
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitulo)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNome)
                                .addComponent(lblIdade)
                                .addComponent(lblCurso))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome)
                                .addComponent(txtIdade)
                                .addComponent(txtCurso)))
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
                        .addComponent(lblCurso)
                        .addComponent(txtCurso))
                .addComponent(btnSalvar)
        );

        // --- Ação do Botão ---
        btnSalvar.addActionListener(e -> salvarAluno());

        // --- Configurações da Janela ---
        this.add(painel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void salvarAluno() {
        if (txtNome.getText().isBlank() || txtIdade.getText().isBlank() || txtCurso.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String curso = txtCurso.getText();

            Aluno aluno = new Aluno(nome, idade, curso);
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.inserir(aluno);

            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo 'Idade' deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o aluno:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtCurso.setText("");
        txtNome.requestFocus();
    }
}