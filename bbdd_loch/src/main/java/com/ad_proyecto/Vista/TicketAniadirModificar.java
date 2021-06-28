/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.Vista;

import com.ad_proyecto.bbdd_loch.Establecimiento;
import com.ad_proyecto.bbdd_loch.Ticket;

/**
 *
 * @author Antonio
 */
public class TicketAniadirModificar extends javax.swing.JFrame {
    
    // TRUE- se puede editar; FALSE- no se puede editar.
    private boolean estadoID;

    /**
     * Creates new form TicketAniadirModificar
     */
    public TicketAniadirModificar() {
        initComponents();
    }
    
    // Rellena los campos con los datos de un ticket.
    public void rellenarCampos(Ticket ticket) {
        tbIdTicket.setText(String.valueOf(ticket.getId()));
        tbIdEstablecimiento.setText(String.valueOf(ticket.getEstab().getId()));
    }
    
    // Limpia los campos.
    public void limpiarCampos() {
        tbIdTicket.setText("");
        tbIdEstablecimiento.setText("");
    }
    
    // Permite habilitar o deshabilitar el TextBox de la ID.
    public void habilitarID(boolean estado) {
        estadoID = estado;
        tbIdTicket.setEnabled(estado);
    }
    public boolean getEstadoID() {
        return estadoID;
    }
    
    // Indica en que modo se inicia la GUI: 1- añadir, 2- modificar.
    public void iniciar(int modo) {
        limpiarCampos();
        
        switch (modo) {
            case 1: // Añadir
                habilitarID(true);
                btnAceptar.setText("Añadir");
                break;
                
            case 2: // Modificar
                habilitarID(false);
                btnAceptar.setText("Modificar");
                break;
        }
    }
    
    public Ticket devolverTicket() {
        Establecimiento estab = new Establecimiento();
        estab.setId(Integer.parseInt(tbIdEstablecimiento.getText()));
        
        return new Ticket(Integer.parseInt(tbIdTicket.getText()), estab);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAniadir = new javax.swing.JPanel();
        panelId = new javax.swing.JPanel();
        lblIdTicket = new javax.swing.JLabel();
        tbIdTicket = new javax.swing.JTextField();
        panelCorreo = new javax.swing.JPanel();
        lblIdEstablecimiento = new javax.swing.JLabel();
        tbIdEstablecimiento = new javax.swing.JTextField();
        panelCancAcep = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblIdTicket.setText("ID Ticket");

        javax.swing.GroupLayout panelIdLayout = new javax.swing.GroupLayout(panelId);
        panelId.setLayout(panelIdLayout);
        panelIdLayout.setHorizontalGroup(
            panelIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdTicket)
                    .addComponent(tbIdTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelIdLayout.setVerticalGroup(
            panelIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIdTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbIdTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblIdEstablecimiento.setText("ID Establecimiento");

        javax.swing.GroupLayout panelCorreoLayout = new javax.swing.GroupLayout(panelCorreo);
        panelCorreo.setLayout(panelCorreoLayout);
        panelCorreoLayout.setHorizontalGroup(
            panelCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCorreoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdEstablecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbIdEstablecimiento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCorreoLayout.setVerticalGroup(
            panelCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCorreoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIdEstablecimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbIdEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Cancelar");

        btnAceptar.setText("Añadir");

        javax.swing.GroupLayout panelCancAcepLayout = new javax.swing.GroupLayout(panelCancAcep);
        panelCancAcep.setLayout(panelCancAcepLayout);
        panelCancAcepLayout.setHorizontalGroup(
            panelCancAcepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCancAcepLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCancAcepLayout.setVerticalGroup(
            panelCancAcepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCancAcepLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCancAcepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelAniadirLayout = new javax.swing.GroupLayout(panelAniadir);
        panelAniadir.setLayout(panelAniadirLayout);
        panelAniadirLayout.setHorizontalGroup(
            panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAniadirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCancAcep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAniadirLayout.createSequentialGroup()
                        .addComponent(panelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAniadirLayout.setVerticalGroup(
            panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAniadirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panelCancAcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAniadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAniadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TicketAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicketAniadirModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAceptar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JLabel lblIdEstablecimiento;
    public javax.swing.JLabel lblIdTicket;
    public javax.swing.JPanel panelAniadir;
    public javax.swing.JPanel panelCancAcep;
    public javax.swing.JPanel panelCorreo;
    public javax.swing.JPanel panelId;
    public javax.swing.JTextField tbIdEstablecimiento;
    public javax.swing.JTextField tbIdTicket;
    // End of variables declaration//GEN-END:variables
}
