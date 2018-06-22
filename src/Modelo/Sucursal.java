/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Excepciones.clienteExistente;
import Excepciones.NoExisteCliente;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class Sucursal {
    int codSuc;
    String localidad;
    ArrayList<Cliente> clientes;
    double saldo;
    
    public Sucursal(){
        
    }
    
    public Sucursal(int c, String l, double s){
        this.codSuc = c;
        this.localidad = l;
        this.clientes = new ArrayList<>();
        this.saldo = s;
    }
    
    public ArrayList<Cliente> getClientes(){
        return this.clientes;
    }
    
    public void acojerDinero(double n){
        this.saldo += n;
    }
    
    public void pedirPrestamo(int n){
        this.saldo -=n;
    }
    
    public double saldoSuc(){
        return this.saldo;
    }
    
    public double getSaldo(){
        return this.saldo+calcDineroClientes();
    }
    
    public double calcDineroClientes(){
        double suma = 0;
        for(Cliente cli : this.clientes){
            suma += cli.calcDinero();
        }
        return suma;
    }
    
    public void setSaldo(double s){
        this.saldo = s;
    }
    public String getLoc(){
        return this.localidad;
    }
    
    public int getCod(){
        return this.codSuc;
    }
    
    public void genGanancias(){
        int g, r;
        int[] random ={40000,70000,80000, 120000, 210000};
        r = (int) (Math.random() * (5 - 0) + 0);
        g = random[r];
        this.saldo = g;
    }
    
    public void borrarCliente(Cliente c) throws NoExisteCliente{
        if(buscarCliente(c.getDni()) != null){
            this.clientes.remove(c);
        }else{
            throw new NoExisteCliente(c);
        }
    }
    
    public void modificarCliente(Cliente c) throws NoExisteCliente{
        if(buscarCliente(c.getDni()) != null){
            for(Cliente cl : this.clientes){
                if(cl.getDni().equals(c.getDni())){
                    cl.setDatos(c.getNom(),c.getAp(),c.getDir(),c.getTlf());
                }
            }
        }else{
            throw new NoExisteCliente(c);
        }
    }
    
    public void añadirCliente(Cliente c) throws clienteExistente{
        if(buscarCliente(c.getDni()) == null){
            this.clientes.add(c);
        }else{
            throw new clienteExistente(c);
        }
    }
    
    public Cliente buscarCliente(String d){
        Cliente c = null;
        for(Cliente cl: this.clientes){
            if(cl.getDni().equalsIgnoreCase(d)){
                c = cl;
            }
        }
        return c;
    }
    
}
