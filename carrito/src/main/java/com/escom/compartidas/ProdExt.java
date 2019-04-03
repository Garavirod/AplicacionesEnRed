/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.compartidas;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * @author sandu
 */
public class ProdExt implements Externalizable {
    private String nombre;
    private String descripcion;
    private float precio;
    private int stockDisponible;
    private String nombreImagen;
    
    
 
    public ProdExt(String nombre, String descripcion, float precio, int stockDisponible, String nombreImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.nombreImagen = nombreImagen;
    }    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the stockDisponible
     */
    public int getStockDisponible() {
        return stockDisponible;
    }

    /**
     * @param stockDisponible the stockDisponible to set
     */
    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    /**
     * @return the nombreImagen
     */
    public String getNombreImagen() {
        return nombreImagen;
    }

    /**
     * @param nombreImagen the nombreImagen to set
     */
    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }


    public ProdExt() {
        //System.out.println("Creando Usuario");
    }
    

    
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getNombre());
        out.writeObject(getDescripcion());
        out.writeObject(getPrecio());
        out.writeObject(getStockDisponible());
        out.writeObject(getNombreImagen());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setNombre((String)in.readObject());
        setDescripcion((String)in.readObject());
        setPrecio((float)in.readObject());
        setStockDisponible((int)in.readObject());
        setNombreImagen((String)in.readObject());
        
    }
    
    public void muestraUsuario(){
        StringBuilder sb = new StringBuilder();
        sb.append("nombre: ").append(getNombre());
        sb.append("descripcion: ").append(getDescripcion());
        sb.append("precio: ").append(getPrecio());
        sb.append("stock: ").append(getStockDisponible());
        sb.append("nombreImagen: ").append(getNombreImagen());
        System.out.println(sb.toString());
    }
    
}
