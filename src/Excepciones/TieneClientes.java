/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

import Modelo.Sucursal;

/**
 *
 * @author joshu
 */
public class TieneClientes extends Exception {

    /**
     * Creates a new instance of <code>TieneClientes</code> without detail
     * message.
     */
    Sucursal s;
    public TieneClientes(Sucursal suc) {
        this.s = suc;
    }

    /**
     * Constructs an instance of <code>TieneClientes</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     * @return 
     */
    @Override
    public String toString(){
        return "La sucursal nº"+this.s.getCod()+" aún tiene clientes";
    }
}
