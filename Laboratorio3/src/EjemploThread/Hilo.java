/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemploThread;

/**
 *
 * @author rodrigo
 */
public class Hilo extends Thread{
    public Hilo(){
        super();
    }
    
    public void run(){
        for (char i = 'A'; i<='Z'; i++)
            System.out.println(i);   
    }
}
