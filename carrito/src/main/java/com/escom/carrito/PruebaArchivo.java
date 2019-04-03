/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.carrito;

import com.escom.compartidas.Producto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javafx.util.Pair;

/**
 *
 * @author sandu
 */
public class PruebaArchivo {
    public static void main(String[] args) {
        //----------------Se crean productos de tienda------------
        Producto p1,p2,p3,p4;
        p1 = new Producto("xbox 360","consola",1500,10,"xb.png");
        p2 = new Producto("ps4","consola",2000,5,"ps4.png");
        p3 = new Producto("ps3","consola",1500,2,"ps3.png");
        p4 = new Producto("xbox one","consola",2000,1,"xb1.png");
        //--------------------------------------------------------
        Ticket t = new Ticket();
        t.addProducto(p1);
        t.addProducto(p2);
        t.addProducto(p3);
        t.addProducto(p4);
        t.setCodigoTransaccion("XMSDFMK1");
        t.setDetalle("3 consolas y dos videojuegos");
        DateFormat d = new SimpleDateFormat("dd/mm/yyyy");
        t.setFechaTransaccion(d.toString());
        t.setNombreComercio("Gaming store");
        t.setNombreLogo("loquesea");
        Documento doc = new Documento(t);
        try{
            doc.estructuraDoc("");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
