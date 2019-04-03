/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Server;

import java.net.*;
import java.io.*;

/**
 *
 * @author rodrigo
 */
public class Server {
     public static void main(String[] args){
        try{
            //creamos el socket servidor
            ServerSocket s = new ServerSocket(9013);
            //iniciamos el ciclo infinito del servidor, simpre estará en espera de peticiones
            while(true){
                //Esperamos una conexion
                System.out.println("Esperando conexion...");
                Socket cl = s.accept();
                System.out.println("Se estableció una conexion desde: [ " + cl.getInetAddress()+":"+cl.getPort()+" ]");
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                byte[] b = new byte[2048];
                
                //Cantidad de archivos que mandaremos
                int numArchivos = dis.readInt();
                System.out.println("Numero de archivos a mandar: "+numArchivos);

                int iter; //Variable iteradora
                String[] nombres = new String[numArchivos];
                Long[] tams = new Long[numArchivos];
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                for(iter = 0; iter < numArchivos; iter++){
                    nombres[iter] = dis.readUTF();
                    System.out.println("Archivos a recibir: "+nombres[iter]);
                    tams[iter] = dis.readLong();
                } 
                //Sección para recibir el archivo
                Long recibidos;
                int n=0,porcentaje;
                iter = 0;
                for(; iter < numArchivos; iter++){
                    recibidos = 0l;
                    dos = new DataOutputStream(new FileOutputStream(nombres[iter]));
                    while(recibidos < tams[iter]){
                        if(tams[iter] - recibidos < 1024)
                            n = dis.read(b,0,(int)(tams[iter]-recibidos));                        
                        else
                            n = dis.read(b); //Leemos numero de bytes de flujo de entrada
                        
                        dos.write(b,0,n);                                                        
                        dos.flush();                                                
                        recibidos = recibidos+n;
                        porcentaje = (int)(recibidos*100/tams[iter]);
                        System.out.print("Recibido: " + porcentaje +"%\n");
                    }
                    dos.close();
                }
                System.out.print("\n\n-*-*-*-!Archivos recibidos correctamente!-*-*-*\n\n");
                dos.close();
                dis.close();
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
