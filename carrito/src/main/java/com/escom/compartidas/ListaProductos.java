/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.compartidas;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author sandu
 */
public class ListaProductos implements Serializable {
    private LinkedList<ProdExt> prod = new LinkedList<>();
    public ListaProductos(String[] nombres,String[] descripciones, float[] precios,
            int[] stocks,String[] imagen){
        for(int i=0;i<nombres.length;i++){
            prod.add(new ProdExt(nombres[i],descripciones[i],precios[i],stocks[i],imagen[i]));
        }
    }
    
    public LinkedList<ProdExt> getProd(){
        return prod;
    }
    
    public void muestraProductos(){
        ListIterator<ProdExt> li = prod.listIterator();
        ProdExt p;
        while(li.hasNext()){
            p = (ProdExt)li.next();
            p.muestraUsuario();
        }
    }
}
