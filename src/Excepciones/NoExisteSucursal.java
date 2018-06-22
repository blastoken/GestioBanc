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
public class NoExisteSucursal extends Exception {

    /**
     * Creates a new instance of <code>NoExisteSucursal</code> without detail
     * message.
     */
    Sucursal s;
    public NoExisteSucursal(Sucursal suc) {
        this.s = suc;
    }

    /**
     * Constructs an instance of <code>NoExisteSucursal</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @return 
     */
    @Override
    public String toString(){
        return "No existe la sucursal nº"+this.s.getCod();
    }
}
