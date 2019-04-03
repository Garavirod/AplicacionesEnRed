
package carrito;
import java.io.Serializable;

public class DatosP implements Serializable{
    String nombre;
    int existencias;
    float precio;
    String descripcion;
    
    public DatosP(String nom, int exis, float precio, String desc)
    {
        nombre = nom;
        existencias = exis;
        this.precio = precio;
        descripcion = desc;
    }
    
    public DatosP(String nom, float precio)
    {
        nombre = nom;
        this.precio = precio;
    }
}
