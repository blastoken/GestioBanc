/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Excepciones.CuentaExistente;
import Excepciones.NoExisteCuenta;
import java.util.ArrayList;
/**
 *
 * @author joshu
 */
public class Cliente {
    String dni, nombre, apellidos, direccion, telefono;
    int pin;
    ArrayList<Cuenta> cuentas;
    
    public Cliente(){
        
    }
    
    public Cliente(String dni, String nombre, String apellidos, String direccion, String telefono, int p){
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.pin = p;
        this.cuentas = new ArrayList<>();
    }
    
    public void setDatos(String nom, String ap, String dir, String tlf){
        this.nombre = nom;
        this.apellidos = ap;
        this.direccion = dir;
        this.telefono = tlf;
    }
    
    public int getPin(){
        return this.pin;
    }
    
    public boolean compPin(int p){
        if(this.pin == p){
            return true;
        }
        return false;
    }
    
    public String getTlf(){
        return this.telefono;
    }
    
    public String getAp(){
        return this.apellidos;
    }
    
    public String getDir(){
        return this.direccion;
    }
    
    public String getNom(){
        return this.nombre;
    }
    
    public int getNumC(){
        return this.cuentas.size();
    }
    
    public ArrayList<Cuenta> getCuentas(){
        return this.cuentas;
    }
    
    public double calcDinero(){
        double suma = 0;
        for(Cuenta cue : this.cuentas){
            suma += cue.consultarSaldo();
        }
        return suma;
    }
    
    public void altaCuenta(Cuenta c) throws CuentaExistente{
        if(buscarCuenta(c.getNumCuenta()) == null){
            cuentas.add(c);
        }else{
            throw new CuentaExistente(c.getNumCuenta());
        }
    }
    
    public Cuenta buscarCuenta(int n){
        Cuenta c = null;
        for(Cuenta cue : this.cuentas){
            if(cue.getNumCuenta() == n){
                c = cue;
            }
        }
        return c;
    }
    
    public void borrarCuenta(Cuenta c) throws NoExisteCuenta{
        if(buscarCuenta(c.getNumCuenta()) != null){
            this.cuentas.remove(c);
        }else{
            throw new NoExisteCuenta("La cuenta "+c.getNumCuenta()+" no existe");
        }
    }
    
    public String getDni(){
        return this.dni;
    }
    
    public double conSaldo(int n){
        for(int i = 0; i < cuentas.size(); i++){
            if(cuentas.get(i).getNumCuenta() == n){
                return cuentas.get(i).consultarSaldo();
            }
        }
        return 0;
    }
    
    public int conPuntos(int n){
        for(int i = 0; i < cuentas.size(); i++){
            if(cuentas.get(i).getNumCuenta() == n){
                return cuentas.get(i).consultarPuntos();
            }
        }
        return 0;
    }
    
    public boolean revSacarDinero(int n, double dinero){
        double interes;
        FondoInversion f;
        for(int i = 0; i < cuentas.size(); i++){
            if(cuentas.get(i).getNumCuenta() == n){
                interes = cuentas.get(i).getInteres();
                if(cuentas.get(i).getInteres() == 0.1){
                    if(cuentas.get(i).consultarSaldo() >= dinero){
                        cuentas.get(i).sacarDinero(dinero);
                        return true;
                    }else{
                        System.out.println("El máximo dinero que se puede sacar de esta cuenta son "+cuentas.get(i).consultarSaldo()+"€.");
                        return false;
                    }
                }else if(cuentas.get(i).getInteres() == 0.2){
                    System.out.println("De esta cuenta no se puede sacar dinero.");
                    return false;
                }else if (cuentas.get(i).getInteres() == 0.34){
                    f = (FondoInversion) cuentas.get(i);
                    if((cuentas.get(i).consultarSaldo()+500) >= dinero){
                        cuentas.get(i).sacarDinero(dinero);
                        return true;
                    }else if((cuentas.get(i).consultarSaldo()+500) < dinero){
                        cuentas.get(i).sacarDinero(dinero);
                        f.bloquear();
                        //cuentas.get(i).bloquear();
                        //cuentas.get(i) = (Cuenta) f;
                        System.out.println("Ha excedido el límite de la cuenta.");
                        System.out.println("La cuenta ha sido bloqueada.");
                    }else if(f.getBlocked()){
                        System.out.println("La cuenta está bloqueada.");
                    }
            }
            }else{
                System.out.println("No se ha encontrado la cuenta...");
            }
        }
        return false;
    }
    
}
