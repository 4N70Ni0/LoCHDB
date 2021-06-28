/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.Vista;

import com.ad_proyecto.bbdd_loch.Establecimiento;

/**
 *
 * @author Antonio
 */
public class EstabAniadirModificar extends javax.swing.JFrame {

    // TRUE- se puede editar; FALSE- no se puede editar.
    private boolean estadoID;
    
    /**
     * Creates new form EstabAniadirModificar
     */
    public EstabAniadirModificar() {
        initComponents();
    }
    
    // Rellena los campos con los datos de un establecimiento.
    public void rellenarCampos(int id, String nombre, int personasActuales, int aforoMaximo, String ubicacion) {
        tbId.setText(String.valueOf(id));
        tbNombre.setText(nombre);
        spnPersonasActuales.setValue(personasActuales);
        spnAforoMaximo.setValue(aforoMaximo);
        tbUbicacion.setText(ubicacion);
    }
    public void rellenarCampos(Establecimiento establecimiento) {
        tbId.setText(String.valueOf(establecimiento.getId()));
        tbNombre.setText(establecimiento.getNombre());
        spnPersonasActuales.setValue(establecimiento.getPersonasActuales());
        spnAforoMaximo.setValue(establecimiento.getAforoMaximo());
        tbUbicacion.setText(establecimiento.getUbicacion());
    }
    
    // Limpia los campos.
    public void limpiarCampos() {
        tbId.setText("");
        tbNombre.setText("");
        spnPersonasActuales.setValue(0);
        spnAforoMaximo.setValue(0);
        tbUbicacion.setText("");
    }
    
    // Permite habilitar o desabilitar el TextBox de la ID.
    public void habilitarID(boolean estado) {
        estadoID = estado;
        tbId.setEnabled(estado);
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
    
    public Establecimiento devolverEstablecimiento() {
        return new Establecimiento(Integer.parseInt(tbId.getText()), tbNombre.getText(), 
                (int) spnPersonasActuales.getValue(), (int) spnAforoMaximo.getValue(), tbUbicacion.getText());
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
        lblId = new javax.swing.JLabel();
        tbId = new javax.swing.JTextField();
        panelNombre = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        tbNombre = new javax.swing.JTextField();
        panelPersonasActuales = new javax.swing.JPanel();
        lblPersonasActuales = new javax.swing.JLabel();
        spnPersonasActuales = new javax.swing.JSpinner();
        panelAforoMaximo = new javax.swing.JPanel();
        lblAforoMaximo = new javax.swing.JLabel();
        spnAforoMaximo = new javax.swing.JSpinner();
        panelUbicacion = new javax.swing.JPanel();
        lblUbicacion = new javax.swing.JLabel();
        tbUbicacion = new javax.swing.JTextField();
        panelCancAcep = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblId.setText("ID");

        javax.swing.GroupLayout panelIdLayout = new javax.swing.GroupLayout(panelId);
        panelId.setLayout(panelIdLayout);
        panelIdLayout.setHorizontalGroup(
            panelIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(tbId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelIdLayout.setVerticalGroup(
            panelIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblNombre.setText("Nombre");

        javax.swing.GroupLayout panelNombreLayout = new javax.swing.GroupLayout(panelNombre);
        panelNombre.setLayout(panelNombreLayout);
        panelNombreLayout.setHorizontalGroup(
            panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(tbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNombreLayout.setVerticalGroup(
            panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPersonasActuales.setText("Personas actuales");

        javax.swing.GroupLayout panelPersonasActualesLayout = new javax.swing.GroupLayout(panelPersonasActuales);
        panelPersonasActuales.setLayout(panelPersonasActualesLayout);
        panelPersonasActualesLayout.setHorizontalGroup(
            panelPersonasActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonasActualesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPersonasActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPersonasActuales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnPersonasActuales))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPersonasActualesLayout.setVerticalGroup(
            panelPersonasActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonasActualesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPersonasActuales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnPersonasActuales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAforoMaximo.setText("Aforo máximo");

        javax.swing.GroupLayout panelAforoMaximoLayout = new javax.swing.GroupLayout(panelAforoMaximo);
        panelAforoMaximo.setLayout(panelAforoMaximoLayout);
        panelAforoMaximoLayout.setHorizontalGroup(
            panelAforoMaximoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAforoMaximoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAforoMaximoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAforoMaximo)
                    .addComponent(spnAforoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelAforoMaximoLayout.setVerticalGroup(
            panelAforoMaximoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAforoMaximoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAforoMaximo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnAforoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUbicacion.setText("Ubicación");

        javax.swing.GroupLayout panelUbicacionLayout = new javax.swing.GroupLayout(panelUbicacion);
        panelUbicacion.setLayout(panelUbicacionLayout);
        panelUbicacionLayout.setHorizontalGroup(
            panelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUbicacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUbicacion)
                    .addComponent(tbUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUbicacionLayout.setVerticalGroup(
            panelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUbicacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUbicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCancAcep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAniadirLayout.createSequentialGroup()
                        .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelPersonasActuales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelAforoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAniadirLayout.setVerticalGroup(
            panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAniadirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAniadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelAforoMaximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPersonasActuales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelCancAcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(EstabAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstabAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstabAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstabAniadirModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstabAniadirModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAceptar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JLabel lblAforoMaximo;
    public javax.swing.JLabel lblId;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblPersonasActuales;
    public javax.swing.JLabel lblUbicacion;
    public javax.swing.JPanel panelAforoMaximo;
    public javax.swing.JPanel panelAniadir;
    public javax.swing.JPanel panelCancAcep;
    public javax.swing.JPanel panelId;
    public javax.swing.JPanel panelNombre;
    public javax.swing.JPanel panelPersonasActuales;
    public javax.swing.JPanel panelUbicacion;
    public javax.swing.JSpinner spnAforoMaximo;
    public javax.swing.JSpinner spnPersonasActuales;
    public javax.swing.JTextField tbId;
    public javax.swing.JTextField tbNombre;
    public javax.swing.JTextField tbUbicacion;
    // End of variables declaration//GEN-END:variables
}