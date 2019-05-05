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
public class Main {
    public static void main(String[] args) {
        Thread hilo = new Hilo();
        hilo.start();
    }
}
