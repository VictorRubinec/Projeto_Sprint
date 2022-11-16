/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.jframekash;

import banco.TbUsuario;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class TelaDisco extends javax.swing.JFrame {

    Looca looca = new Looca();
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
    List<Disco> discos = grupoDeDiscos.getDiscos();
    List<Volume> volumes = grupoDeDiscos.getVolumes();
    ImageIcon img = new ImageIcon("src/main/resources/logoWhite.png");
    TbUsuario usuario = new TbUsuario();

    /**
     * Creates new form TelaDisco
     */
    public TelaDisco() {
        initComponents();
        
        String tudo = "";
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                txtDisco.setText("");
                String stringDiscos = "";
                for (Disco disco : discos) {
                    String nome = "Nome: " + disco.getNome() + "\n";
                    String modelo = "Modelo: " + disco.getModelo() + "\n";
                    String serial = "Serial: " + disco.getSerial() + "\n";
                    Double tamanho = disco.getTamanho().doubleValue();
                    tamanho = tamanho / 1024 / 1024 / 1024;
                    tamanho = Math.round(tamanho * 10.0) / 10.0;
                    String tamanhoString = "Tamanho: " + tamanho + "GB\n";
                    String leitura = "leitura: " + disco.getLeituras() + "\n";
                    String bytesLeitura = "Bytes de leitura: " + disco.getBytesDeLeitura() + "\n";
                    String escrita = "escrita: " + disco.getEscritas() + "\n";
                    String bytesEscrita = "Bytes de escrita: " + disco.getBytesDeEscritas() + "\n";
                    String tamanhoFila = "Tamanho da fila: " + disco.getTamanhoAtualDaFila() + "\n";
                    String tempoTrasferencia = "Tempo de transferencia: " + disco.getTempoDeTransferencia() + "\n\n";
                    stringDiscos += nome + modelo + serial + tamanhoString + leitura + bytesLeitura + escrita + bytesEscrita + tamanhoFila + tempoTrasferencia;
                    txtDisco.setText(stringDiscos);
                }
                txtParticoes.setText("");
                String stringParticoes = "";
                for (Volume volume : volumes) {
                    String pontoMontagem = "Partição: " + volume.getPontoDeMontagem() + "\n";
                    String uuid = "ID: " + volume.getUUID() + "\n";
                    String tipo = "Tipo: " + volume.getTipo() + "\n";

                    Double volumeDisponivel = volume.getDisponivel().doubleValue();
                    volumeDisponivel = volumeDisponivel / 1024 / 1024 / 1024;
                    volumeDisponivel = Math.round(volumeDisponivel * 10.0) / 10.0;
                    String volumeDisponivelString = "Volume disponível: " + volumeDisponivel + "GB" + "\n";

                    Double volumeTotal = volume.getTotal().doubleValue();
                    volumeTotal = volumeTotal / 1024 / 1024 / 1024;
                    volumeTotal = Math.round(volumeTotal * 10.0) / 10.0;
                    String volumeTotalString = "Volume total: " + volumeTotal + "GB" + "\n\n";
                    
                    stringParticoes += pontoMontagem + uuid + tipo + volumeDisponivelString + volumeTotalString;

                    txtParticoes.setText(stringParticoes);
                }
                lblQntDiscos.setText("Quantidade de Discos: ");
                lblQntVolume.setText("Quantidade de volume: ");
                lblQntDiscos.setText(lblQntDiscos.getText() + grupoDeDiscos.getQuantidadeDeDiscos());
                lblQntVolume.setText(lblQntVolume.getText() + grupoDeDiscos.getQuantidadeDeVolumes());
                
                lblTamanhoDiscos.setText("Tamanho dos discos: ");
                Double tamanhoDisco = grupoDeDiscos.getTamanhoTotal().doubleValue();
                tamanhoDisco = tamanhoDisco / 1024 / 1024 / 1024;
                tamanhoDisco = Math.round(tamanhoDisco * 10.0) / 10.0;
                lblTamanhoDiscos.setText(lblTamanhoDiscos.getText() + tamanhoDisco + "GB");
            }
        }, 0, 5000);
        
        
        
        img.setImage(img.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 1));
        lblLogo.setIcon(img);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnCPU = new javax.swing.JButton();
        btnProcessos = new javax.swing.JButton();
        btnMemo = new javax.swing.JButton();
        btnSO = new javax.swing.JButton();
        lblQntDiscos = new javax.swing.JLabel();
        lblQntVolume = new javax.swing.JLabel();
        lblTamanhoDiscos = new javax.swing.JLabel();
        pnlDisco = new javax.swing.JPanel();
        lblDisco = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDisco = new javax.swing.JTextPane();
        pnlParticoes = new javax.swing.JPanel();
        lblParticoes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtParticoes = new javax.swing.JTextPane();
        btnTemp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoWhite.png"))); // NOI18N
        lblLogo.setAlignmentX(-18.0F);
        lblLogo.setAlignmentY(-200.0F);

        lblTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTitulo.setText("Disco e Volume");

        btnCPU.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnCPU.setText("CPU");
        btnCPU.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });

        btnProcessos.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnProcessos.setText("Processos");
        btnProcessos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessosActionPerformed(evt);
            }
        });

        btnMemo.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnMemo.setText("Memória");
        btnMemo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnMemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemoActionPerformed(evt);
            }
        });

        btnSO.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnSO.setText("Sistema");
        btnSO.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnSO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSOActionPerformed(evt);
            }
        });

        lblQntDiscos.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblQntDiscos.setText("Quantidade de Discos: ");

        lblQntVolume.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblQntVolume.setText("Quantidade de volume: ");

        lblTamanhoDiscos.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblTamanhoDiscos.setText("Tamanho dos discos: ");

        lblDisco.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblDisco.setText("Disco:");

        txtDisco.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(txtDisco);

        javax.swing.GroupLayout pnlDiscoLayout = new javax.swing.GroupLayout(pnlDisco);
        pnlDisco.setLayout(pnlDiscoLayout);
        pnlDiscoLayout.setHorizontalGroup(
            pnlDiscoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiscoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDiscoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(pnlDiscoLayout.createSequentialGroup()
                        .addComponent(lblDisco)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDiscoLayout.setVerticalGroup(
            pnlDiscoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiscoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDisco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblParticoes.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblParticoes.setText("Partições:");

        txtParticoes.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(txtParticoes);

        javax.swing.GroupLayout pnlParticoesLayout = new javax.swing.GroupLayout(pnlParticoes);
        pnlParticoes.setLayout(pnlParticoesLayout);
        pnlParticoesLayout.setHorizontalGroup(
            pnlParticoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParticoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlParticoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlParticoesLayout.createSequentialGroup()
                        .addComponent(lblParticoes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlParticoesLayout.setVerticalGroup(
            pnlParticoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParticoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblParticoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTemp.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnTemp.setText("Temperatura");
        btnTemp.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTempActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnCPU))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnSO))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProcessos)
                            .addComponent(btnMemo)
                            .addComponent(btnTemp))))
                .addGap(119, 119, 119)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQntVolume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTamanhoDiscos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblQntDiscos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlParticoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(198, 198, 198))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175)
                .addComponent(lblTitulo)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblTitulo)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnCPU)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnSO)
                                .addGap(28, 28, 28)
                                .addComponent(btnMemo)
                                .addGap(29, 29, 29)
                                .addComponent(btnProcessos)
                                .addGap(18, 18, 18)
                                .addComponent(btnTemp))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblQntDiscos)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(lblQntVolume)))
                                .addGap(40, 40, 40)
                                .addComponent(lblTamanhoDiscos))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(pnlDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlParticoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        TelaCPU in = new TelaCPU();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnMemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemoActionPerformed
        TelaMemoria in = new TelaMemoria();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnMemoActionPerformed

    private void btnSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSOActionPerformed
        TelaSO in = new TelaSO();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnSOActionPerformed

    private void btnProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessosActionPerformed
        TelaProcessos in = new TelaProcessos();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnProcessosActionPerformed

    private void btnTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTempActionPerformed
        TelaTemperatura in = new TelaTemperatura();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnTempActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDisco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDisco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDisco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDisco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDisco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPU;
    private javax.swing.JButton btnMemo;
    private javax.swing.JButton btnProcessos;
    private javax.swing.JButton btnSO;
    private javax.swing.JButton btnTemp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDisco;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblParticoes;
    private javax.swing.JLabel lblQntDiscos;
    private javax.swing.JLabel lblQntVolume;
    private javax.swing.JLabel lblTamanhoDiscos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlDisco;
    private javax.swing.JPanel pnlParticoes;
    private javax.swing.JTextPane txtDisco;
    private javax.swing.JTextPane txtParticoes;
    // End of variables declaration//GEN-END:variables
}
