/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rodrigo
 */
public class Server {
     public static void main(String[] args){
        try{
            //creamos el socket servidor
            ServerSocket s = new ServerSocket(9010);
            //iniciamos el ciclo infinito del servidor, simpre estará en espera de peticiones
            while(true){
                //Esperamos una conexion
                System.out.println("Esperando conexion...");
                Socket cl = s.accept();
                System.out.println("Se estableció una conexion desde: [ " + cl.getInetAddress()+":"+cl.getPort()+" ]");
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                byte[] b = new byte[1024];
                
                //Cantidad de archivos que mandaremos
                int numArchivos = dis.readInt();
                System.out.println("Numero de archivos a mandar: "+numArchivos);

                int iter;
                String[] nombres = new String[numArchivos];
                Long[] tams = new Long[numArchivos];
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                for(iter = 0; iter < numArchivos; iter++){
                    nombres[iter] = dis.readUTF();
                    System.out.println("Recibimos el archivo: "+nombres[iter]);
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
                            n = dis.read(b);
                        
                        dos.write(b,0,n);                                                        
                        dos.flush();                                                
                        recibidos = recibidos+n;
                        porcentaje = (int)(recibidos*100/tams[iter]);
                        System.out.print("Recibido: " + porcentaje +"%\n");
                    }
                    dos.close();
                }
                System.out.print("\n\n-*-*-*-!Archivo recibido!-*-*-*\n\n");
                dos.close();
                dis.close();
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
