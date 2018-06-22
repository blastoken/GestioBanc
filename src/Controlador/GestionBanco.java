/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.CuentaExistente;
import Excepciones.NoExisteCuenta;
import Excepciones.clienteExistente;
import Excepciones.SucursalExistente;
import Excepciones.TieneClientes;
import Excepciones.TieneCuentas;
import Modelo.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import static sun.text.normalizer.UTF16.append;

/**
 *
 * @author joshu
 */
public class GestionBanco {
    static Banco b;
    static File rutaBanco = new File("src/Banco");

    public static void borrarSucursal(Sucursal s) throws TieneClientes {
       File[] sucursales = rutaBanco.listFiles(), datos;
       for(File suc: sucursales){
           if(suc.getName().equals(s.getCod()+"")){
               datos = suc.listFiles();
               if(datos.length == 1){
               for(File d: datos){
                   if(d.getName().equals("datos.csv")){
                       d.delete();
                   }
               }
               suc.delete();
               }else{
                   throw new TieneClientes(s);
               }
           }
       }
    }

    public static double getSaldoSucursal(Sucursal s) throws FileNotFoundException {
        File datos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/datos.csv");
        String[] dades;
        double saldo;
        Scanner sc = new Scanner(datos);
        dades = sc.nextLine().split(";");
        saldo = Double.parseDouble(dades[2]);
        return saldo;
    }
    
    public GestionBanco() throws FileNotFoundException, SucursalExistente, clienteExistente, CuentaExistente{
        CargarDatosBanco();
    }
    
    public Banco getBanco(){
        return b;
    }
    
    public static void añadirSucursal(Sucursal s) throws SucursalExistente, FileNotFoundException, IOException{
        b.añadirSucursal(s);
        PrintWriter pw;
        File novaSuc = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()), datos;
        novaSuc.mkdir();
        datos = new File(novaSuc.getAbsolutePath()+"/datos.csv");
        datos.createNewFile();
        pw = new PrintWriter(datos);
        pw.println(s.getCod()+";"+s.getLoc()+";"+s.getSaldo()+";");
        pw.close();
    }
    
    public static void guardarAccion(Sucursal s, Cliente c, int nc, String accion , String saldo) throws IOException{
        File movimientos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/"+nc+"Mov.csv");
        Scanner sc;
        String cont="";
        if(!movimientos.exists()){
            movimientos.createNewFile();
        }
        sc = new Scanner(movimientos);
        while(sc.hasNext()){
            cont += sc.nextLine();
        }
        sc.close();
        PrintWriter wr = new PrintWriter(movimientos); 
        if(cont.equals("")){
            wr.println(nc+";"+accion+";"+saldo+"€;"+obtenerFecha()+";");
        }else{
            wr.println(cont+"&"+nc+";"+accion+";"+saldo+"€;"+obtenerFecha()+";");
        }
        wr.close();
    }
    
    public static String obtenerFecha(){
        Calendar fecha = Calendar.getInstance();
        int min = fecha.get(Calendar.MINUTE);
        String minutos;
        if(min < 10){
            minutos = "0"+min;
        }else{
            minutos = ""+min;
        }
        String forFecha = fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR)+" - "+fecha.get(Calendar.HOUR_OF_DAY)+":"+minutos;
        return forFecha;
    }
    
    public static ArrayList<String> getMovimientos(Sucursal s, Cliente c, int nc) throws FileNotFoundException, IOException{
        ArrayList<String> datos = new ArrayList<>();
        String linea;
        String[] linies;
        File movimientos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/"+nc+"Mov.csv");
        BufferedReader in = new BufferedReader(new FileReader(movimientos));
        if(movimientos.exists()){
            linea = in.readLine();
            linies = linea.split("&");
            for(String dades : linies){
                datos.add(dades);
            }
            in.close();
            return datos;
        }
        return null;
    }
    
    public static void modSaldoCuenta(Sucursal s, Cliente c, int nc, double saldo, int puntos) throws FileNotFoundException, IOException{
        File cuenta = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/"+nc+".csv");
        Scanner sc = new Scanner(cuenta);
        String linea;
        String[] datos;
        PrintWriter pw;
        linea = sc.nextLine();
        sc.close();
        datos = linea.split(";");
        cuenta.delete();
        cuenta.createNewFile();
        pw = new PrintWriter(cuenta);
        pw.println(datos[0]+";"+datos[1]+";"+saldo+";"+puntos+";");
        pw.close();
    }
    
    public static void modSaldoSucursal(Sucursal s) throws FileNotFoundException, IOException{
        File sucursal = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/datos.csv");
        Scanner sc = new Scanner(sucursal);
        String linea;
        String[] datos;
        PrintWriter pw;
        linea = sc.nextLine();
        sc.close();
        datos = linea.split(";");
        sucursal.delete();
        sucursal.createNewFile();
        pw = new PrintWriter(sucursal);
        pw.println(datos[0]+";"+datos[1]+";"+s.saldoSuc()+";");
        pw.close();
    }
    
    public static void borrarCliente(Sucursal s, Cliente c) throws TieneCuentas{
        File client = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()), datos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/datos.csv");
        File[] arCli = client.listFiles();
        if(arCli.length == 1){
            datos.delete();
            client.delete();
        }else{
            throw new TieneCuentas(c);
        }
    }
    
    public static void borrarCuenta(Sucursal s, Cliente c, int numCuenta) throws NoExisteCuenta{
        File cuenta = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/"+numCuenta+".csv"), movimientos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/"+numCuenta+"Mov.csv");
        if(cuenta.exists()){
            cuenta.delete();
            if(movimientos.exists()){
                movimientos.delete();
            }
        }else{
            throw new NoExisteCuenta("No se ha encontrado el fichero de la cuenta "+numCuenta);
        }
    }
    
    public static void modificarCliente(Sucursal s, Cliente c) throws IOException{
        File datos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/datos.csv");
        PrintWriter pw;
        datos.delete();
        datos.createNewFile();
        pw = new PrintWriter(datos);
        pw.println(c.getDni()+";"+c.getNom()+";"+c.getAp()+";"+c.getDir()+";"+c.getTlf()+";"+c.getPin()+";");
        pw.close();
    }
    
    public static void añadirCliente(Sucursal s, Cliente c) throws IOException{
        File client = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()), datos = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/datos.csv");
        PrintWriter pw;
        client.mkdir();
        datos.createNewFile();
        pw = new PrintWriter(datos);
        pw.println(c.getDni()+";"+c.getNom()+";"+c.getAp()+";"+c.getDir()+";"+c.getTlf()+";"+c.getPin()+";");
        pw.close();
    }
    
    public static void añadirCuenta(Sucursal s, Cliente c, int numCuenta, String tipo) throws IOException{
        File cuenta = new File(rutaBanco.getAbsolutePath()+"/"+s.getCod()+"/"+c.getDni()+"/"+numCuenta+".csv");
        PrintWriter pw;
        cuenta.createNewFile();
        pw = new PrintWriter(cuenta);
        pw.println(tipo+";"+numCuenta+";"+0+";"+0+";");
        pw.close();
    }
    
    public static void CargarDatosBanco() throws FileNotFoundException, SucursalExistente, clienteExistente, CuentaExistente{
        File[] dirSucursales = rutaBanco.listFiles(),dirClientes, dirCuentas;
        File infoSucursal, infoCliente;
        Banco bank = new Banco("Banco Dam");
        Sucursal s = null;
        Cliente c;
        CuentaCorriente cc;
        CuentaVivienda cv;
        FondoInversion fi;
        Scanner sc, scC = null, scCu;
        String linea;
        String[] datos;
        if(dirSucursales.length > 0){
        for(File sucursal : dirSucursales){
            if(sucursal.isDirectory()){
                infoSucursal = new File(sucursal.getAbsolutePath()+"\\datos.csv");
                sc = new Scanner(infoSucursal);
                linea = sc.nextLine();
                datos = linea.split(";");
                sc.close();
                s = new Sucursal(Integer.parseInt(datos[0]),datos[1],Double.parseDouble(datos[2]));
                dirClientes = sucursal.listFiles();
                if(dirClientes.length > 1){
                for(File cliente : dirClientes){
                    if(cliente.isDirectory()){
                        infoCliente = new File(cliente.getAbsolutePath()+"\\datos.csv");
                        scC = new Scanner(infoCliente);
                        linea = ""+scC.nextLine();
                        datos = linea.split(";");
                        scC.close();
                        c = new Cliente(datos[0],datos[1],datos[2],datos[3],datos[4],Integer.parseInt(datos[5]));
                        dirCuentas = cliente.listFiles();
                        if(dirCuentas.length > 1){
                        for(File cuenta : dirCuentas){
                            if(cuenta.isFile()){
                                scCu = new Scanner(cuenta);
                                linea = scCu.nextLine();
                                datos = linea.split(";");
                                scCu.close();
                                switch (datos[0]) {
                                    case "CC":
                                        cc = new CuentaCorriente(Integer.parseInt(datos[1]),Double.parseDouble(datos[2]),Integer.parseInt(datos[3]));
                                        c.altaCuenta(cc);
                                        break;
                                    case "CV":
                                        cv = new CuentaVivienda(Integer.parseInt(datos[1]),Double.parseDouble(datos[2]),Integer.parseInt(datos[3]));
                                        c.altaCuenta(cv);
                                        break;
                                    case "FI":
                                        fi = new FondoInversion(Integer.parseInt(datos[1]),Double.parseDouble(datos[2]),Integer.parseInt(datos[3]));
                                        c.altaCuenta(fi);
                                        break;
                                }
                            }
                        }
                        }
                        s.añadirCliente(c);
                    }
                }
                }
            }
                bank.añadirSucursal(s);
        }
        }
        GestionBanco.b = bank;
    }
}
