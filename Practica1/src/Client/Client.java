/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFileChooser;
import javafx.stage.FileChooser;
/**
 *
 * @author rodrigo
 */
public class Client {
    public static void main(String[] args) {
        try{
 
            //Ingreso de datos de servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la direccion del servidor:");
            String host = br.readLine();
            System.out.printf("\n\nEscriba el puerto:");
            int pto = Integer.parseInt(br.readLine());
            Socket cl = new Socket(host,pto);
            JFileChooser jf = new JFileChooser();
            jf.setMultiSelectionEnabled(true);
            
            int r = jf.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){ //cuando el usuario da al boton de aceptar
                //se obtiene arreglo con las referencias de los archivo seleccionados
                File[] f = jf.getSelectedFiles(); //Arreglo de archovos seleccionados
                String[] archivos = new String[f.length]; //Arreglo de nombres de los archivos
                String nombre;
                long[] tams = new long[f.length]; //Arreglo del tamaño de cada archivo
                

                //obtencion de flujos de datos
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); //Flujo de datos de salida                
                dos.writeInt(f.length); //se envia al servidor la cantidad de archivos que se enviaran
                dos.flush(); //VAciamos el buffer de salida sin cerra la conexion
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                int iter; //Variable iteradora
                for(iter = 0; iter < f.length;iter++){
                    archivos[iter] = f[iter].getAbsolutePath(); //De cada archivo obtenemos su ruta absoluta
                    nombre = f[iter].getName(); //Alamacenamos su nombre
                    tams[iter] = f[iter].length(); //Obtenemos su tamaño
                    System.out.println("Path: "+archivos[iter]+" nombre: "+nombre+" tamaño: "+tams[iter]);
                    dos.writeUTF(nombre);
                    dos.flush();
                    dos.writeLong(tams[iter]);
                    dos.flush();
                    
                }

                byte[] b = new byte[1024];
                iter = 0;
                long enviados;
                int porcentaje,n;
                
                for(;iter<tams.length;iter++){
                    enviados = 0l;
                    //Leemos datos de tipo primitivo del flujo de entrada
                    dis = new DataInputStream(new FileInputStream(archivos[iter]));
                    while(enviados < tams[iter]){
                        if(tams[iter]-enviados < 1024)
                            n = dis.read(b,0,(int)(tams[iter]-enviados));                        
                        else
                            n = dis.read(b);
                        //b: buffer en en el que se leen los datos, 
                        //0: inicio en la matriz de destino, 
                        //n: numero maximo de bytes leidos
                        dos.write(b,0,n);
                        dos.flush();
                        enviados = enviados + n;
                        porcentaje = (int)(enviados*100/tams[iter]);
                        System.out.print("Enviado "+porcentaje+"%\n");
                    }
                    System.out.println("-----------FIN DE ARCHIVO----------");
                }

                System.out.print("\n\n*|*|*|----Archivos Enviados----|*|*|*");
                dos.close();
                dis.close();
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
