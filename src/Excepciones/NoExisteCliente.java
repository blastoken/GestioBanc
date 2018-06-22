/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

import Modelo.Cliente;

/**
 *
 * @author joshu
 */
public class NoExisteCliente extends Exception {

    /**
     * Creates a new instance of <code>NoExisteCliente</code> without detail
     * message.
     */
    Cliente c;
    public NoExisteCliente(Cliente cl) {
        this.c = cl;
    }

    /**
     * Constructs an instance of <code>NoExisteCliente</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     * @return 
     */
    @Override
    public String toString(){
        return "El cliente "+this.c.getNom()+" no existe";
    }
}
