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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Antonio
 */
@Entity
public class Ticket implements Serializable {
    
    @Id
    private int id;
    // La ID del establecimiento al que hace referencia.
    private transient int id_estab;
    
    // transient --> GSON no lo toma en cuenta.
    
    // Relación 1:(N) con Establecimiento
    @ManyToOne (optional=false)
    @JoinColumn (name="establecimiento_id", nullable=false, updatable=false)
    private Establecimiento estab;
    
    // Relación N:M con Usuario
    // Lado esclavo
    @ManyToMany (targetEntity=com.ad_proyecto.bbdd_loch.Usuario.class, mappedBy="ticket") 
    private Set<Usuario> usuarios;

    
    // Constructor
    public Ticket() {
    }
    public Ticket(int id, Establecimiento estab, Set<Usuario> usuarios) {
        this.id = id;
        this.estab = estab;
        this.usuarios = usuarios;
    }
    public Ticket(int id, Establecimiento estab) {
        this.id = id;
        this.estab = estab;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstab(Establecimiento estab) {
        this.estab = estab;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }

    public Establecimiento getEstab() {
        return estab;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setIdEstab(int id_estab) {
        this.id_estab = id_estab;
    }
    
    public int getIdEstab() {
        return id_estab;
    }
    
    public String toString() {
        return "ID: "+ id;
    }
}
