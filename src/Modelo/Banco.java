/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Excepciones.NoExisteSucursal;
import Excepciones.SucursalExistente;
import Excepciones.TieneClientes;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class Banco {
    private ArrayList<Sucursal> sucursales;
    private String nombre;
    private double bolsa;
    
    public Banco(){
        
    }
    
    public Banco(String n){
        this.nombre = n;
        this.sucursales = new ArrayList<>();
        this.bolsa = calcBolsa();
    }
    
    public double calcBolsa(){
        double suma = 0;
        for(Sucursal suc: this.sucursales){
            suma+=suc.getSaldo();
        }
        return suma;
    }
    
    public double getBolsa(){
        return this.bolsa;
    }
    
    public ArrayList<Sucursal> getSucursales(){
        return this.sucursales;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public Sucursal buscarSucursal(int c){
        Sucursal s = null;
        for(Sucursal suc : this.sucursales){
            if(suc.getCod() == c){
                s = suc;
            }
        }
        return s;
    }
    
    public void borrarSucursal(Sucursal s) throws NoExisteSucursal, TieneClientes{
        if(buscarSucursal(s.getCod()) != null){
            if(s.getClientes().size() == 0){
                this.sucursales.remove(s);
            }else{
                throw new TieneClientes(s);
            }
        }else{
            throw new NoExisteSucursal(s);
        }
    }
    
    public void añadirSucursal(Sucursal s) throws SucursalExistente{
        if(buscarSucursal(s.getCod()) == null){
            sucursales.add(s);
        }else{
            throw new SucursalExistente(s);
        }
    }
    
    @Override
    public String toString(){
        CuentaCorriente cc;
        CuentaVivienda cv;
        FondoInversion fi;
        String datos = calcBolsa()+"€:\nNúmero de sucursales: "+this.sucursales.size();
        for(Sucursal s : this.sucursales){
            datos += "\n\t Sucursal "+s.getCod()+" ("+s.getSaldo()+"€):";
            if(s.getClientes().size() > 0){
            for(Cliente cl : s.getClientes()){
                if(cl.getNumC() > 0){
                datos += "\n\t\t"+cl.getDni()+" tiene "+cl.getNumC();
                for(Cuenta c : cl.getCuentas()){
                    if(c instanceof CuentaCorriente){
                        cc = (CuentaCorriente) c;
                        datos += "\n\t\t\tCuenta Corriente: "+cc.getSaldo()+"€.";
                    }else if(c instanceof CuentaVivienda){
                        cv = (CuentaVivienda) c;
                        datos += "\n\t\t\tCuenta Vivienda: "+cv.getSaldo()+"€.";
                    }else if(c instanceof FondoInversion){
                        fi = (FondoInversion) c;
                        datos += "\n\t\t\tFondo Inversión: "+fi.getSaldo()+"€.";
                    }
                }
                }else{
                    datos += "\n\t\t"+cl.getDni()+" no tiene ninguna cuenta.";
                }
            }
            }else{
                datos += " no tiene clientes.";
            }
        }
        return datos;
    }
    
}
