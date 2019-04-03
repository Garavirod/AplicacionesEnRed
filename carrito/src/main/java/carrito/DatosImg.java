
package carrito;
import java.io.*;

public class DatosImg implements Serializable{
    String nombre;
    long tam;
    
    public DatosImg(String n,long t)
    {
        nombre = n;
        tam = t;
    }
}