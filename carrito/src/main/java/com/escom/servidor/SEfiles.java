/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.servidor;

import java.io.*;
import java.net.*;

/**
 *
 * @author sandu
 */
public class SEfiles {

    public static void main(String[] args) {
        DataInputStream dis;
        DataOutputStream dos;
        try {
            ServerSocket s = new ServerSocket(7010);
            System.out.println("Servidor iniciado...");
            for (;;) {
                Socket cl = s.accept();
                System.out.println("cliente conectado desde " + cl.getInetAddress() + ":" + cl.getPort());
                dis = new DataInputStream(cl.getInputStream());
                dos = new DataOutputStream(cl.getOutputStream());
                File folder = new File("resources");
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
                    System.out.println("End of file");
                }
                dos.close();
                dis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
