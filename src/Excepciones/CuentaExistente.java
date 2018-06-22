/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author joshu
 */
public class CuentaExistente extends Exception {

    /**
     * Creates a new instance of <code>CuentaExistente</code> without detail
     * message.
     */
    int NC;
    public CuentaExistente(int n) {
        this.NC = n;
    }

    /**
     * Constructs an instance of <code>CuentaExistente</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     * @return 
     */
    @Override
    public String toString(){
        return "Ya existe la cuenta nº"+this.NC;
    }
}
