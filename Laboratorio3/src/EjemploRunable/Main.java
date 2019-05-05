/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemploRunable;

/**
 *
 * @author rodrigo
 */
public class Main {
    public static void main(String[] args) {
        Hilo h1 = new Hilo();
        Thread h2 = new Thread(h1);        
        h2.start();
    }
}
