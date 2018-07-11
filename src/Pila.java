
import java.util.Vector;

public class Pila {
    private int TOPE;
    //private Object[] vector;
    Vector<Object> vector;
    
    public Pila(/*int tam*/){
        this.vector = new Vector<Object>();
        TOPE = -1;
        //vector = new Object[tam];
    }
    
    public boolean apilar(Object dato){
//        if(TOPE == vector.size()){
//            return false;
//        }
        TOPE++;
        vector.add(TOPE, dato);
        //System.out.println("Apilo "+dato+":"+TOPE);
        //vector[TOPE] = dato;
        return true;
    }
    
    public boolean retirar(Object dato){
        if( estaVacia() ){
            return false;
        }
        if(vector.get(TOPE).equals(dato)){
            //System.out.println("Retiró "+dato+":"+TOPE);
            TOPE--;
            return true;
        }else
            return false;
    }
    
    public String mostrado(){
        if( estaVacia() ){
            return null;
        }
        String temp = "";
        for(int i=0; i<=TOPE; i++){
            temp =temp+vector.get(i);
            //temp = vector[i]+"|";
        }
        System.out.println(temp);
        return temp;
    }
    
    public int cantidadDatos(){
        return TOPE+1;
    }
    
    public boolean estaVacia(){
        return TOPE == -1;
    }
    
//    private boolean estaLlena(){
//        return TOPE == vector.size();
//    }
}
