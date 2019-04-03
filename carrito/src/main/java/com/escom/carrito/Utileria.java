/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.carrito;

import com.escom.compartidas.Producto;
import java.util.HashMap;

/**
 *
 * @author sandu
 */
public class Utileria {
    HashMap<Integer,Producto> productos;
    public Utileria(){
        this.productos = new HashMap<>();
    }
    public HashMap<Integer,Producto> stockGenerator(){
        this.productos.put(1,new Producto("XBOX_ONE_S","Bluray_4K_HDR",6000f,9,"xbox.png"));
        this.productos.put(2,new Producto("XBOX_ONE_X","4K_HDR",10999f,9,"xboxonex.png"));
        this.productos.put(3,new Producto("PS4_SLIM","AMD_jaguar_8nucleos",7000f,10,"ps4s.png"));
        this.productos.put(4,new Producto("PS4_PRO","4K_HDR",9000f,10,"ps4p.png"));
        this.productos.put(5,new Producto("N_SWITCH","Portatil",8000f,9,"n_switch.png"));
        return this.productos;
    }
}
