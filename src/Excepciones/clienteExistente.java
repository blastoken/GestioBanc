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
public class clienteExistente extends Exception {

    /**
     * Creates a new instance of <code>ClienteExistente</code> without detail
     * message.
     */
    Cliente c;
    public clienteExistente(Cliente cl) {
        this.c = cl;
    }

    /**
     * Constructs an instance of <code>ClienteExistente</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @return 
     */
    @Override
    public String toString(){
        return "El cliente "+this.c.getDni()+" ya está registrado";
    }
}
