/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.servidor;

import com.escom.compartidas.ListaProductos;
import com.escom.compartidas.ProdExt;
import com.escom.compartidas.Producto;
import java.net.*;
import java.io.*;
import java.util.HashMap;

/**
 *
 * @author sandu
 */
public class Servidor {

    public static void main(String args[]) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            ServerSocket s = new ServerSocket(9999);
            System.out.println("Servidor iniciado...");
            //se hace lectura el archivo .dat en el servidor
            
            ListaProductos lp;
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("objeto.dat"));
            lp = (ListaProductos) in.readObject();
            
            //usar getProd para tener acceso a la LinkedList y manejar con mayor facilidad los objetos
            for(ProdExt p:lp.getProd()){
                System.out.println(p.getNombre());
            }
            for (;;) {
                Socket cl = s.accept();
                System.out.println("cliente conectado desde " + cl.getInetAddress() + ":" + cl.getPort());
                
                
                oos = new ObjectOutputStream(cl.getOutputStream());
                ois = new ObjectInputStream(cl.getInputStream());
                
                //primero se envia objeto con productos
                oos.writeObject(lp);
                oos.flush();
                

                /************************************************************
                 * seccion de archivos
                 */
                
                dis = new DataInputStream(cl.getInputStream());
                dos = new DataOutputStream(cl.getOutputStream());
                File folder = new File("imgServer");
                File[] imagenes = folder.listFiles();
                String[] nombre = new String[imagenes.length];
                long[] tams = new long[imagenes.length];
                int i = 0;
                for (File f : imagenes) {
                    nombre[i] = f.getName();
                    tams[i] = f.length();
                    i++;
                }
                dos.writeInt(imagenes.length);
                dos.flush();
                for (i = 0; i < imagenes.length; i++) {
                    dos.writeUTF(nombre[i]);
                    dos.flush();
                    dos.writeLong(tams[i]);
                }
                byte[] b = new byte[1024];
                long enviados;
                int n;
                for (i = 0; i < imagenes.length; i++) {
                    enviados = 0l;
                    dis = new DataInputStream(new FileInputStream(imagenes[i]));
                    while (enviados < tams[i]) {
                        if (tams[i] - enviados < 1024) {
                            n = dis.read(b, 0, (int) (tams[i] - enviados));
                        } else {
                            n = dis.read(b);
                        }
                        dos.write(b, 0, n);
                        dos.flush();
                        enviados = enviados + n;
                    }
                }
                dos.close();
                dis.close();                
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
