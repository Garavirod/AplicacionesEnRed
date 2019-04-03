/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.compartidas;

import java.io.Serializable;

/**
 *
 * @author sandu
 */
public class Producto implements Serializable {
    private String nombre;
    private String descripcion;
    private float precio;
    private int stockDisponible;
    private String nombreImagen;

    public Producto(String nombre, String descripcion,float precio, int stockDisponible, String nombreImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.nombreImagen = nombreImagen;
    }

    /**
     * @return the nombre
     */
    
    public Boolean extraerProducto(){
        if(this.stockDisponible>0){
            this.stockDisponible--;
            return true;
        }
        else{
            return false;
        }
    }
    
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
    public double getPrecio() {
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

}
