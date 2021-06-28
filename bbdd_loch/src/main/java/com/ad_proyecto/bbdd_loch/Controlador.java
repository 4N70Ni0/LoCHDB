/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.bbdd_loch;

import com.ad_proyecto.DAOmetodos.DAO;
import com.ad_proyecto.DAOmetodos.DAOImplementacionHibernate;
import com.ad_proyecto.DAOmetodos.DAOImplementacionMongoDB;
import com.ad_proyecto.DAOmetodos.DAOImplementacionObjectDB;
import com.ad_proyecto.Vista.ElegirBBDD;
import com.ad_proyecto.Vista.EstabAniadirModificar;
import com.ad_proyecto.Vista.TicketAniadirModificar;
import com.ad_proyecto.Vista.UsrAniadirModificar;
import com.ad_proyecto.exceptions.DAOConnectionException;
import com.ad_proyecto.exceptions.DAOEliminarException;
import com.ad_proyecto.exceptions.DAOEstablecimientoException;
import com.ad_proyecto.exceptions.DAOInsertarException;
import com.ad_proyecto.exceptions.DAOModificarException;
import com.ad_proyecto.exceptions.DAOUsuarioException;
import com.ad_proyecto.Vista.Vista;
import com.ad_proyecto.exceptions.DAOTicketException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Antonio
 */
public class Controlador implements ActionListener {
    // Patrón FACTORY del DAO.
    // Dependiendo de que use el usuario se hace un polimorfismo u otro.
    private DAO peticion = null;

    // Vista
    private ElegirBBDD elegirbbdd = new ElegirBBDD();
    private Vista vista = new Vista();
    
    private UsrAniadirModificar usrAM = new UsrAniadirModificar();
    private EstabAniadirModificar estabAM = new EstabAniadirModificar();
    private TicketAniadirModificar ticketAM = new TicketAniadirModificar();

    
    public Controlador() throws DAOConnectionException {
        elegirbbdd.setVisible (true);

        /* VISTA ELEGIR BBDD */
        elegirbbdd.btnAceptar.addActionListener(this);
        elegirbbdd.btnCancelar.addActionListener(this);
        
        /* BARRA */
        vista.mitemSalir.addActionListener(this);

        /* VISTA PRINCIPAL */
        vista.btnAniadir.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.cbTablas.addActionListener(this);
        // tabla evento
        
        /* VISTA AÑADIR MODIFICAR */
        // Usuario
        usrAM.btnAceptar.addActionListener(this);
        usrAM.btnCancelar.addActionListener(this);
        // Establecimiento
        estabAM.btnAceptar.addActionListener(this);
        estabAM.btnCancelar.addActionListener(this);
        // Ticket
        ticketAM.btnAceptar.addActionListener(this);
        ticketAM.btnCancelar.addActionListener(this);
    }
    
    //
    // MÉTODOS
    //
    // Hace una consulta a todos los usuarios.
    public List<Usuario> consultarUsuarios() {
        List<Usuario> usr = null;
        
        try { 
            usr = peticion.consultarTodosLosUsuarios(); 
        }
        catch (DAOUsuarioException daoue) {
            System.err.println ("No se ha podido consultar todos los usuarios: "+ daoue.getMessage());
        }
        
        return usr;
    }
    // Hace una consulta a todos los establecimientos.
    public List<Establecimiento> consultarEstablecimientos() {
        List<Establecimiento> estab = null;
        
        try {
            //System.out.println("=== Antes de consultar la tabla Establecimiento");
            estab = peticion.consultarTodosLosEstablecimientos();
            //System.out.println("=== Después de consultar la tabla Establecimiento");
        }
        catch (DAOEstablecimientoException daoee) {
            System.err.println ("No se ha podido consultar todos los establecimientos: "+ daoee.getMessage());
        }
        
//        for (int i = 0; i < estab.size(); i++) {
//            System.out.println (estab.get(i).getNombre());
//        }
        
        return estab;
    }
    // Hace una consulta a todos los tickets.
    public List<Ticket> consultarTickets() {
        List<Ticket> tick = null;
        
        try { 
            tick = peticion.consultarTodosLosTickets(); 
        }
        catch (DAOTicketException daoue) {
            System.err.println ("No se ha podido consultar todos los usuarios: "+ daoue.getMessage());
        }
        System.out.println(tick);
        return tick;
    }
    
    // Elimina todos los usuarios de la BBDD.
    public void eliminarUsuarios() {
        try {
            peticion.eliminarTodosLosUsuarios();
        }
        catch (DAOUsuarioException ex) {
            System.err.println("No se han podido eliminar todos los usuarios: "+ ex.getMessage());
        }
    }
    // Elimina todos los establecimientos de la BBDD.
    public void eliminarEstablecimientos() {
        try {
            peticion.eliminarTodosLosEstablecimientos();
        }
        catch (DAOEstablecimientoException ex) {
            System.err.println("No se han podido eliminar todos los usuarios: "+ ex.getMessage());
        }
    }
    // Elimina todos los tickets de la BBDD.
    public void eliminarTickets() {
        try {
            peticion.eliminarTodosLosTickets();
        }
        catch (DAOTicketException ex) {
            System.err.println("No se han podido eliminar todos los usuarios: "+ ex.getMessage());
        }
    }
    
    // Muestra los datos de la tabla Usuario en el JTable.
    public void refrescarTablaUsuario() {
        vista.cargarTablaUsuario(consultarUsuarios());
    }
    // Muestra los datos de la tabla Establecimiento en el JTable.
    public void refrescarTablaEstablecimiento() {
        vista.cargarTablaEstablecimiento(consultarEstablecimientos());
    }
    // Muestra los datos de la tabla Ticket en el JTable.
    public void refrescarTablaTicket() {
        vista.cargarTablaTicket(consultarTickets());
    }
    
    public void refrescarTabla() {
        vista.cargarTablaUsuario(consultarUsuarios());
        vista.cargarTablaEstablecimiento(consultarEstablecimientos());
        vista.cargarTablaTicket(consultarTickets());
    }
    
    // Devuelve el usuario de una fila de la tabla.
    Usuario getUsuarioFila (int fila) {
        Usuario usr = new Usuario();
        
        usr.setId((int) vista.tabla.getValueAt(fila, 0));
        usr.setCorreo(vista.tabla.getValueAt(fila, 1).toString());
        usr.setContrasenia(vista.tabla.getValueAt(fila, 2).toString());
        usr.setUbicacion(vista.tabla.getValueAt(fila, 3).toString());
        
        return usr;
    }
    // Devuelve el establecimiento de una fila de la tabla.
    Establecimiento getEstablecimientoFila (int fila) {
        Establecimiento estab = new Establecimiento();
        
        estab.setId((int) vista.tabla.getValueAt(fila, 0));
        estab.setNombre(vista.tabla.getValueAt(fila, 1).toString());
        estab.setPersonasActuales((int) vista.tabla.getValueAt(fila, 2));
        estab.setAforoMaximo((int) vista.tabla.getValueAt(fila, 3));
        estab.setUbicacion(vista.tabla.getValueAt(fila, 4).toString());
        
        return estab;
    }
    // Devuelve el ticket de una fila de la tabla.
    Ticket getTicketFila (int fila) {
        Ticket ticket = new Ticket();
        Establecimiento estab = new Establecimiento();
        
        ticket.setId((int) vista.tabla.getValueAt(fila, 0));
        // ID del Establecimiento.
        estab.setId((int) vista.tabla.getValueAt(fila, 1)); 
        ticket.setEstab(estab);
        
        return ticket;
    }
    
    void cerrarLoCHDB() {
        try {
            peticion.cerrarConexion();
        }
        catch (DAOConnectionException ex) {
            System.err.println ("Error al cerrar la conexión: "+ ex.getMessage());
        }
        
        System.exit(0);
    }
    
    
    //
    // Manejo de eventos.
    //
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("=== source: "+ ae.getSource().toString());
        
        /* VISTA ELEGIRBBDD */
        //
        // BOTONES
        //
        if (ae.getSource() == elegirbbdd.btnAceptar) {
            
            switch (elegirbbdd.getOpcion()) {
                case 1: // MariaDB
                    peticion = new DAOImplementacionHibernate();
                    break;
                    
                case 2: // ObjectDB
                    peticion = new DAOImplementacionObjectDB();
                    break;
                    
                case 3: // MongoDB
                    peticion = new DAOImplementacionMongoDB();
                    break;
            }
            
            try {
                peticion.crearConexion();
            } catch (DAOConnectionException ex) {
                System.err.println ("No se ha podido crear la conexión con la base de datos: "+ ex.getMessage());
            }
            
            // Cierra el JFrame
            elegirbbdd.dispose();
            
            // Mostrar las vista principal
            refrescarTablaUsuario();
            vista.setVisible(true);
        }
        else if (ae.getSource() == elegirbbdd.btnCancelar) {
            //elegirbbdd.dispose();
            //cerrarLoCHDB();
            System.exit(0);
        }
        
        /* VISTA PRINCIPAL */
        //
        // BOTONES
        //
        // AÑADIR
        else if (ae.getSource() == vista.btnAniadir) {
            // Si el combobox es Usuario, muestra el formulario de Usuario.
            
            switch (vista.cbTablas.getSelectedIndex()) {
                // Usuario
                case 0:
                    usrAM.iniciar(1); // Modo añadir.
                    usrAM.setVisible(true);
                    break;
                    
                // Establecimiento
                case 1:
                    estabAM.iniciar(1); // Modo añadir.
                    estabAM.setVisible(true);
                    break;
                    
                // Ticket
                case 2:
                    ticketAM.iniciar(1); // Modo añadir.
                    ticketAM.setVisible(true);
                    break;
            }
        }
        // MODIFICAR
        else if (ae.getSource() == vista.btnModificar) {
            int fila;
            
            switch (vista.cbTablas.getSelectedIndex()) {
                // Usuario
                case 0:
                    fila = vista.tabla.getSelectedRow();
                    
                    if (fila >= 0) {
                        Usuario usuario = getUsuarioFila(fila);
                        usrAM.iniciar(2); // Modo modificar.
                        usrAM.rellenarCampos(usuario);
                        usrAM.setVisible(true);
                    }
                    break;
                    
                // Establecimiento
                case 1:
                    fila = vista.tabla.getSelectedRow();
                    
                    if (fila >= 0) {
                        Establecimiento establecimiento = getEstablecimientoFila(fila);
                        estabAM.iniciar(2); // Modo modificar.
                        estabAM.rellenarCampos(establecimiento);
                        estabAM.setVisible(true);
                    }
                    break;
                    
                // Ticket
                case 2:
                    fila = vista.tabla.getSelectedRow();
                    
                    if (fila >= 0) {
                        Ticket ticket = getTicketFila(fila);
                        ticketAM.iniciar(2); // Modo modificar.
                        ticketAM.rellenarCampos(ticket);
                        ticketAM.setVisible(true);
                    }
                    break;
            }
        }
        // ELIMINAR
        else if (ae.getSource() == vista.btnEliminar) {
            System.out.println("--- Eliminar");
            int fila;
            
            switch (vista.cbTablas.getSelectedIndex()) {
                // Usuario
                case 0:
                    fila = vista.tabla.getSelectedRow();
                    
                    if (fila >= 0) {
                        Usuario usuario = getUsuarioFila(fila);

                        try {
                            peticion.eliminar(usuario);
                        }
                        catch (DAOEliminarException ex) {
                            System.err.println ("No se ha podido eliminar el usuario: "+ ex.getMessage());
                        }
                    }
                    
                    refrescarTablaUsuario();
                    break;
                    
                // Establecimiento
                case 1:
                    fila = vista.tabla.getSelectedRow();
                    
                    if (fila >= 0) {
                        Establecimiento establecimiento = getEstablecimientoFila(fila);
                        
                        try {
                            peticion.eliminar(establecimiento);
                        }
                        catch (DAOEliminarException ex) {
                            System.err.println ("No se ha podido eliminar el usuario: "+ ex.getMessage());
                        }
                    }
                    
                    refrescarTablaEstablecimiento();
                    break;
                    
                // Ticket
                case 2:
                    fila = vista.tabla.getSelectedRow();
                    
                    if (fila >= 0) {
                        Ticket ticket = getTicketFila(fila);

                        try {
                            peticion.eliminar(ticket);
                        }
                        catch (DAOEliminarException ex) {
                            System.err.println ("No se ha podido eliminar el ticket: "+ ex.getMessage());
                        }
                    }
                    
                    refrescarTablaTicket();
                    break;
            }
        }
        //
        // TABLA
        //
        else if (ae.getSource() == vista.cbTablas) { // COMBO BOX USR o ESTAB
            
            switch (vista.cbTablas.getSelectedIndex()) {
                // Usuario
                case 0:
                    refrescarTablaUsuario();
                    break;
                    
                // Establecimiento
                case 1:
                    refrescarTablaEstablecimiento();
                    break;
                
                // Ticket
                case 2:
                    refrescarTablaTicket();
                    break;
            }
        }
        
        /* VISTA AÑADIR MODIFICAR */
        //
        // Usuario
        //
        else if (ae.getSource() == usrAM.btnAceptar) { // ACEPTAP
            System.out.println("=== === Aceptar");
            Usuario usuario = usrAM.devolverUsuario();
            
            // Si el estado es TRUE: añadir;
            if (usrAM.getEstadoID()) {
                // Si el usuario no existe, se añade.
                if (! peticion.existe(usuario)) {
                    try {
                        peticion.insertar(usuario);
                    } 
                    catch (DAOInsertarException ex) {
                        System.err.println ("Error al insertar el usuario: "+ ex.getMessage());
                    }
                }
                else {
                    System.out.println ("=== La ID que se intenta insertar ya existe");
                }
            }
            // Si es FALSE: modificar
            else {
                try {
                    peticion.modificar(usuario);
                }
                catch (DAOModificarException ex) {
                    System.err.println ("Error al modificar el usuario: "+ ex.getMessage());
                }
            }
            
            usrAM.dispose();
            refrescarTablaUsuario();
        }
        else if (ae.getSource() == usrAM.btnCancelar) { // CANCELAR
            System.out.println ("=== Dispose");
            usrAM.dispose(); // Cierra la ventana.
            //usrAniadir.setVisible(false);
        }
        
        //
        // Establecimiento
        //
        else if (ae.getSource() == estabAM.btnAceptar) {
            Establecimiento establecimiento = estabAM.devolverEstablecimiento();
            
            // Si el estado es TRUE: añadir;
            if (estabAM.getEstadoID()) {
                // Si el usuario no existe, se añade.
                if (! peticion.existe(establecimiento)) {
                    try {
                        peticion.insertar(establecimiento);
                    } 
                    catch (DAOInsertarException ex) {
                        System.err.println ("Error al insertar el establecimiento: "+ ex.getMessage());
                    }
                }
                else {
                    System.out.println ("=== La ID que se intenta insertar ya existe");
                }
            }
            // Si es FALSE: modificar
            else {
                try {
                    peticion.modificar(establecimiento);
                }
                catch (DAOModificarException ex) {
                    System.err.println ("Error al modificar el establecimiento: "+ ex.getMessage());
                }
            }
            
            estabAM.dispose();
            refrescarTablaEstablecimiento();
        }
        else if (ae.getSource() == estabAM.btnCancelar) {
            estabAM.dispose();
        }
        
        //
        // Ticket
        //
        else if (ae.getSource() == ticketAM.btnAceptar) { // ACEPTAP
            System.out.println("=== === Aceptar");
            Ticket ticket = ticketAM.devolverTicket();
            
            // Si el estado es TRUE: añadir;
            if (ticketAM.getEstadoID()) {
                // Si el usuario no existe, se añade.
                if (! peticion.existe(ticket)) {
                    try {
                        System.out.println(ticket.getEstab());
                        Establecimiento estab = peticion.consultar(ticket.getEstab());
                        
                        if (estab != null)
                            ticket.setEstab(estab);
                        
                        System.out.println(estab);
                        
                        peticion.insertar(ticket);
                    } 
                    catch (DAOInsertarException ex) {
                        System.err.println("Error al insertar el ticket: "+ ex.getMessage());
                    } catch (DAOEstablecimientoException ex) {
                        System.err.println("Error al consultar el establecimiento: "+ ex.getMessage());
                    }
                }
                else {
                    System.out.println ("=== La ID que se intenta insertar ya existe");
                }
            }
            // Si es FALSE: modificar
            else {
                try {
                    Establecimiento estab = peticion.consultar(ticket.getEstab());
                        
                    if (estab != null)
                        ticket.setEstab(estab);
                        
                    peticion.modificar(ticket);
                }
                catch (DAOModificarException ex) {
                    System.err.println ("Error al modificar el ticket: "+ ex.getMessage());
                } catch (DAOEstablecimientoException ex) {
                    System.err.println("Error al consultar el establecimiento: "+ ex.getMessage());
                }
            }
            
            ticketAM.dispose();
            refrescarTablaTicket();
        }
        else if (ae.getSource() == ticketAM.btnCancelar) { // CANCELAR
            System.out.println ("=== Dispose");
            ticketAM.dispose(); // Cierra la ventana.
            //usrAniadir.setVisible(false);
        }
        /*
         * Barra
         */
        //
        // Archivo
        //
        // Salir de la aplicación
        else if (ae.getSource() == vista.mitemSalir) {
            try {
                peticion.cerrarConexion();
            } catch (DAOConnectionException ex) {
                System.err.println ("Error al cerrar la conexión: "+ ex.getMessage());
            }
            
            System.exit (0);
        }
        
    }  
}
