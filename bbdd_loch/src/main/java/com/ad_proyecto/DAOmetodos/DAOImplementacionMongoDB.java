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
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Antonio
 */
public class DAOImplementacionMongoDB implements DAO {
    private List<Usuario> usuarios = null;
    private List<Establecimiento> establecimientos = null;
    private List<Ticket> tickets = null;
    private MongoCollection<Document> colUsuarios = null;
    private MongoCollection<Document> colEstablecimientos = null;
    private MongoCollection<Document> colTickets = null;
    
    MongoClient mongo = null;
    MongoDatabase db = null;
    //MongoCollection coleccion = null;
    
    @Override
    public void crearConexion() throws DAOConnectionException {
        mongo = new MongoClient("localhost", 27017);
        db = mongo.getDatabase("LoCHDB");
        
        colUsuarios = db.getCollection("Usuario");
        colEstablecimientos = db.getCollection("Establecimiento");
        colTickets = db.getCollection("Ticket");
    }

    @Override
    public void cerrarConexion() throws DAOConnectionException {
        db = null;
        mongo.close();
        mongo = null;
    }
    
    // De documento a Usuario.
    private Usuario documentToUsuario(Document document) {
        Usuario usr = null;
        
        try {
            usr = new Usuario(document.getInteger("id"), 
                    document.getString("correo"), document.getString("contrasenia"), 
                    document.getString("ubicacion"));
        }
        catch (ClassCastException ex) {
            System.err.println("No se ha podido recuperar el Usuario de MongoDB: "+ ex.getMessage());
        }
        
        return usr;
    }
    // De documento a Establecimiento.
    private Establecimiento documentToEstablecimiento(Document document) {
        return new Establecimiento(document.getInteger("id"), document.getString("nombre"), 
                document.getInteger("personas_actuales"), document.getInteger("aforo_maximo"), 
                document.getString("ubicacion"));
    }
    // De documento a Ticket.
    private Ticket documentToTicket(Document doc) {
        Establecimiento estab = null;
        
        try {
            estab = consultar(new Establecimiento(doc.getInteger("id_estab"), "", 0, 0, ""));
        } catch (DAOEstablecimientoException ex) {
            System.err.println("No se pudo consultar el establecimiento");
        }
        
        return new Ticket(doc.getInteger("id"), estab);
    }

    //
    // Consultas
    //
    // Devuelve el objeto completo a través de la ID del mismo.
    @Override
    public Usuario consultar(Usuario usuario) throws DAOUsuarioException {
        Usuario usr = null;
        Document doc = colUsuarios.find(eq("id", usuario.getId())).first();
        
        if (doc != null)
            usr = documentToUsuario(doc);

        return usr;
    }

    @Override
    public Establecimiento consultar(Establecimiento establecimiento) throws DAOEstablecimientoException {
        Establecimiento estab = null;
        Document doc = colEstablecimientos.find(eq("id", establecimiento.getId())).first();
        
        if (doc != null)
            estab = documentToEstablecimiento(doc);

        return estab;
    }

    @Override
    public Ticket consultar(Ticket ticket) throws DAOTicketException {
        Ticket tick = null;
        Establecimiento estab = null;
        Document doc = colTickets.find(eq("id", ticket.getId())).first();
        
        if (doc != null)
            tick = documentToTicket(doc);

        return tick;
    }
    
    //
    // Actualizar las listas
    //
    @Override
    public List<Usuario> consultarTodosLosUsuarios() throws DAOUsuarioException {
        List<Usuario> usr = new ArrayList<Usuario>();
        
        MongoCursor<Document> cursor = colUsuarios.find().iterator();
        while (cursor.hasNext()) {
            usr.add(documentToUsuario(cursor.next()));
        }
        usuarios = usr;
        
        return usuarios;
    }

    @Override
    public List<Establecimiento> consultarTodosLosEstablecimientos() throws DAOEstablecimientoException {
        List<Establecimiento> estab = new ArrayList<Establecimiento>();
        
        MongoCursor<Document> cursor = colEstablecimientos.find().iterator();
        while (cursor.hasNext()) {
            estab.add(documentToEstablecimiento(cursor.next()));
        }
        establecimientos = estab;
        
        return establecimientos;
    }

    @Override
    public List<Ticket> consultarTodosLosTickets() throws DAOTicketException {
        List<Ticket> tick = new ArrayList<Ticket>();
        
        MongoCursor<Document> cursor = colTickets.find().iterator();
        while (cursor.hasNext()) {
            tick.add(documentToTicket(cursor.next()));
        }
        tickets = tick;
        
        return tickets;
    }
    
    

    @Override
    public void insertar(Usuario usuario) throws DAOInsertarException {
        //coleccion = db.getCollection("Usuario");
        Document doc = new Document();
        
        doc.put("id", usuario.getId());
        doc.put("correo", usuario.getCorreo());
        doc.put("contrasenia", usuario.getContrasenia());
        doc.put("ubicacion", usuario.getUbicacion());
        
        colUsuarios.insertOne(doc);
    }

    @Override
    public void insertar(Establecimiento establecimiento) throws DAOInsertarException {
//        coleccion = db.getCollection("Establecimiento");
        Document doc = new Document();
        
        doc.put("id", establecimiento.getId());
        doc.put("nombre", establecimiento.getNombre());
        doc.put("personas_actuales", establecimiento.getPersonasActuales());
        doc.put("aforo_maximo", establecimiento.getAforoMaximo());
        doc.put("ubicacion", establecimiento.getUbicacion());
        
        colEstablecimientos.insertOne(doc);
    }

    @Override
    public void insertar(Ticket ticket) throws DAOInsertarException {
//        coleccion = db.getCollection("Usuario");
        Document doc = new Document();
        
        doc.put("id", ticket.getId());
        doc.put("id_estab", ticket.getEstab().getId());
        
        colTickets.insertOne(doc);   
    }

    @Override
    public void modificar(Usuario usuario) throws DAOModificarException {
        // Lista inmutable al estilo Java 8.
        List<Bson> update = new ArrayList<Bson>();
        update.add(Updates.set("correo", usuario.getCorreo()));
        update.add(Updates.set("contrasenia", usuario.getContrasenia()));
        update.add(Updates.set("ubicacion", usuario.getUbicacion()));
        update = Collections.unmodifiableList(update);
        
        colUsuarios.updateOne(eq("id", usuario.getId()), update);
    }

    @Override
    public void modificar(Establecimiento establecimiento) throws DAOModificarException {
        List<Bson> update = new ArrayList<Bson>();
        update.add(Updates.set("nombre", establecimiento.getNombre()));
        update.add(Updates.set("personas_actuales", establecimiento.getPersonasActuales()));
        update.add(Updates.set("aforo_maximo", establecimiento.getAforoMaximo()));
        update.add(Updates.set("ubicacion", establecimiento.getUbicacion()));
        update = Collections.unmodifiableList(update);
        
        colEstablecimientos.updateOne(eq("id", establecimiento.getId()), update);
    }

    @Override
    public void modificar(Ticket ticket) throws DAOModificarException {
        List<Bson> update = new ArrayList<Bson>();
        update.add(Updates.set("id_estab", ticket.getEstab().getId()));
        update = Collections.unmodifiableList(update);
        
        colTickets.updateOne(eq("id", ticket.getId()), update);
    }

    @Override
    public void eliminar(Usuario usuario) throws DAOEliminarException {
        DeleteResult result = colUsuarios.deleteOne(eq("id", usuario.getId()));
    }

    @Override
    public void eliminar(Establecimiento establecimiento) throws DAOEliminarException {
        colEstablecimientos.deleteOne(eq("id", establecimiento.getId()));
    }

    @Override
    public void eliminar(Ticket ticket) throws DAOEliminarException {
        colTickets.deleteOne(eq("id", ticket.getId()));
    }



    @Override
    public void eliminarTodosLosUsuarios() throws DAOUsuarioException {
        db.getCollection("usuario").drop();
    }

    @Override
    public void eliminarTodosLosEstablecimientos() throws DAOEstablecimientoException {
        db.getCollection("establecimiento").drop();
    }

    @Override
    public void eliminarTodosLosTickets() throws DAOTicketException {
        db.getCollection("ticket").drop();    
    }

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

    @Override
    public void exportarBBDDJSON(String rutaDirectorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tablas importarBBDDJSON(String rutaDirectorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportarBBDDXML(String rutaFichero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tablas importarBBDDXML(String rutaDirectorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
