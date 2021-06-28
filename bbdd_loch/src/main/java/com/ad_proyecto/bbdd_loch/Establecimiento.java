/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.bbdd_loch;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Antonio
 */
@Entity
public class Establecimiento implements Serializable {
    
    @Id
    private int id;
    private String nombre;
    private int personasActuales;
    private int aforoMaximo;
    private String ubicacion;
    
    // transient --> GSON no lo toma en cuenta.
    
    // Relación (1):N con Ticket
    @OneToMany (targetEntity=com.ad_proyecto.bbdd_loch.Ticket.class, 
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, 
            mappedBy="establecimiento")
    private Set<Ticket> tickets;
    
    // Relación 1:(N) con Usuario
    @ManyToOne (optional=true)
    @JoinColumn (name="usuario_id", nullable=true, updatable=false)
    private Usuario usuario;
    
    // Relación N:M con Usuario
    @ManyToMany (targetEntity=com.ad_proyecto.bbdd_loch.Usuario.class, mappedBy="establecimiento")
    private Set<Usuario> usuarios;
    
    // Constructor
    public Establecimiento() {
    }

    public Establecimiento(int id, String nombre, int personasActuales, int aforoMaximo, String ubicacion, Set<Ticket> tickets, Usuario usuario, Set<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.personasActuales = personasActuales;
        this.aforoMaximo = aforoMaximo;
        this.ubicacion = ubicacion;
        this.tickets = tickets;
        this.usuario = usuario;
        this.usuarios = usuarios;
    }

    public Establecimiento(int id, String nombre, int personasActuales, int aforoMaximo, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.personasActuales = personasActuales;
        this.aforoMaximo = aforoMaximo;
        this.ubicacion = ubicacion;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPersonasActuales(int personasActuales) {
        this.personasActuales = personasActuales;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPersonasActuales() {
        return personasActuales;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public String toString() {
        return "ID: "+ id +", Nombre: "+ nombre +
                ", Personas actuales: "+ personasActuales +
                ", Aforo maximo: "+ aforoMaximo +", Ubicacion: "+ ubicacion;
    }
}
