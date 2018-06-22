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
abstract class Cuenta {
    protected int num_cuenta, puntos;
    protected double saldo, interes, comision;
    
    public Cuenta(){
        
    }
    
    public Cuenta(int n_c, double saldo, double interes, int p){
        this.num_cuenta = n_c;
        this.saldo = saldo;
        this.interes = interes;
        this.comision = 0.6;
        this.puntos = p;
    }
    
    public void transferencia(int n){
        this.saldo += n;
    }
    
    public int getPuntos(){
        return this.puntos;
    }
    
    public void prestamo(int n){
        this.saldo += n;
    }
    
    public void ingresarDinero(double cant){
        this.saldo += cant;
        int p=0;
        if(cant >= 6){
            do{
                cant -=6;
                p++;
            }while(cant >= 6);
            this.puntos += p;
        }
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    
    public double getInteres(){
        return this.interes;
    }
    
    public int getNumCuenta(){
        return this.num_cuenta;
    }
    
    public double getComision(){
        return this.comision;
    }
    
    public void sacarDinero(double cant){
        this.saldo -= cant;
    }
    
    public double consultarSaldo(){
        return this.saldo;
    }
    
    public int consultarPuntos(){
        return this.puntos;
    }
    
    public void setSaldo(double n){
        this.saldo = n;
    }
    
}
