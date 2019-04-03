/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.compartidas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author sandu
 */
public class pruebaExt {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.println("creando objeto");
        String[] nombres = {"xbox one","ps3","ps4","nintendo switch"};
        String[] descripciones = {"4k","1080","dvr","portatil"};
        float[] precios = {4000f,2000f,4500f,10000f};
        int[] stocks = {10,10,5,9};
        String[] imagenes = {"xb.png","ps3.png","ps4.png","switch.png"};
        ListaProductos lp = new ListaProductos(nombres,descripciones,precios,stocks,imagenes);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("objeto.dat")); 
        o.writeObject(lp);
        o.close();
        System.out.println("recuperando");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("objeto.dat"));
        lp = (ListaProductos)in.readObject();
        lp.muestraProductos();
    }
}
