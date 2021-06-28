/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad_proyecto.exceptions;

/**
 *
 * @author Antonio
 */
public class DAOEstablecimientoException extends Exception {
    
    public DAOEstablecimientoException (String msg) {
        super ("Se ha producido un error en la persistencia del establecimiento: "+ msg);
    }
}
