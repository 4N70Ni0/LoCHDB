/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.Vista;

import com.ad_proyecto.bbdd_loch.Establecimiento;
import com.ad_proyecto.bbdd_loch.Ticket;
import com.ad_proyecto.bbdd_loch.Usuario;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antonio
 */
public class Vista extends javax.swing.JFrame {
    public DefaultTableModel usrModel = null;
    public DefaultTableModel estabModel = null;
    public DefaultTableModel tickModel = null;

    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        
    }
    
    // Carga los datos de Usuario en la tabla para que se muestren.
    public void cargarTablaUsuario(List<Usuario> usrs) {
        int filas = usrs.size();
        String[] columnas = {"ID", "Correo electrónico", "Contraseña", "Ubicación"};
        Object[][] datos = new Object[filas][columnas.length];
        
        // Rellena las columnas de cada fila.
        for (int row = 0; row < filas; row++) {
            datos[row][0] = usrs.get(row).getId();
            datos[row][1] = usrs.get(row).getCorreo();
            datos[row][2] = usrs.get(row).getContrasenia();
            datos[row][3] = usrs.get(row).getUbicacion();                                    
        }
        
        // Crea la tabla con el modelo por defecto.
        usrModel = new DefaultTableModel(datos, columnas) {
            // Impide que la tabla se pueda editar.
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tabla.setModel(usrModel);
    }
    
    // Carga los datos de Establecimiento en la tabla para que se muestren.
    public void cargarTablaEstablecimiento(List<Establecimiento> estabs) {
        int filas = estabs.size();
        String[] columnas = {"ID", "Nombre", "Personas actuales", "Aforo máximo", "Ubicación"};
        Object[][] datos = new Object[filas][columnas.length];
        
        // Rellena las columnas de cada fila.
        for (int row = 0; row < filas; row++) {
            datos[row][0] = estabs.get(row).getId();
            datos[row][1] = estabs.get(row).getNombre();
            datos[row][2] = estabs.get(row).getPersonasActuales();
            datos[row][3] = estabs.get(row).getAforoMaximo();
            datos[row][4] = estabs.get(row).getUbicacion();                                    
        }
        
        // Crea la tabla con el modelo por defecto.
        estabModel = new DefaultTableModel(datos, columnas) {
            // Impide que la tabla se pueda editar.
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tabla.setModel(estabModel);
    }
    
    // Carga los datos de Ticket en la tabla para que se muestren.
    public void cargarTablaTicket(List<Ticket> tick) {
        int filas = tick.size();
        String[] columnas = {"ID", "ID del establecimiento", "Nombre del establecimiento"};
        Object[][] datos = new Object[filas][columnas.length];
        
        // Rellena las columnas de cada fila.
        for (int row = 0; row < filas; row++) {
            datos[row][0] = tick.get(row).getId();
            datos[row][1] = tick.get(row).getEstab().getId();
            datos[row][2] = tick.get(row).getEstab().getNombre();
        }
        
        // Crea la tabla con el modelo por defecto.
        tickModel = new DefaultTableModel(datos, columnas) {
            // Impide que la tabla se pueda editar.
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tabla.setModel(tickModel);
    }
    
    // Devuelve la ruta de un fichero seleccionado usando JFileChooser.
    public String rutaDirectorio() {
        File fichero = null;
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
        fc.setFileHidingEnabled (true);
        
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fichero = fc.getSelectedFile();
        }
        //System.out.println ("--- --- "+ fichero);
        return fichero.toString() +"/";
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        btnAniadir = new javax.swing.JButton();
        panelTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbTablas = new javax.swing.JComboBox<>();
        mbar = new javax.swing.JMenuBar();
        mbarArchivo = new javax.swing.JMenu();
        mitemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LoCHDB");

        btnAniadir.setText("Añadir");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla.setFocusable(false);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        panelTabla.setViewportView(tabla);

        btnModificar.setText("Modificar");

        btnEliminar.setText("Eliminar");

        cbTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Establecimiento", "Ticket" }));
        cbTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTablasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(cbTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(btnAniadir)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(67, 67, 67))
            .addComponent(panelTabla)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAniadir)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(cbTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );

        mbarArchivo.setText("Archivo");

        mitemSalir.setText("Salir");
        mbarArchivo.add(mitemSalir);

        mbar.add(mbarArchivo);

        setJMenuBar(mbar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTablasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTablasActionPerformed

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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAniadir;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JComboBox<String> cbTablas;
    public javax.swing.JMenuBar mbar;
    public javax.swing.JMenu mbarArchivo;
    public javax.swing.JMenuItem mitemSalir;
    public javax.swing.JPanel panel;
    public javax.swing.JScrollPane panelTabla;
    public javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
