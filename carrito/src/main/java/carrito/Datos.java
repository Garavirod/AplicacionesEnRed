
package carrito;
import java.io.Serializable;

public class Datos implements Serializable{
    int sec;
    byte [] b;
    int num;
    
    public Datos(int s,byte[]b,int n)
    {
        sec = s;
        this.b = b;
        num = n;
    }
    public String toString()
    {
        return ""+sec;
    }
}
