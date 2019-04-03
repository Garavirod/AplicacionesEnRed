
package carrito;
import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class Servidor {

    private static ArrayList<String> datos = new ArrayList<String>();
    private static ArrayList<Integer> id = new ArrayList<Integer>();
    
    private static void modificar()
    {
        ArrayList<DatosP> dp = new ArrayList<DatosP>();
        String concat = "";
        int contProd0 = 0;
        int contProd1 = 0;
        int contProd2 = 0;
        int contProd3 = 0;
        int contProd4 = 0;
        int contProd5 = 0;
        int contProd6 = 0;
        int contProd7 = 0;
        int contProd8 = 0;
        int contProd9 = 0;
        int contProd10 = 0;
        int contProd11 = 0;
        
        for(int i=0;i<datos.size();i++)
            dp.add(arch(i));
        
        for(int i=0;i<id.size();i++)
        {
            switch(id.get(i))
            {
                case 0: contProd0++;
                break;
                case 1: contProd1++;
                break;
                case 2: contProd2++;
                break;
                case 3: contProd3++;
                break;
                case 4: contProd4++;
                break;
                case 5: contProd5++;
                break;
                case 6: contProd6++;
                break;
                case 7: contProd7++;
                break;
                case 8: contProd8++;
                break;
                case 9: contProd9++;
                break;
                case 10: contProd10++;
                break;
                case 11: contProd11++;
                break;
                
                default:
                break;
            }
        }
        
        for(int i=0;i<dp.size();i++)
        {
            switch(i)
            {
                case 0: dp.get(0).existencias -= contProd0;
                break;
                case 1: dp.get(1).existencias -= contProd1;
                break;
                case 2: dp.get(2).existencias -= contProd2;
                break;
                case 3: dp.get(3).existencias -= contProd3;
                break;
                case 4: dp.get(4).existencias -= contProd4;
                break;
                case 5: dp.get(5).existencias -= contProd5;
                break;
                case 6: dp.get(6).existencias -= contProd6;
                break;
                case 7: dp.get(7).existencias -= contProd7;
                break;
                case 8: dp.get(8).existencias -= contProd8;
                break;
                case 9: dp.get(9).existencias -= contProd9;
                break;
                case 10: dp.get(10).existencias -= contProd10;
                break;
                case 11: dp.get(11).existencias -= contProd11;
                break;
                default:    break;
            }
        }
        
        for(int i=0;i<dp.size();i++){
            if(i == dp.size()-1)
                concat = concat+dp.get(i).nombre+"\t"+dp.get(i).existencias+"\t"+dp.get(i).precio+"\t"+dp.get(i).descripcion;
            else
                concat = concat+dp.get(i).nombre+"\t"+dp.get(i).existencias+"\t"+dp.get(i).precio+"\t"+dp.get(i).descripcion+"\r\n";
        }
        
        try
        {
            File modify = new File("productos.txt");
            modify.createNewFile();
            FileWriter writer = new FileWriter(modify,false);
            if(modify.exists()){
                writer.write(concat);
                writer.flush();
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static DatosP arch(int cont)
    {
        String nombre = "", descripcion = "";
        int existencias = 0;
        float precio = 0;
        String info;
        int numTokens = 0;
        StringTokenizer st = new StringTokenizer(datos.get(cont));
        
        while(st.hasMoreTokens())
        {
            info = st.nextToken();
            numTokens++;
            if(numTokens == 1)
                nombre = info;
            else if(numTokens == 2)
                existencias = Integer.parseInt(info);
            else if(numTokens == 3)
                precio = Float.parseFloat(info);
            else if(numTokens == 4)
                descripcion = info;
        }
        
        DatosP archivo = new DatosP(nombre, existencias, precio, descripcion);
        return archivo;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            System.out.println("Servicio iniciado");
            String host = "192.168.1.74";
            int pto = 9090;
            String info;
            int num = 0;
            
            InetAddress dst = null;
            try{
                dst = InetAddress.getByName(host);
            }catch(UnknownHostException u){
                u.printStackTrace();
            }
            
            while(true)
            {
                datos = new ArrayList<String>();
                FileReader f = new FileReader("productos.txt");
                BufferedReader br = new BufferedReader(f);
                while((info = br.readLine())!=null)
                    datos.add(info);
                br.close();
                f.close();
                DatagramSocket cl = new DatagramSocket(1234);
                DatagramSocket s = new DatagramSocket();
                
                byte [] bId = new byte[1000];
                DatagramPacket pRec = new DatagramPacket(bId, bId.length);
                cl.receive(pRec);
                
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(pRec.getData()));
                Object o = ois.readObject();
                cl.close();
                if(o instanceof ArrayList)
                {
                    ArrayList<Integer> idT = (ArrayList<Integer>)o;
                    id = idT;
                    modificar();
                }
                else if(o instanceof String)
                {
                    String convNum = (String)o;
                    num = Integer.parseInt(convNum);
                }
                
                /*Envia DatosP*/
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(arch(num));
                oos.flush();
                byte [] bDatosP = baos.toByteArray();
                DatagramPacket pDatosP = new DatagramPacket(bDatosP,bDatosP.length,dst,pto);
                s.send(pDatosP);
                
                /*Envia Imagen*/
                File fImg = new File("imgServer/Producto"+(num+1)+".jpg");
                String path = fImg.getAbsolutePath();
                long tam = fImg.length();
                DatosImg img = new DatosImg(fImg.getName(), tam);
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(img);
                oos.flush();
                byte [] bDatosImg = baos.toByteArray();
                DatagramPacket pDatosImg = new DatagramPacket(bDatosImg,bDatosImg.length,dst,pto);
                s.send(pDatosImg);
                
                byte [] b = new byte[20000];
                int n=0,i=0;
                long enviados = 0;
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
                while(enviados<tam)
                {
                    n = bis.read(b);
                    Datos d = new Datos(i,b,n);
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    oos.writeObject(d);
                    oos.flush();
                    byte [] bDatos = baos.toByteArray();
                    DatagramPacket pDatos = new DatagramPacket(bDatos,bDatos.length,dst,pto);
                    s.send(pDatos);
                    i++;
                    enviados+=n;
                }
                System.out.println("Imagen enviada");
                bis.close();
                baos.close();
                oos.close();
                s.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
