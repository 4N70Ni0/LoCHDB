/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.bbdd_loch;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

/**
 *
 * @author Antonio
 */
@Entity
public class Usuario implements Serializable {
    
    @Id
    private int id;
    private String correo;
    private String contrasenia;
    private String ubicacion;
    
    // transient --> GSON no lo toma en cuenta.
    
    // Relación N:M con Ticket
    // Lado Maestro
    @ManyToMany (targetEntity=com.ad_proyecto.bbdd_loch.Ticket.class)
    private Set<Ticket> tickets;
    
    // Relación (1):N con Establecimiento
    @OneToMany (targetEntity=com.ad_proyecto.bbdd_loch.Establecimiento.class, 
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, 
            mappedBy="usuario")
    private Set<Establecimiento> otm_estabs;
    
    // Relación N:M con Establecimiento    
    // Lado Maestro
    @ManyToMany (targetEntity=com.ad_proyecto.bbdd_loch.Establecimiento.class)
    private Set<Establecimiento> mtm_estabs;

    
    // Constructores
    public Usuario() {
    }

    public Usuario(int id, String correo, String contrasenia, String ubicacion, Set<Ticket> tickets, Set<Establecimiento> otm_estabs, Set<Establecimiento> mtm_estabs) {
        this.id = id;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.ubicacion = ubicacion;
        this.tickets = tickets;
        this.otm_estabs = otm_estabs;
        this.mtm_estabs = mtm_estabs;
    }
    
    public Usuario(int id, String correo, String contrasenia, String ubicacion) {
        this.id = id;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.ubicacion = ubicacion;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setOtm_estabs(Set<Establecimiento> otm_estabs) {
        this.otm_estabs = otm_estabs;
    }

    public void setMtm_estabs(Set<Establecimiento> mtm_estabs) {
        this.mtm_estabs = mtm_estabs;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Set<Establecimiento> getOtm_estabs() {
        return otm_estabs;
    }

    public Set<Establecimiento> getMtm_estabs() {
        return mtm_estabs;
    }
    
}
