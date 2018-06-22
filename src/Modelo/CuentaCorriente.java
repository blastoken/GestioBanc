/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author joshu
 */
public class CuentaCorriente extends Cuenta{
    public CuentaCorriente(){
        super();
    }
    public CuentaCorriente(int n, double s, int p){
        super(n,s,0.1,p);
    }
        
}

