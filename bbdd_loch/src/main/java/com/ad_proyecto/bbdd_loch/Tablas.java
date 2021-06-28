/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.bbdd_loch;

import java.util.List;

/**
 *
 * @author Antonio
 */
public class Tablas {
    private List<Usuario> usuarios;
    private List<Establecimiento> establecimientos;
    private List<Ticket> tickets;

    public Tablas(List<Usuario> usuarios, List<Establecimiento> establecimientos, List<Ticket> tickets) {
        this.usuarios = usuarios;
        this.establecimientos = establecimientos;
        this.tickets = tickets;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    
}
