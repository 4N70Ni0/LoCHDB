package com.ad_proyecto.bbdd_loch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ad_proyecto.DAOmetodos.DAOImplementacionHibernate;
import com.ad_proyecto.exceptions.DAOConnectionException;
import com.ad_proyecto.exceptions.DAOUsuarioException;

/**
 *
 * @author Antonio
 */
public class Main {
    
    public static void main (String[] args) throws DAOUsuarioException, DAOConnectionException {
        
        DAOImplementacionHibernate hibernate = new DAOImplementacionHibernate();
        Controlador controlador = new Controlador();
    }

}
