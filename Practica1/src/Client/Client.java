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
            if(r == JFileChooser.APPROVE_OPTION){
                //se obtiene arreglo con las referencias de los archivo seleccionados
                File[] f = jf.getSelectedFiles();
                String[] archivos = new String[f.length];
                String nombre;
                long[] tams = new long[f.length];
                

                //obtencion de flujos de datos
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                //se envia al servidor la cantidad de archivos que se enviaran
                dos.writeInt(f.length);
                dos.flush();
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                int iter;
                for(iter = 0; iter < f.length;iter++){
                    archivos[iter] = f[iter].getAbsolutePath();
                    nombre = f[iter].getName();
                    tams[iter] = f[iter].length();
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
                    dis = new DataInputStream(new FileInputStream(archivos[iter]));
                    while(enviados < tams[iter]){
                        if(tams[iter]-enviados < 1024)
                            n = dis.read(b,0,(int)(tams[iter]-enviados));                        
                        else
                            n = dis.read(b);
                        
                        dos.write(b,0,n);
                        dos.flush();
                        enviados = enviados + n;
                        porcentaje = (int)(enviados*100/tams[iter]);
                        System.out.print("Enviado "+porcentaje+"%\n");
                    }
                    System.out.println("-----------FIN DE ARCHIVO----------");
                }

                System.out.print("\n\nA*|*|*|----rchivos Enviados----|*|*|*");
                dos.close();
                dis.close();
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
