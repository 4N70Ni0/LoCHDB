/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.DAOmetodos;

import com.ad_proyecto.bbdd_loch.Tablas;
import com.ad_proyecto.bbdd_loch.Establecimiento;
import com.ad_proyecto.bbdd_loch.Ticket;
import com.ad_proyecto.bbdd_loch.Usuario;
import com.ad_proyecto.exceptions.DAOConnectionException;
import com.ad_proyecto.exceptions.DAOEliminarException;
import com.ad_proyecto.exceptions.DAOEstablecimientoException;
import com.ad_proyecto.exceptions.DAOInsertarException;
import com.ad_proyecto.exceptions.DAOModificarException;
import com.ad_proyecto.exceptions.DAOTicketException;
import com.ad_proyecto.exceptions.DAOUsuarioException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author Antonio
 */
public class DAOImplementacionHibernate implements DAO {
    private List<Usuario> usuarios;
    private List<Establecimiento> establecimientos;
    private List<Ticket> tickets;
    private int estabPos = 0; // Posición de la consulta actual en la lista 'establecimientos'.
    
    private Session sesion;
    private SessionFactory factory;
    
    //
    // Conexión y cerrar conexión.
    //
    @Override
    public void crearConexion() throws DAOConnectionException {
        factory = new Configuration().configure().buildSessionFactory();
        sesion = factory.openSession();
        
        if (sesion == null) {
            throw new DAOConnectionException ("No se ha conectado a Hibernate.");
        }
    }
    
    @Override
    public void cerrarConexion() throws DAOConnectionException {
        sesion.close();
        
        if (sesion != null) {
            throw new DAOConnectionException ("No se ha podido cerrar la conexión con Hibernate.");
        }
    }

    //
    // Consultas.
    //
    // Devuelve el objeto completo a través de la ID del mismo.
    @Override
    public Usuario consultar(Usuario usuario) throws DAOUsuarioException {
        Query consulta = sesion.createQuery ("SELECT e FROM Usuario e WHERE e.id = :id");
        // Devuelve el objeto completo.
        return (Usuario) consulta.setParameter ("id", usuario.getId()).getSingleResult();
    }
    @Override
    public Establecimiento consultar(Establecimiento establecimiento) throws DAOEstablecimientoException {
        Query consulta = sesion.createQuery ("SELECT e FROM Establecimiento e WHERE e.id = :id");
        // Devuelve el objeto completo.
        return (Establecimiento) consulta.setParameter ("id", establecimiento.getId()).getSingleResult();
    }
    @Override
    public Ticket consultar(Ticket ticket) throws DAOTicketException {
        Query consulta = sesion.createQuery ("SELECT e FROM Ticket e WHERE e.id = :id");
        // Devuelve el objeto completo.
        return (Ticket) consulta.setParameter ("id", ticket.getId()).getSingleResult();
    }
    
    // Actualizar la lista 'usuarios' además de devolverla.
    @Override
    public List<Usuario> consultarTodosLosUsuarios() throws DAOUsuarioException {
        Query consulta = sesion.createQuery ("FROM Usuario");
        
        usuarios = consulta.list();
        
        return usuarios;
    }
    // Actualizar la lista 'establecimientos' además de devolverla.
    @Override
    public List<Establecimiento> consultarTodosLosEstablecimientos() throws DAOEstablecimientoException {
        Query consulta = sesion.createQuery ("FROM Establecimiento");
        
        establecimientos = consulta.list();
        
        return establecimientos;
    }
    // Actualizar la lista 'tickets' además de devolverla.
    @Override
    public List<Ticket> consultarTodosLosTickets() throws DAOTicketException {
        Query consulta = sesion.createQuery ("FROM Ticket");
        
        tickets = consulta.list();
        for (Ticket t: tickets) {
            t.setIdEstab(t.getEstab().getId());
        }
        
        return tickets;
    }
    
    // Eliminar todos los usuarios.
    @Override
    public void eliminarTodosLosUsuarios() throws DAOUsuarioException {
        Query consulta = sesion.createQuery ("FROM Usuario");
        List<Usuario> usr = consulta.list();
        
        for (Usuario u: usr) {
            sesion.beginTransaction();
            sesion.delete (u);
            sesion.getTransaction().commit();
        }
    }
    
    // Eliminar todos los establecimientos.
    @Override
    public void eliminarTodosLosEstablecimientos() throws DAOEstablecimientoException {
        Query consulta = sesion.createQuery ("FROM Establecimiento");
        List<Establecimiento> estab = consulta.list();
        
        for (Establecimiento e: estab) {
            sesion.beginTransaction();
            sesion.delete (e);
            sesion.getTransaction().commit();
        }
    }
    
    // Eliminar todos los tickets.
    @Override
    public void eliminarTodosLosTickets() throws DAOTicketException {
        Query consulta = sesion.createQuery ("FROM Ticket");
        List<Ticket> tick = consulta.list();
        
        for (Ticket t: tick) {
            sesion.beginTransaction();
            sesion.delete (t);
            sesion.getTransaction().commit();
        }
    }
    
    
    //
    // Funciones.
    //
    // Insertar
    @Override
    public void insertar(Usuario Usuario) throws DAOInsertarException {
        sesion.beginTransaction();
        sesion.save (Usuario);
        sesion.getTransaction().commit();
    }
    @Override
    public void insertar(Establecimiento establecimiento) throws DAOInsertarException {
        sesion.beginTransaction();
        sesion.save (establecimiento);
        sesion.getTransaction().commit();
    }
    @Override
    public void insertar(Ticket ticket) throws DAOInsertarException {
        sesion.beginTransaction();
        sesion.save (ticket);
        sesion.getTransaction().commit();
    }
    
    // Modificar
    @Override
    public void modificar(Usuario usuario) throws DAOModificarException {
        Query consulta = sesion.createQuery ("SELECT e FROM Usuario e WHERE e.id = :id");
        // Devuelve el objeto a modificar.
        Usuario usr = (Usuario) consulta.setParameter ("id", usuario.getId()).getSingleResult();
        
        // Guarda los nuevos valores.
        usr.setCorreo(usuario.getCorreo());
        usr.setContrasenia(usuario.getContrasenia());
        usr.setUbicacion (usuario.getUbicacion());
        
        sesion.beginTransaction();
        sesion.update (usr);
        sesion.getTransaction().commit();
    }
    @Override
    public void modificar(Establecimiento establecimiento) throws DAOModificarException {
        Query consulta = sesion.createQuery ("SELECT e FROM Establecimiento e WHERE e.id = :id");
        Establecimiento estab = (Establecimiento) consulta.setParameter ("id", establecimiento.getId()).getSingleResult();
        
        estab.setNombre (establecimiento.getNombre());
        estab.setPersonasActuales (establecimiento.getPersonasActuales());
        estab.setAforoMaximo (establecimiento.getAforoMaximo());
        estab.setUbicacion (establecimiento.getUbicacion());
        
        sesion.beginTransaction();
        sesion.update (estab);
        sesion.getTransaction().commit();
    }
    @Override
    public void modificar(Ticket ticket) throws DAOModificarException {
        Query consulta = sesion.createQuery ("SELECT e FROM Ticket e WHERE e.id = :id");
        Ticket tick = (Ticket) consulta.setParameter ("id", ticket.getId()).getSingleResult();
        
        tick.setEstab (ticket.getEstab());
        
        sesion.beginTransaction();
        sesion.update (tick);
        sesion.getTransaction().commit();
    }

    // Eliminar
    @Override
    public void eliminar(Usuario usuario) throws DAOEliminarException {
        Query consulta = sesion.createQuery ("SELECT e FROM Usuario e WHERE e.id = :id");
        Usuario usr = (Usuario) consulta.setParameter ("id", usuario.getId()).getSingleResult();
        
        sesion.beginTransaction();
        sesion.delete (usr);
        sesion.getTransaction().commit();
    }
    @Override
    public void eliminar(Establecimiento establecimiento) throws DAOEliminarException {
        Query consulta = sesion.createQuery ("SELECT e FROM Establecimiento e WHERE e.id = :id");
        Establecimiento estab = (Establecimiento) consulta.setParameter ("id", establecimiento.getId()).getSingleResult();
        
        sesion.beginTransaction();
        sesion.delete (estab);
        sesion.getTransaction().commit();
    }
    @Override
    public void eliminar(Ticket ticket) throws DAOEliminarException {
        Query consulta = sesion.createQuery ("SELECT e FROM Ticket e WHERE e.id = :id");
        Ticket tick = (Ticket) consulta.setParameter ("id", ticket.getId()).getSingleResult();
        
        sesion.beginTransaction();
        sesion.delete (tick);
        sesion.getTransaction().commit();
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
        
        for (Usuario usr: usuarios) {
            if (usr.getId() == usuario.getId())
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
        
        for (Establecimiento estab: establecimientos) {
            if (estab.getId() == establecimiento.getId())
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
        
        for (Ticket tick: tickets) {
            if (tick.getId() == ticket.getId())
                ret = true;
        }
        
        return ret;
    }
    
    // Exporta la BBDD a JSON.
    @Override
    public void exportarBBDDJSON(String rutaDirectorio) {
        
        // Guardar campos en JSON
        Tablas tablas = null;
        try {
            // Crear el objeto que contiene la lista de cada objeto.
            tablas = new Tablas(consultarTodosLosUsuarios(), consultarTodosLosEstablecimientos(), consultarTodosLosTickets());
        } 
        catch (DAOUsuarioException ex) {
            System.err.println("Error al consultar todos los usuarios: "+ ex.getMessage());
        } 
        catch (DAOEstablecimientoException ex) {
            System.err.println("Error al consultar todos los establecimientos: "+ ex.getMessage());
        } 
        catch (DAOTicketException ex) {
            System.err.println("Error al consultar todos los tickets: "+ ex.getMessage());
        }     
        
        // Devuelve el objeto Tablas con formato JSON.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(tablas);
        
        // Escribe el JSON en un fichero de texto.
        try {
            //gson.toJson(tablas, new FileWriter(rutaDirectorio +"lochdb.json"));
            FileWriter fw = new FileWriter(new File(rutaDirectorio, "lochdb.json"));
            fw.write(json);
            fw.close();
        } catch (IOException ex) {
            System.err.println ("No se pudo escribir en el fichero: "+ ex.getMessage());
        }
    }

    // Importa la BBDD de un fichero JSON.
    @Override
    public Tablas importarBBDDJSON(String rutaDirectorio) {
        String rutaFichero = rutaDirectorio + "/lochdb.json";
        String json = null;
        Tablas tablas = null;
        
        try {
            json = new String (Files.readAllBytes (Paths.get(rutaFichero)));

            tablas = new Gson().fromJson(json, Tablas.class);
        }
        catch (IOException ex) {
            System.err.println ("No se ha podido leer el fichero de JSON: "+ ex.getMessage());
        }
        catch (Exception ex) {
            System.err.println ("No se ha podido convertir JSON a Tablas: "+ ex.getMessage());
        }
        
        System.out.println("=== === No problemo importando el JSON");
        return tablas;
    }
    
    @Override
    public void exportarBBDDXML(String rutaFichero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tablas importarBBDDXML(String rutaFichero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
