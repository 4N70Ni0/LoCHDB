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
public class DAOModificarException extends Exception {
    
    public DAOModificarException (String msg) {
        super ("No se ha podido realizar la modificación: "+ msg);
    }
    
}
