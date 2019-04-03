/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.cliente;

import com.escom.compartidas.ListaProductos;
import java.net.*;

import java.io.*;

/**
 *
 * @author Rga
 */
public class Cliente {

    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        String host = "127.0.0.1";
        int port = 9999;
        try {
            Socket cl = new Socket(host, port);
            System.out.println("conexion establecida...");
            oos = new ObjectOutputStream(cl.getOutputStream());
            ois = new ObjectInputStream(cl.getInputStream());
            
            //recepcion de archivo .dat
            ListaProductos lp;
            lp = (ListaProductos) ois.readObject();
            lp.muestraProductos();

            
            /**************************************************
             * zona de archivos
             */
            byte[] b = new byte[1024];
            dis = new DataInputStream(cl.getInputStream());
            int numeroArchivos = dis.readInt();
            String[] nombres = new String[numeroArchivos];
            long[] tams = new long[numeroArchivos];
            dos = new DataOutputStream(cl.getOutputStream());
            int i = 0;
            for (; i < numeroArchivos; i++) {
                nombres[i] = dis.readUTF();
                tams[i] = dis.readLong();
                System.out.println(nombres[i] + " " + tams[i]);
            }
            long recibidos;
            int n;
            for (i = 0; i < numeroArchivos; i++) {
                recibidos = 0l;
                dos = new DataOutputStream(new FileOutputStream(nombres[i]));
                while (recibidos < tams[i]) {
                    if (tams[i] - recibidos < 1024) {
                        n = dis.read(b, 0, (int) (tams[i] - recibidos));
                    } else {
                        n = dis.read(b);
                    }
                    dos.write(b, 0, n);
                    dos.flush();
                    recibidos = recibidos + n;
                }
                dos.close();

            }
            dos.close();
            dis.close();
            cl.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
