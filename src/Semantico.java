
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
    
    private void separarTokens(){
        for( int i=0; i<m.getRowCount(); i++ ){
            if( !m.getValueAt(i, 0).equals("") ){
                if( m.getValueAt(i, 0).equals("Identificador") ){
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
    public boolean unicidad(){
        Object[] s;
        boolean c = false;
        int temp=0;
        for( int i=0; i<id.size(); i++ ){
            s = buscarTipoDatoTabla( id.get(i),temp );
            if( s!=null ){
                if( tipoDatos(s[0])==true ){
                    if( c==true ){//si ya se ha declarado antes devuelve un error
                        return false;
                    }else{
                        c=true;
                        i--;
                        temp = Integer.parseInt(s[1].toString());
                    }
                }else{
                    c=false;//si no se encuentra otra declaración se reinicia contador para 
                }
            } return false;//si no se encuentra declarado entonces devuelve error
        }//fin de for
        return true;
    }
    
    //busca el tipo de dato antes de la variable
    private Object[] buscarTipoDatoTabla(String b, int desde){
        for( int i=desde; i<m.getRowCount(); i++ ){
            if( m.getValueAt(i, 1).equals(b) ){
                Object S[] = {m.getValueAt(i-1, 1).toString(), (i+1)};
                m.setValueAt(S[0], i, 6);
                return S;
            }
        }
        return null;
    }
    
    public boolean tipoDatos(Object Q){
        return ( Q.equals("String") || Q.equals("Int") || Q.equals("Double") );
    }
    
    public boolean comprobadorTipos(){
        
        return true;
    }
}
