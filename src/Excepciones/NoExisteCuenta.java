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
public class NoExisteCuenta extends Exception {

    /**
     * Creates a new instance of <code>NoExisteCuenta</code> without detail
     * message.
     */
    String m;
    public NoExisteCuenta(String msg) {
        this.m = msg;
    }

    /**
     * Constructs an instance of <code>NoExisteCuenta</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     * @return 
     */
    @Override
    public String toString(){
        return this.m;
    }
}
