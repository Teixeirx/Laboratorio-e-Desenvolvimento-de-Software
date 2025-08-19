package com.mycompany.exemploaula;

import javax.swing.JOptionPane;

public class IMC extends javax.swing.JFrame {

    public IMC() {
        initComponents();
    }

    double peso, altura, imc;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblPeso = new javax.swing.JLabel();
        lblAltura = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        lblResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cálculo do IMC");

        lblPeso.setText("Peso (kg):");
        lblAltura.setText("Altura (m):");

        btnCalcular.setText("Calcular IMC");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        lblResultado.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultado.setText("Digite os valores e calcule seu IMC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPeso)
                        .addGap(18, 18, 18)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAltura)
                        .addGap(18, 18, 18)
                        .addComponent(txtAltura)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeso)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAltura)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnCalcular)
                .addGap(30, 30, 30)
                .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null); // centraliza a janela
    }// </editor-fold>                        

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {                                            
        try {
            peso = Double.parseDouble(txtPeso.getText());
            altura = Double.parseDouble(txtAltura.getText());

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this, "Informe valores válidos para peso e altura!");
                return;
            }

            imc = peso / (altura * altura);

            String classificacao;
            if (imc < 18.5) {
                classificacao = "Abaixo do peso";
            } else if (imc < 24.9) {
                classificacao = "Peso normal";
            } else if (imc < 29.9) {
                classificacao = "Sobrepeso";
            } else if (imc < 34.9) {
                classificacao = "Obesidade Grau I";
            } else if (imc < 39.9) {
                classificacao = "Obesidade Grau II";
            } else {
                classificacao = "Obesidade Grau III (grave)";
            }

            lblResultado.setText(String.format("IMC: %.2f - %s", imc, classificacao));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite apenas números válidos!");
        }
    }                                           

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IMC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCalcular;
    private javax.swing.JLabel lblAltura;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration                   
}
