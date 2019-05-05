/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemploThread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class Hilo extends Thread{
    public Hilo(){
        super();
    }
    
    public void run(){
        for (char i = 'A'; i<='Z'; i++){
            System.out.println(i);
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
