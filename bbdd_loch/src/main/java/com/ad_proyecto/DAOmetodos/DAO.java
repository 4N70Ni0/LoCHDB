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

/**
 *
 * @author Antonio
 */
public interface DAO {
    
    void crearConexion() throws DAOConnectionException;
    void cerrarConexion() throws DAOConnectionException;

    Usuario consultar(Usuario usuario) throws DAOUsuarioException;
    Establecimiento consultar(Establecimiento establecimiento) throws DAOEstablecimientoException;
    Ticket consultar(Ticket ticket) throws DAOTicketException;

    void insertar(Usuario usuario) throws DAOInsertarException;
    void insertar(Establecimiento establecimiento) throws DAOInsertarException;
    void insertar(Ticket ticket) throws DAOInsertarException;

    void modificar(Usuario usuario) throws DAOModificarException;
    void modificar(Establecimiento establecimiento) throws DAOModificarException;
    void modificar(Ticket ticket) throws DAOModificarException;

    void eliminar(Usuario usuario) throws DAOEliminarException;
    void eliminar(Establecimiento establecimiento) throws DAOEliminarException;
    void eliminar(Ticket ticket) throws DAOEliminarException;

    List<Usuario> consultarTodosLosUsuarios() throws DAOUsuarioException;
    List<Establecimiento> consultarTodosLosEstablecimientos() throws DAOEstablecimientoException;
    List<Ticket> consultarTodosLosTickets() throws DAOTicketException;

    void eliminarTodosLosUsuarios() throws DAOUsuarioException;
    void eliminarTodosLosEstablecimientos() throws DAOEstablecimientoException;
    void eliminarTodosLosTickets() throws DAOTicketException;

    boolean existe(Usuario usuario);
    boolean existe(Establecimiento establecimiento);
    boolean existe(Ticket ticket);

    void exportarBBDDJSON(String rutaDirectorio);
    Tablas importarBBDDJSON(String rutaDirectorio);
    
    void exportarBBDDXML(String rutaFichero);
    Tablas importarBBDDXML(String rutaDirectorio);
}
