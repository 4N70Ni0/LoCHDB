/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.DAOmetodos;

import com.ad_proyecto.bbdd_loch.Establecimiento;
import com.ad_proyecto.bbdd_loch.Tablas;
import com.ad_proyecto.bbdd_loch.Ticket;
import com.ad_proyecto.bbdd_loch.Usuario;
import com.ad_proyecto.exceptions.DAOConnectionException;
import com.ad_proyecto.exceptions.DAOEliminarException;
import com.ad_proyecto.exceptions.DAOEstablecimientoException;
import com.ad_proyecto.exceptions.DAOInsertarException;
import com.ad_proyecto.exceptions.DAOModificarException;
import com.ad_proyecto.exceptions.DAOTicketException;
import com.ad_proyecto.exceptions.DAOUsuarioException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Antonio
 */
public class DAOImplementacionObjectDB implements DAO {
    private List<Usuario> usuarios;
    private List<Establecimiento> establecimientos;
    private List<Ticket> tickets;
    private int usuariosPos = 0; // Posición de la consulta actual en el lista 'usuarios'.
    
    private EntityManagerFactory emf;
    private EntityManager em;

    //
    // Conexión y cerrar conexión.
    //
    @Override
    public void crearConexion() throws DAOConnectionException {
        emf = Persistence.createEntityManagerFactory ("object:db/nba.odb");
        em = emf.createEntityManager();
    }

    @Override
    public void cerrarConexion() throws DAOConnectionException {
        em.close();
        emf.close();
    }

    //
    // Consultas.
    //
    // Devuelve el objeto completo a través de la ID del mismo.
    @Override
    public Usuario consultar(Usuario usuario) throws DAOUsuarioException {
        return em.find(Usuario.class, usuario.getId());
    }
    @Override
    public Establecimiento consultar(Establecimiento establecimiento) throws DAOEstablecimientoException {
        return em.find(Establecimiento.class, establecimiento.getId());
    }
    @Override
    public Ticket consultar(Ticket ticket) throws DAOTicketException {
        return em.find(Ticket.class, ticket.getId());
    }
    
    // Actualizar la lista 'usuarios' además de devolverla.
    @Override
    public List<Usuario> consultarTodosLosUsuarios() throws DAOUsuarioException {
        TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        usuarios = consulta.getResultList();
        
        return usuarios;
    }
    // Actualizar la lista 'establecimientos' además de devolverla.
    @Override
    public List<Establecimiento> consultarTodosLosEstablecimientos() throws DAOEstablecimientoException {
        TypedQuery<Establecimiento> consulta = em.createQuery("SELECT e FROM Establecimiento e", Establecimiento.class);
        establecimientos = consulta.getResultList();
        
        return establecimientos;
    }
    // Actualizar la lista 'tickets' además de devolverla.
    @Override
    public List<Ticket> consultarTodosLosTickets() throws DAOTicketException {
        TypedQuery<Ticket> consulta = em.createQuery("SELECT t FROM Ticket t", Ticket.class);
        tickets = consulta.getResultList();
        
        return tickets;
    }
    
    // Eliminar todos los usuarios
    @Override
    public void eliminarTodosLosUsuarios() throws DAOUsuarioException {
        TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> usr = consulta.getResultList();
        
        for (Usuario u: usr) {
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        }
    }
    // Eliminar todos los establecimientos
    @Override
    public void eliminarTodosLosEstablecimientos() throws DAOEstablecimientoException {
        TypedQuery<Establecimiento> consulta = em.createQuery("SELECT e FROM Establecimiento e", Establecimiento.class);
        List<Establecimiento> estab = consulta.getResultList();
        
        for (Establecimiento e: estab) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }
    }
    // Eliminar todos los tickets
    @Override
    public void eliminarTodosLosTickets() throws DAOTicketException {
        TypedQuery<Ticket> consulta = em.createQuery("SELECT t FROM Ticket t", Ticket.class);
        List<Ticket> tick = consulta.getResultList();
        
        for (Ticket t: tick) {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
    }

    //
    // Funciones.
    //
    // Insertar
    @Override
    public void insertar(Usuario usuario) throws DAOInsertarException {
        // Transacción para insertar el usuario.
        em.getTransaction().begin();
        em.persist (usuario);
        em.getTransaction().commit();
    }
    @Override
    public void insertar(Establecimiento establecimiento) throws DAOInsertarException {
        // Transacción para insertar el usuario.
        em.getTransaction().begin();
        em.persist (establecimiento);
        em.getTransaction().commit();
    }
    @Override
    public void insertar(Ticket ticket) throws DAOInsertarException {
        // Transacción para insertar el usuario.
        em.getTransaction().begin();
        em.persist (ticket);
        em.getTransaction().commit();
    }

    // Modificar
    @Override
    public void modificar(Usuario usuario) throws DAOModificarException {
        // Devuelve el la ID del Usuario a modificar.
        Usuario usr = em.find(Usuario.class, usuario.getId());
        
        // Machaca los nuevos valores.
        em.getTransaction().begin();
        usr.setCorreo (usuario.getCorreo());
        usr.setContrasenia (usuario.getContrasenia());
        usr.setUbicacion (usuario.getUbicacion());
        em.getTransaction().commit();
    }
    @Override
    public void modificar(Establecimiento establecimiento) throws DAOModificarException {
        // Devuelve el la ID del Usuario a modificar.
        Establecimiento estab = em.find(Establecimiento.class, establecimiento.getId());
        
        // Machaca los nuevos valores.
        em.getTransaction().begin();
        estab.setNombre(establecimiento.getNombre());
        estab.setPersonasActuales(establecimiento.getPersonasActuales());
        estab.setAforoMaximo(establecimiento.getAforoMaximo());
        estab.setUbicacion(establecimiento.getUbicacion());
        em.getTransaction().commit();
    }
    @Override
    public void modificar(Ticket ticket) throws DAOModificarException {
        // Devuelve el la ID del Usuario a modificar.
        Ticket tick = em.find(Ticket.class, ticket.getId());
        
        // Machaca los nuevos valores.
        em.getTransaction().begin();
        tick.setEstab(ticket.getEstab());
        em.getTransaction().commit();
    }

    // Eliminar
    @Override
    public void eliminar(Usuario usuario) throws DAOEliminarException {
        Usuario usr = em.find (Usuario.class, usuario.getId());
        
        em.getTransaction().begin();
        em.remove (usr);
        em.getTransaction().commit();
    }
    @Override
    public void eliminar(Establecimiento establecimiento) throws DAOEliminarException {
        Establecimiento estab = em.find (Establecimiento.class, establecimiento.getId());
        
        em.getTransaction().begin();
        em.remove (estab);
        em.getTransaction().commit();
    }
    @Override
    public void eliminar(Ticket ticket) throws DAOEliminarException {
        Ticket tick = em.find (Ticket.class, ticket.getId());
        
        em.getTransaction().begin();
        em.remove (tick);
        em.getTransaction().commit();
    }
    
    // ¿Existe?
    @Override
    public boolean existe(Usuario usuario) {
        boolean ret = false; // Valor de retorno.
        try {
            consultarTodosLosUsuarios();
        } catch (DAOUsuarioException ex) {
            System.err.println("Los usuarios no pudieron ser consultados: "+ ex.getMessage());
        }
        
        for (Usuario u: usuarios) {
            if (u.getId() == usuario.getId())
                ret = true;
        }
        
        return ret;
    }
    @Override
    public boolean existe(Establecimiento establecimiento) {
        boolean ret = false; // Valor de retorno.
        try {
            consultarTodosLosEstablecimientos();
        } catch (DAOEstablecimientoException ex) {
            System.err.println("Los establecimientos no pudieron ser consultados: "+ ex.getMessage());
        }
        
        for (Establecimiento e: establecimientos) {
            if (e.getId() == establecimiento.getId())
                ret = true;
        }
        
        return ret;
    }
    @Override
    public boolean existe(Ticket ticket) {
        boolean ret = false; // Valor de retorno.
        try {
            consultarTodosLosTickets();
        } catch (DAOTicketException ex) {
            System.err.println("Los tickets no pudieron ser consultados: "+ ex.getMessage());
        }
        
        for (Ticket t: tickets) {
            if (t.getId() == ticket.getId())
                ret = true;
        }
        
        return ret;
    }

    @Override
    public void exportarBBDDJSON(String rutaFichero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportarBBDDXML(String rutaFichero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tablas importarBBDDJSON(String rutaDirectorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tablas importarBBDDXML(String rutaDirectorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
