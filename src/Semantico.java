
import javax.swing.table.TableModel;
import java.util.Vector;

public class Semantico{
    TableModel m;
    Vector<String> tipoDatos = new Vector<String>(), nombreMetodo = new Vector<String>();
    Vector<String> id = new Vector<String>(); 
    Vector<String> palReservadas = new Vector<String>(), num = new Vector<String>();
    Vector<String> opRelacional = new Vector<String>(), opAritmetico = new Vector<String>();
    
    Semantico(TableModel m){
        this.m = m; 
        separarTokens();
    }
    
    private boolean buscarId(String id){
        for( int i=0; i<this.id.size(); i++ ){
            if( this.id.get(i).equals(id) ){
                return true;
            }
        }
        return false;
    }
    
    private void separarTokens(){
        for( int i=0; i<m.getRowCount(); i++ ){
            if( !m.getValueAt(i, 0).equals("") ){
                if( m.getValueAt(i, 0).equals("Identificador") ){
                    if( !buscarId(m.getValueAt(i, 1).toString()) )
                        id.add(m.getValueAt(i, 1).toString() );
                }else
                if( m.getValueAt(i, 0).equals("Nombre Método") ){
                    nombreMetodo.add( m.getValueAt(i, 1).toString() );
                }else
                if( m.getValueAt(i, 0).equals("Operador relacional") ){
                    opRelacional.add( m.getValueAt(i, 1).toString() );
                }else
                if( m.getValueAt(i, 0).equals("Operador aritmetico") ){
                    opAritmetico.add( m.getValueAt(i, 1).toString() );
                }else
                if( m.getValueAt(i, 0).equals("Número") ){
                    num.add( m.getValueAt(i, 1).toString() );
                }else{
                    palReservadas.add( m.getValueAt(i, 1).toString() );
                }
            }
        }
    }
    
    //verifica que una variable solo haya sido declarada una sola vez, devuelve falso si no es así
    public String unicidad(){
        String error="";
        Object[] s;
        boolean c = false;
        int temp=0, t=0;
        for( int i=0; i<id.size(); i++ ){
            s = buscarTipoDatoTabla( id.get(i),temp );
            if( s!=null ){
                if( tipoDatos(s[0])==true ){//si es un tipo de dato
                    if( c==true ){//si ya se ha declarado antes devuelve un error
                        System.out.println(id.get(i)+": "+s[0]);
                        error = error+"Identificador: "+id.get(i)+" ya declarada#";
                        c=false;
                        System.out.println("Cambio a falso(1) con: "+id.get(i));
                        temp=0;
                    }else{
                        c=true;
                        System.out.println("Cambio a verdadero con: "+id.get(i));
                        i--;
                        temp = Integer.parseInt(s[1].toString());
                    }
                }else if( c ){
                    c=false;//si no se encuentra otra declaración se reinicia contador
                    System.out.println("Cambio a falso(2) con: "+id.get(i));
                    temp=0;
                    error = error+"todo bien 2";
                }else{
                    error = error+"Identificador: \""+id.get(i)+"\" no ha sido declarada#";
                    temp=0;
                }
            }else if(c==false){
                error = error+"Identificador: "+id.get(i)+" no ha sido declarada#";
                temp=0;
                //si no se encuentra declarado entonces devuelve error
            }
        }//fin de for
        //System.out.println(error);
        return error;
    }
    
    //busca el tipo de dato antes de la variable
    private Object[] buscarTipoDatoTabla(String b, int desde){
        for( int i=desde; i<m.getRowCount(); i++ ){
            if( m.getValueAt(i, 1)!=null){//ignora la linea en blanco
                if( m.getValueAt(i, 1).equals(b) ){//si el valor es igual al de la busqueda
                    Object S[] = {m.getValueAt(i-1, 1).toString(), (i+1)};//prepara el valor a devolver, Lo que esta antes de declararlo, linea en que se quejo
                    if( tipoDatos(S[0])==true ){//si la palabra de fila anterior es un tipo e dato se pone en la posición 6 de la fila i de la tabla
                        m.setValueAt(S[0], i, 6);
                    }
                    return S;
                }
            }
        }
        return null;
    }
    
    public boolean tipoDatos(Object Q){
        return ( Q.equals("String") || Q.equals("Int") || Q.equals("Double") );
    }
    
    public boolean comprobadorTipos(){
/*1. Separar las variables por tipos de datos (arreglo de Int, String, Double)*/
        
/*2. Hacer las validaciones especificas (según el tipo al que pertenezca) de que la expreción obtenida del valor corresponda con el tipo de dato al pertence.
3. Mostrar el listado de errores*/
        return true;
    }
}
