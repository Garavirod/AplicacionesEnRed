/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.carrito;

import com.escom.compartidas.Producto;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.util.Pair;

/**
 *
 * @author sandu
 */
public class Ticket {
    private static int contador = 1;
    private  int id;
    private String cabecera;
    private String nombreComercio;
    private String detalle;
    private LinkedList<Producto> productos;
    private float total;
    private String nombreLogo;
    private String codigoTransaccion;
    private String fechaTransaccion;

    public Ticket(String nombreComercio, String detalle, LinkedList<Producto> productos, float total, String nombreLogo, String codigoTransaccion) {
        this.nombreComercio = nombreComercio;
        this.detalle = detalle;
        this.productos = productos;
        this.total = total;
        this.nombreLogo = nombreLogo;
        this.codigoTransaccion = codigoTransaccion;
        this.id = contador;
        contador++;
    }

    public Ticket() {
        this.nombreComercio = new String();
        this.detalle = new String();
        this.productos = new LinkedList<>();
        this.total = (float) 0.0;
        this.nombreLogo = new String();
        this.codigoTransaccion = new String();
        this.id = contador;
        contador++;
    }


    
    
    public void addProducto(Producto prod){
        this.productos.add(prod);
    }
    
    
    public int getId(){
        return this.id;
    }
    

    /**
     * @return the nombreComercio
     */    
    public String getNombreComercio() {
        return nombreComercio;
    }

    /**
     * @param nombreComercio the nombreComercio to set
     */
    public void setNombreComercio(String nombreComercio) {
        this.nombreComercio = nombreComercio;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the productos
     */
    public LinkedList<Producto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(LinkedList<Producto> productos) {
        this.productos = productos;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the nombreLogo
     */
    public String getNombreLogo() {
        return nombreLogo;
    }

    /**
     * @param nombreLogo the nombreLogo to set
     */
    public void setNombreLogo(String nombreLogo) {
        this.nombreLogo = nombreLogo;
    }

    /**
     * @return the codigoTransaccion
     */
    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    /**
     * @param codigoTransaccion the codigoTransaccion to set
     */
    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    /**
     * @return the fechaTransaccion
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * @param fechaTransaccion the fechaTransaccion to set
     */
    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    
    
    public void setCabecera(String c){
        this.cabecera = c;
    }

    @Override
    public String toString() {
        
        String str = new String();
        str+="\n\n-------LISTA DE ARTICULOS--------\n\n";
        str+="id ticket: "+this.id+"\n";
        str+="nombre comercio: "+this.nombreComercio+"\n";
        str+="detalle: "+this.detalle+"\n";
        float total =0f;
        for(Producto p:this.productos){
            total+=(float)p.getPrecio();
            str+=p.getNombre()+" $"+p.getPrecio()+"\n";
        }
        str+="total: $"+total+"\n";
        str+=this.fechaTransaccion+"\n";
        return str;
    }
    
    
}
