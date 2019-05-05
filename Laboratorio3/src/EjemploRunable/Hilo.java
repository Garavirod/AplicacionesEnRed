/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemploRunable;

import static java.lang.Math.sqrt;

/**
 *
 * @author rodrigo
 */
public class Hilo implements Runnable {

    @Override
    public void run() {
        int i = 0, j = 0, div = 0, raiz = 0;
        for (i = 1; i < 101; i++) { //ciclo para recorrer los numeros hasta el num 100
            div = 0; //variable para contar cuantas veces es el residuo de dividir es 0
            raiz = (int) sqrt(i);//la raiz del número a buscarle los primos
            for (j = 1; j <= raiz; j++) { //ciclo para recorrer los numeros hasta la raiz de i (estos seran los divisores)
                if (i % j == 0)//evalua la condicion de que el residuo de dividir i entre j es igual a cero                
                    div++;// si la condicion anterior se cumple entonces entonces suma 1 a esta variable                
            }
            if (div <= 1)//Si existe más de un divisor, entonces el número no es primo                          
                System.out.println(i);// imprime que cierto numero es primo            
        }
    }
}


