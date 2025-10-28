package view;

import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import model.Disciplina;
import model.Professor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmDisciplina extends javax.swing.JFrame {

    public FrmDisciplina() {
        initComponents();
        carregarProfessores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        lblCarga = new javax.swing.JLabel();
        lblProfessor = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCarga = new javax.swing.JTextField();
        cmbProfessor = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisciplinas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Disciplinas");

        lblNome.setText("Nome:");

        lblCarga.setText("Carga Horária:");

        lblProfessor.setText("Professor:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        tblDisciplinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Carga Horária", "Professor"
            }
        ));
        jScrollPane1.setViewportView(tblDisciplinas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar)
                    .addComponent(btnListar)
                    .addComponent(btnFechar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCarga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarga)
                    .addComponent(txtCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProfessor)
                    .addComponent(cmbProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFechar)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Botão Salvar
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            Disciplina d = new Disciplina();
            d.setNome(txtNome.getText());

            // Garantir que a carga horária é um número
            String texto = txtCarga.getText().replaceAll("\\D+", ""); // remove letras
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite apenas números na carga horária (ex: 40)");
                return;
            }
            d.setCargaHoraria(Integer.parseInt(texto));

            // Pega o professor selecionado
            d.setProfessor((Professor) cmbProfessor.getSelectedItem());

            // Salva no banco
            new DisciplinaDAO().inserir(d);
            JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    // Botão Listar
    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        try {
            List<Disciplina> lista = new DisciplinaDAO().listar();
            DefaultTableModel modelo = (DefaultTableModel) tblDisciplinas.getModel();
            modelo.setRowCount(0);
            for (Disciplina d : lista) {
                modelo.addRow(new Object[]{
                    d.getId(),
                    d.getNome(),
                    d.getCargaHoraria() + " horas",
                    d.getProfessor().getNome()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btnListarActionPerformed

    // Botão Fechar
    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    // Método para carregar professores no combo
    private void carregarProfessores() {
        try {
            List<Professor> lista = new ProfessorDAO().listar();
            cmbProfessor.removeAllItems();
            for (Professor p : lista) {
                cmbProfessor.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar professores: " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<model.Professor> cmbProfessor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCarga;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblProfessor;
    private javax.swing.JTable tblDisciplinas;
    private javax.swing.JTextField txtCarga;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
