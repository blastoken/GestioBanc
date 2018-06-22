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
public class FondoInversion extends Cuenta{
    protected boolean bloqueada;
    
    public FondoInversion(){
        super();
        this.bloqueada = false;
    }
    public FondoInversion(int n, double s, int p){
        super(n,s,0.34,p);
        this.bloqueada = false;
    }
    public boolean getBlocked(){
        return this.bloqueada;
    }
    public void bloquear(){
        this.bloqueada = true;
    }
}
