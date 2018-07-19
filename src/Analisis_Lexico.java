
import java.util.Stack;
import static javax.swing.JOptionPane.showMessageDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this Simboloslate file, choose Tools | Templates
 * and open the Simboloslate in the editor.
 */

/**
 *
 * @author basix
 */
public class Analisis_Lexico {   
    String TV[];
    String State="q0";
    String Simbolos="";
    //################################################################################################################################################
    private boolean esNomMet(String S){
        for(int k=0;k<S.length();k++){
            if( !esLetraMay(S.charAt(k)) ){
                return false;
            }
        }
        return true;
    }
    
    private boolean esId(String S){
        for(int k=0;k<S.length();k++){
            if( !esLetraMin(S.charAt(k)) ){
                return false;
            }
        }
        return true;
    }
    
    private boolean esLetraMay(char l){
        return l>=65 && l<=90;
    }
    
    private boolean esLetraMin(char l){ //devuelve verdadero si es una letra minuscula
        return l>=97 && l<=122;
    }
    
    private boolean esLetra(char l){//devuelve verdadero si es una letra
        if( (l>=65 && l<=90) || (l>=97 && l<=122) )
            return true;
        else
            return false;
    }
    
    private boolean esNumero(char l){//devuelve verdadero si es un número
        if( (l>=48 && l<=57) )
            return true;
        else
            return false;
    }
    
    private boolean esOpR(char l){//devuelve verdadero si es un operador relacional ( ) < = >
        if( (l>=60 && l<=62) || l==40 || l==41 || l==46 || l==42 || l==43 || l==47 )
            return true;
        else
            return false;
    }
    
    public String[] generaArray(String Exp){
        boolean letra=false, num=false, opR=false, com=false;
        String Simbolos="";
        for(int i=0; i<Exp.length(); i++){
            //validación de comillas
            if( Exp.charAt(i)==34 ){
                if(com==false){
                    com=true;
                    if( letra==true || num==true || opR==true ){//verifica si se estaba escribiendo algo antes
                        letra = num = opR = false;//lo que sea que este escribiendo lo pasa a que se dejo de escribir
                    }
                    Simbolos = Simbolos+Exp.charAt(i);
                }else{
                    com=false;
                    Simbolos = Simbolos+Exp.charAt(i)+"#";
                }
            }
            //validación de espacio
            if( Exp.charAt(i)==32 ){
                if( letra==true || num==true || opR==true ){//verifica si se estaba escribiendo algo antes
                    letra = num = opR = false;//lo que sea que este escribiendo lo pasa a que se dejo de escribir
                    Simbolos = Simbolos+"#";
                }
            }
            //validación si es letra
            if( esLetra(Exp.charAt(i)) ){
                if( num ){//si es numero agrega un separador para el esplit posterior y quita la bandera de que se escribian numeros
                    Simbolos = Simbolos+"#";
                    num=false;
                }else if( opR ){//si es operador agrega un separador para el esplit posterior y quita la bandera de que se escribian operadores
                    Simbolos = Simbolos+"#";
                    opR=false;
                }
                letra=true;//activa la bandera de que se escriben letras
                Simbolos = Simbolos+Exp.charAt(i);
            }
            //validación si es número
            if( esNumero(Exp.charAt(i)) ){
                if( letra ){//si es letra agrega un separador para el esplit posterior y quita la bandera de que se escribian letras
                    Simbolos = Simbolos+"#";
                    letra=false;
                }else if( opR ){//si es operador agrega un separador para el esplit posterior y quita la bandera de que se escribian operadores
                    Simbolos = Simbolos+"#";
                    opR=false;
                }
                num=true;//activa la bandera de que se escriben numeros
                Simbolos = Simbolos+Exp.charAt(i);
            }
            //validación si es operador relacional
            if( esOpR(Exp.charAt(i)) ){
                if( letra ){//si es letra agrega un separador para el esplit posterior y quita la bandera de que se escribian letras
                    Simbolos = Simbolos+"#";
                    letra=false;
                }else if( num ){//si es numero agrega un separador para el esplit posterior y quita la bandera de que se escribian numeros
                    Simbolos = Simbolos+"#";
                    num=false;
                }
                opR=true;//activa la bandera de que se escriben operadores
                Simbolos = Simbolos+Exp.charAt(i);
            }
        }
        return Simbolos.split("#");
    }
    
    
    //################################################################################################################################################
    public String Compile(String Exp, int linea){//Creamos el método que nos analizará la expresión
        Simbolos="";
        boolean ban=false;
        String Temp=Exp;
        String AA[]=generaArray(Exp);
        TV=AA;
        String Q="";
        for(int AL=0;AL<TV.length;AL++){
            if(!TV[AL].equals("")){
                if(TV[AL].substring(0,1).equals("N") && (!esNomMet(TV[AL]))){
                    TN(TV[AL], linea);
                    ban=true;
                }
                if(TV[AL].substring(0,1).equals("M")&& (!esNomMet(TV[AL]))){
                    TM(TV[AL], linea);
                    ban=true;
                }
                if(TV[AL].substring(0,1).equals("D")&& (!esNomMet(TV[AL]))){
                    TD(TV[AL], linea);
                    ban=true;
                }//fin si  el primer caracter de la palabra es 'D'         
                if(TV[AL].substring(0,1).equals("A")&& (!esNomMet(TV[AL]))){
                    TA(TV[AL], linea);
                    ban=true;
                }//fin si  el primer caracter de la palabra es 'D'  
                if(TV[AL].substring(0,1).equals("E")&& (!esNomMet(TV[AL]))){
                    TE(TV[AL], linea);
                    ban=true;
                }
                if(TV[AL].substring(0,1).equals("B")&& (!esNomMet(TV[AL]))){
                    TB(TV[AL], linea);
                    ban=true;
                }
                if(TV[AL].substring(0,1).equals("S")&& (!esNomMet(TV[AL]))){
                    TS(TV[AL], linea);
                    ban=true;
                }
                if(TV[AL].substring(0,1).equals("o")&& (!esNomMet(TV[AL]))){
                   TO(TV[AL], linea);
                   ban=true;
                }
                if(TV[AL].substring(0,1).equals("C")&& (!esNomMet(TV[AL]))){
                   TC(TV[AL], linea);
                   ban=true;
                }
                if(TV[AL].substring(0,1).equals("I")&& (!esNomMet(TV[AL]))){
                   TI(TV[AL], linea);
                   ban=true;
                }
                if(TV[AL].substring(0,1).equals("P")&& (!esNomMet(TV[AL]))){
                   TP(TV[AL], linea);
                   ban=true;
                }                
            }
            Q = letraI(TV[AL]);
            if(Q!=null && ban==false){
                switch( Q ){
                    case "+":
                        Simbolos = Simbolos + "Operador aritmetico,"+TV[AL]+",No,Caracter (,"+linea+"#";
                        break; 
                    case "-":
                        Simbolos = Simbolos + "Operador aritmetico,"+TV[AL]+",No,Caracter (,"+linea+"#";
                        break; 
                    case "*":
                        Simbolos = Simbolos + "Operador aritmetico,"+TV[AL]+",No,Caracter (,"+linea+"#";
                        break; 
                    case "/":
                        Simbolos = Simbolos + "Operador aritmetico,"+TV[AL]+",No,Caracter (,"+linea+"#";
                        break; 
                    case ".":
                        Simbolos = Simbolos + "Operador,"+TV[AL]+",No,Caracter (,"+linea+"#";
                        break;                    
                    case "(":
                        Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter (,"+linea+"#";
                        break;
                    case ")":
                        Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter ),"+linea+"#";
                        break;                    
                    case "<":
                        if(TV[AL].length()==1)
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter <,"+linea+"#";
                        else if( TV[AL].equals("<=") )
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter < seguido del caracter =,"+linea+"#";
                        else if( TV[AL].equals("<>") )
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter < seguido del caracter >,"+linea+"#";
                        else
                            Simbolos = Simbolos + "Elemento Desconocido,"+TV[AL]+",No,?,"+linea+"#";
                        break;
                    case ">":
                        if(TV[AL].length()==1)
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter >,"+linea+"#";
                        else if( TV[AL].equals(">=") )
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter > seguido del caracter =,"+linea+"#";
                        else
                            Simbolos = Simbolos + "Elemento Desconocido,"+TV[AL]+",No,?,"+linea+"#";
                        break;
                    case "=":
                        if(TV[AL].length()==1)
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter =,"+linea+"#";
                        else if( TV[AL].equals("==") )
                            Simbolos = Simbolos + "Operador relacional,"+TV[AL]+",No,Caracter = seguido del caracter =,"+linea+"#";
                        else
                            Simbolos = Simbolos + "Elemento Desconocido,"+TV[AL]+",No,?,"+linea+"#";
                        break;
                    case "##":
                        if(TV[AL].length()>1)
                            Simbolos = Simbolos + "Número,"+TV[AL]+",No,Digito seguido de mas digitos,"+linea+"#";
                        else
                            Simbolos = Simbolos + "Número,"+TV[AL]+",No,Digito,"+linea+"#";
                        break;
                    case "\"":
                        break;
                    default:
                        int b=0;
                        //nombre de metodo
                        if( TV[AL].equals("task") ){
                            Simbolos = Simbolos + "task,"+TV[AL]+",si,t seguida de a seguida de s seguida de k,"+linea+"#";
                        }else if( esNomMet(TV[AL]) ){
                            Simbolos = Simbolos + "Nombre Método,"+TV[AL]+",No,Caracter seguido de caracteres,"+linea+"#";
                        }else if( esId(TV[AL]) ){
                            Simbolos = Simbolos + "Identificador,"+TV[AL]+",No,Caracter seguido de caracteres,"+linea+","+valor(TV)+"#";
                        }else{
                            Simbolos = Simbolos + "Elemento Desconocido,"+TV[AL]+",No,?,"+linea+"#";
                        }
                        break;
                }
            }
            ban=false;
        }//Fin recorrido palabras en linea
        return Simbolos;
    }//Fin Metodo Compile
    
    private String valor(String[] S){
        boolean b=false;
        String temp="";
        for(int i=0; i<S.length; i++){
            if( b==true )
                temp = temp + S[i];
            if( S[i].equals("=") )
                b=true;
        }
        if(temp.equals("")){
            temp = "null";
        }
        return temp;
    }
    
    private String letraI(String C){
        if(C!=null){
            //System.out.println("Primero: "+C.substring(0, 1));
            if(C.substring(0).equals(""))
                return null;
            if(esNumero(C.charAt(0)))
                return "##";
            if(C.length()==1)
                return C;
            return C.substring(0, 1);
        }
        return null;
    }
    
    public void TA(String TokD, int linea){
        //"q27","q5","q8","q18"
        State="q1";
        if((TokD.length()==5) || (TokD.length()==6) || (TokD.length()==7)){
            switch(TokD.length()){
                case 6:{
                    if(TokD.substring(1,2).equals("f")){  
                        State="q14"; 
                        if(TokD.substring(2,3).equals("u")){
                            State="q15";
                        }//fin si el siguiente caracter es 'r'
                        else{State="22"; break;}
                        if(TokD.substring(3,4).equals("e")){
                            State="q16";
                        }//fin si el siguiente caracter es 'l'
                        else{State="22"; break;}
                        if(TokD.substring(4,5).equals("r")){
                            State="q17";
                        }//fin si el siguiente caracter es i
                        else{
                            State="q19";
                        }//Else si no era i
                        if(TokD.substring(5,6).equals("a")){
                            State="q18";
                        }//fin si era z
                        else{
                            State="q19";break;
                        }
                        break;
                    }//Fin Si la segunda letra es f y de tamaño 6 
                    if(TokD.substring(1,2).equals("r")){  
                        State="q23"; 
                        if(TokD.substring(2,3).equals("r")){
                            State="q24";
                        }//fin si el siguiente caracter es 'r'
                        else{State="28"; break;}
                        if(TokD.substring(3,4).equals("i")){
                            State="q25";
                        }//fin si el siguiente caracter es 'l'
                        else{State="28"; break;}
                        if(TokD.substring(4,5).equals("b")){
                            State="q26";
                        }//fin si el siguiente caracter es i
                        else{
                            State="q28";
                        }//Else si no era i                
                        if(TokD.substring(5,6).equals("a")){
                            State="q27";
                        }//fin si era z
                        else{
                            State="q28";break;
                        }
                        break;
                    }//Fin Si la segunda letra es r y de tamaño 6
            }//Fin caso 6
            
            case 5:{
              if(TokD.substring(1,2).equals("b")){
              State="q2";  
                if(TokD.substring(2,3).equals("a")){
                    State="q3";
                    if(TokD.substring(3,4).equals("j")){
                        State="q4";
                    }//fin si el segundo caracter es 'e'
                    else{State="q21"; break;
                    }//Fin si el segundo caracter no es 'e'
                    if(TokD.substring(4,5).equals("o")){
                    State="q5";
                    }//fin si el siguiente caracter es 'r'
                    else{State="21"; break;}
                    break;
                }//fin si la tercera letra es a
                if(TokD.substring(2,3).equals("r")){
                    State="q6";
                    if(TokD.substring(3,4).equals("i")){
                        State="q7";
                    }//fin si el segundo caracter es 'e'
                    else{State="q20"; break;
                    }//Fin si el segundo caracter no es 'e'
                    if(TokD.substring(4,5).equals("r")){
                    State="q8";
                    }//fin si el siguiente caracter es 'r'
                    else{State="20"; break;}
                    break;
                }//fin si la tercera letra es a
              }//fin si la segunda letras es b
            }//Fin caso 6
                case 7:{
                    if(TokD.substring(1,2).equals("d")){
                        State="q2";
                if(TokD.substring(2,3).equals("e")){
                    State="q3";
                }//fin si el siguiente caracter es 's'
                else{
                    State="q19"; break;
                }
                if(TokD.substring(3,4).equals("n")){
                    State="q4";
                }//fin si el siguiente caracter es 'c'
                else{
                    State="q19"; break;
                }
                
                if(TokD.substring(4,5).equals("t")){
                    State="q5";
                }//fin si el siguiente caracter es e
                else{
                    State="q19"; break;
                }//Else si no era i
                
                if(TokD.substring(5,6).equals("r")){
                    State="q6";
                }//fin si era n
                else{
                    State="q19"; break;
                }//Else si no era i                
                
                if(TokD.substring(6,7).equals("o")){
                    State="q7";
                }//fin si era d
                else{
                    State="q19"; break;
                }//Else si no era i
               
                break;      
                  
                  
                  
              
              }
        
                
        
             if(TokD.substring(1,2).equals("p")){
                    State="q9";

                
                if(TokD.substring(2,3).equals("a")){
                    State="q10";
                }//fin si el siguiente caracter es 's'
                else{
                    State="q19"; break;
                }
                
                if(TokD.substring(3,4).equals("g")){
                    State="q11";
                }//fin si el siguiente caracter es 'c'
                else{
                    State="q19"; break;
                }
                
                if(TokD.substring(4,5).equals("a")){
                    State="q12";
                }//fin si el siguiente caracter es e
                else{
                    State="q19"; break;
                }//Else si no era i
                
                if(TokD.substring(5,6).equals("d")){
                    State="q13";
                }//fin si era n
                else{
                    State="q19"; break;
                }//Else si no era i                
                
                if(TokD.substring(6,7).equals("o")){
                    State="q5";
                }//fin si era d
                else{
                    State="q19"; break;
                }//Else si no era i
               
                break;
              }//fin si el segundo caracter es 'e'
            
          }//Fin caso 7            
        
        }//fin switch
        
                
        
    }//Fin Si la longitud coincide   
    if(State.equals("q27") || State.equals("q5") || State.equals("q8") || State.equals("q18")  || State.equals("q7") ){
                    if(State.equals("q18")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'A' seguida de 'f' seguida de 'u' seguida de 'e' seguida de 'r' seguida de 'a'"+","+linea+"#";
                    }
                    
                    if(State.equals("q27")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'A' seguida de 'r' seguida de 'r' seguida de 'i' seguida de 'b' seguida de 'a'"+","+linea+"#";
                    }
                    if(State.equals("q5")){
                      if(TokD.substring(1,2).equals("b")){  
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'A' seguida de 'b' seguida de 'a' seguida de 'j' seguida de 'o'"+","+linea+"#";
                    }
                    if(TokD.substring(1,2).equals("p")){
                    if(State.equals("q5")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'A' seguida de 'p' seguida de 'a' seguida de 'g' seguida de 'a' seguida de 'd' seguida de 'o'"+","+linea+"#";
                    }                     
                    }
                      
                    }
                    
                    if(State.equals("q8")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'A' seguida de 'b' seguida de 'r' seguida de 'i' seguida de 'r'"+","+linea+"#";
                    }
                    if(State.equals("q7")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'A' seguida de 'd' seguida de 'e' seguida de 'n' seguida de 't' seguida de 'r' seguida de 'o'"+","+linea+"#";
                    } 
                   
                    
                }
                else{
                     Simbolos=Simbolos+"Desconocido"+","+TokD+",,,"+linea+"#";
                }
                State="q0";
  }//Fin Metodo TA
    
    public void TB(String TokD, int linea){
        //"q4","q8"
        State="q1";
        if((TokD.length()==4) ||(TokD.length()==5)){
            switch(TokD.length()){
                case 4:
                {   State="q1";
                    if(TokD.substring(1,2).equals("a")){
                        State="q2";
                    }//fin si el segundo caracter es 'e'
                    else{State="q9"; break;
                    }//Fin si el segundo caracter no es 'e'

                    if(TokD.substring(2,3).equals("s")){
                        State="q3";
                    }//fin si el siguiente caracter es 'r'
                    else{State="q9"; break;}

                    if(TokD.substring(3,4).equals("e")){
                        State="q4";
                    }//fin si el siguiente caracter es 'l'
                    else{State="q9"; break;}           
                    break;
                }//Fin caso 4

                    case 5:
                    {
                    State="q1";
                    if(TokD.substring(1,2).equals("r")){
                        State="q5";
                    }//fin si el segundo caracter es 'e'
                    else{State="q9";
                    }//Fin si el segundo caracter no es 'e'

                    if(TokD.substring(2,3).equals("a")){
                        State="q6";
                    }//fin si el siguiente caracter es 's'
                    else{
                        State="q9"; break;
                    }

                    if(TokD.substring(3,4).equals("z")){
                        State="q7";
                    }//fin si el siguiente caracter es 'c'
                    else{
                        State="q9"; break;
                    }

                    if(TokD.substring(4,5).equals("o")){
                        State="q8";
                    }//fin si el siguiente caracter es e
                    else{
                        State="q9"; break;
                    }//Else si no era i               
                    break;
                }//Fin caso 5            

            }//fin switch



        }//Fin Si la longitud coincide 
    
    if(State.equals("q4") || State.equals("q8")){
                    if(State.equals("q4")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'B' seguida de 'a' seguida de 's' seguida de 'e'"+","+linea+"#";}
                    if(State.equals("q8")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'B' seguida de 'r' seguida de 'a' seguida de 'z' seguida de 'o"+","+linea+"#";}
                }
                else{
                     Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
                }
                State="q0";
 }//Fin Metodo TB 
    
    public void TC(String TokD, int linea){
        State = "q1";
        int tam=TokD.length();
        if( tam==6 ){
            switch(tam){
                case 6:
                    //Cerrar
                    if( TokD.substring(1, 2).equals("e") ){
                        State="q2";
                        if( TokD.substring(2, 3).equals("r") ){
                            State="q3";
                            if( TokD.substring(3, 4).equals("r") ){
                                State="q4";
                                if( TokD.substring(4, 5).equals("a") ){
                                    State="q5";
                                    if( TokD.substring(5).equals("r") ){
                                        State="q6";
                                    }else State="q7";break;//comparacion "r"
                                }else State="q7";break;//comparacion "a"
                            }else State="q7";break;//comparacion "r"
                        }else State="q7";break;//comparacion "r"
                    }else State="q7";break;//comparacion "e"
            }//fin de switch( tam )
        }//fin de if( tam==6 )
        switch( State ){
            case "q6"://Cerrar
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'C' seguida de 'e' seguida de 'r' seguida de 'r' seguida de 'a' seguida de 'r'"+","+linea+"#";
                break;
            default://si State no esta entre los estados validos
                Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
                break;
        }//fin de switch( State )
        State="q0";
    }//Fin Método TC
    
    public void TD(String TokD, int linea){
    //"q3","q10","q15"
    State="q1";
    if((TokD.length()==3) || (TokD.length()==8) || (TokD.length()==9)){
        switch(TokD.length()){
            case 3:
            {
                if(TokD.substring(1,2).equals("e")){
                    State="q2";
                }//fin si el segundo caracter es 'e'
                else{State="q16"; break;
                }//Fin si el segundo caracter no es 'e'
                
                if(TokD.substring(2,3).equals("r")){
                    State="q3";
                }//fin si el ultimo caracter es 'r'
                else{State="q16"; break;}

                break;
            }//Fin caso 3
            
            case 8:
            {
                if(TokD.substring(1,2).equals("e")){
                    State="q2";
                }//fin si el segundo caracter es 'e'
                else{State="q16"; break;
                }//Fin si el segundo caracter no es 'e'
                
                if(TokD.substring(2,3).equals("s")){
                    State="q4";
                }//fin si el siguiente caracter es 'r'
                else{State="16"; break;}
                
                if(TokD.substring(3,4).equals("l")){
                    State="q5";
                }//fin si el siguiente caracter es 'l'
                else{State="16"; break;}
                
                if(TokD.substring(4,5).equals("i")){
                    State="q7";
                }//fin si el siguiente caracter es i
                else{
                    State="q18";
                }//Else si no era i
                
                if(TokD.substring(5,6).equals("z")){
                    State="q8";
                }//fin si era z
                else{
                    State="q18";break;
                }
                
                if(TokD.substring(6,7).equals("a")){
                    State="q9";
                }//fin si era a
                else{
                    State="q18";break;
                }
                
                if(TokD.substring(7,8).equals("r")){
                    State="q10";
                }
                else{
                    State="q18"; break;
                }               
                break;
            }//Fin caso 8

                case 9:
                {
                if(TokD.substring(1,2).equals("e")){
                    State="q2";
                }//fin si el segundo caracter es 'e'
                else{State="q16";
                }//Fin si el segundo caracter no es 'e'
                
                if(TokD.substring(2,3).equals("s")){
                    State="q4";
                }//fin si el siguiente caracter es 's'
                else{
                    State="q16"; break;
                }
                
                if(TokD.substring(3,4).equals("c")){
                    State="q6";
                }//fin si el siguiente caracter es 'c'
                else{
                    State="q16"; break;
                }
                
                if(TokD.substring(4,5).equals("e")){
                    State="q11";
                }//fin si el siguiente caracter es e
                else{
                    State="q17"; break;
                }//Else si no era i
                
                if(TokD.substring(5,6).equals("n")){
                    State="q12";
                }//fin si era n
                else{
                    State="q17"; break;
                }//Else si no era i                
                
                if(TokD.substring(6,7).equals("d")){
                    State="q13";
                }//fin si era d
                else{
                    State="q17"; break;
                }//Else si no era i
                
                if(TokD.substring(7,8).equals("e")){
                    State="q14";
                }//fin si era e
                else{
                    State="q17"; break;
                }//Else si no era i                
                
                if(TokD.substring(8,9).equals("r")){
                    State="q15";
                }//fin si era r
                else{
                    State="q17"; break;
                }//Else si no era i                
                break;
            }//Fin caso 9            
        
        }//fin switch
                
    }//Fin Si la longitud coincide 
   if(State.equals("q3") || State.equals("q10") || State.equals("q15") ){
                    if(State.equals("q3")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'D' seguida de 'e' seguida de 'r'"+","+linea+"#";
                    }
                    
                    if(State.equals("q10")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'D' seguida de 'e' seguida de 's' seguida de 'l' seguida de 'i' seguida de 'z' seguida de 'a' seguida de 'r'"+","+linea+"#";
                    }
                    
                                        
                    if(State.equals("q15")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'D' seguida de 'e' seguida de 's' seguida de 'c' seguida de 'e' seguida de 'n' seguida de 'd' seguida de 'e' seguida de 'r'"+","+linea+"#";
                    }
                }
                else{
                    
                     Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
                }
                State="q0";
    
  }//Fin Metodo TD

    public void TE(String TokD, int linea){
    //"q6","q14"
    State="q1";
    if((TokD.length()==6) ||(TokD.length()==9)){
        switch(TokD.length()){
     
            
            case 6:
            {   State="q1";
                if(TokD.substring(1,2).equals("l")){
                    State="q2";
                }//fin si el segundo caracter es 'e'
                else{State="q15"; break;
                }//Fin si el segundo caracter no es 'e'
                
                if(TokD.substring(2,3).equals("e")){
                    State="q3";
                }//fin si el siguiente caracter es 'r'
                else{State="q15"; break;}
                
                if(TokD.substring(3,4).equals("v")){
                    State="q4";
                }//fin si el siguiente caracter es 'l'
                else{State="q15"; break;}
                
                if(TokD.substring(4,5).equals("a")){
                    State="q5";
                }//fin si el siguiente caracter es i
                else{
                    State="q15";
                }//Else si no era i
                
                if(TokD.substring(5,6).equals("r")){
                    State="q6";
                }//fin si era z
                else{
                    State="q15";break;
                }
                            
                break;
            }//Fin caso 6

                case 9:
                {
                State="q1";
                if(TokD.substring(1,2).equals("n")){
                    State="q7";
                }//fin si el segundo caracter es 'e'
                else{State="q15";
                }//Fin si el segundo caracter no es 'e'
                
                if(TokD.substring(2,3).equals("c")){
                    State="q8";
                }//fin si el siguiente caracter es 's'
                else{
                    State="q15"; break;
                }
                
                if(TokD.substring(3,4).equals("e")){
                    State="q9";
                }//fin si el siguiente caracter es 'c'
                else{
                    State="q15"; break;
                }
                
                if(TokD.substring(4,5).equals("n")){
                    State="q10";
                }//fin si el siguiente caracter es e
                else{
                    State="q15"; break;
                }//Else si no era i
                
                if(TokD.substring(5,6).equals("d")){
                    State="q11";
                }//fin si era n
                else{
                    State="q15"; break;
                }//Else si no era i                
                
                if(TokD.substring(6,7).equals("i")){
                    State="q12";
                }//fin si era d
                else{
                    State="q15"; break;
                }//Else si no era i
                
                if(TokD.substring(7,8).equals("d")){
                    State="q13";
                }//fin si era e
                else{
                    State="q15"; break;
                }//Else si no era i                
                
                if(TokD.substring(8,9).equals("o")){
                    State="q14";
                }//fin si era r
                else{
                    State="q15"; break;
                }//Else si no era i                
                break;
            }//Fin caso 9            
        
        }//fin switch
    
               
    
    }//Fin Si la longitud coincide  
    
     if(State.equals("q6") || State.equals("q14")){
                    if(State.equals("q6")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'E' seguida de 'l' seguida de 'e' seguida de 'v' seguida de 'a' seguida de 'r'"+","+linea+"#";
                    }
                     if(State.equals("q14")){
                    Simbolos=Simbolos+TokD+","+TokD+",Reservada,'E' seguida de 'n' seguida de 'c' seguida de 'e' seguida de 'n' seguida de 'd' seguida de 'i' seguida de 'd' seguida de 'o'"+","+linea+"#";
                    }
                }
                else{
                     Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
                }
                State="q0";
 }//Fin Metodo TE 
    
    public void TM(String TokD, int linea){
        State = "q1";
        int tam=TokD.length();
        if( tam==8 ){
            switch(tam){
                case 8:
                    //Mientras
                    if( TokD.substring(1, 2).equals("i") ){
                        State="q2";
                        if( TokD.substring(2, 3).equals("e") ){
                            State="q3";
                            if( TokD.substring(3, 4).equals("n") ){
                                State="q4";
                                if( TokD.substring(4, 5).equals("t") ){
                                    State="q5";
                                    if( TokD.substring(5, 6).equals("r") ){
                                        State="q6";
                                        if( TokD.substring(6, 7).equals("a") ){
                                            State="q7";
                                            if( TokD.substring(7).equals("s") ){
                                                State="q8";
                                            }else State="q9";break;//comparacion "s"
                                        }else State="q9";break;//comparacion "a"
                                    }else State="q9";break;//comparacion "r"
                                }else State="q9";break;//comparacion "t"
                            }else State="q9";break;//comparacion "n"
                        }else State="q9";break;//comparacion "e"
                    }else State="q9";break;//comparacion "i"
            }//fin de switch( tam )
        }//fin de if( tam==8 )
        switch( State ){
            case "q8"://Mientras
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'M' seguida de 'i' seguida de 'e' seguida de 'n' seguida de 't' seguida de 'r' seguida de 'a' seguida de 's'"+","+linea+"#";
                break;
            default://si State no esta entre los estados validos
                if( esNomMet(TokD) ){
                    Simbolos = Simbolos + "Nombre Método,"+TokD+",No,Caracter seguido de caracteres,"+linea+"#";
                }else{
                    Simbolos = Simbolos + "Elemento Desconocido,"+TokD+",No,?,"+linea+"#";
                }
                break;
        }//fin de switch( State )
        State="q0";
    }//Fin del método TM
    
    public void TN(String TokD, int linea){
        State = "q1";
        int tam=TokD.length();
        if( tam==2 ){
            switch(tam){
                case 2:
                    //No
                    if( TokD.substring(1, 2).equals("o") ){
                        State="q2";break;
                    }else State="q3";break;//comparacion "r"
            }//fin de switch( tam )
        }//fin de if( tam==2 )
        switch( State ){
            case "q2"://No
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'N' seguida de 'o'"+","+linea+"#";
                break;
            default://si State no esta entre los estados validos
                Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
                break;
        }//fin de switch( State )
        State="q0";
    }//Fin Método TC
    
    public void TS(String TokD, int linea){
    //"q2"
        State="q1";
        switch(TokD.length()){
            case 2:{//Si
                if(TokD.substring(1,2).equals("i")){
                    State="q2";
                }//fin si el segundo caracter es 'i'
                else{
                    State="q8"; 
                    break;
                }//Fin si el segundo caracter no es 'i'
            }//Fin caso 2
            case 6://String
                if(TokD.substring(1,2).equals("t")){
                    State = "q3";
                    if(TokD.substring(2,3).equals("r")){
                        State = "q4";
                        if(TokD.substring(3,4).equals("i")){
                            State = "q5";
                            if(TokD.substring(4,5).equals("n")){
                                State = "q6";
                                if(TokD.substring(5).equals("g")){
                                    State = "q7";
                                }else { State = "q8"; }
                            }else { State = "q8"; }
                        }else { State = "q8"; }
                    }else { State = "q8"; }
                }else { State = "q8"; }
                break;//fin de caso 6
        }//fin switch
        switch (State) {
            case "q2":
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'s' seguida de 'i'"+","+linea+"#";
                break;
            case "q7":
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'s' seguida de 't' seguida de 'r' seguida de 'i' seguida de 'n' seguida de 'g'"+","+linea+"#";
                break;
            default:
                System.out.println("Entro a desconocido por: "+TokD);
                Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
                break;
        }
        State="q0";
    
   
 }//Fin Metodo TB 
  
    public void TO(String TokD, int linea){
    //"q11"
        State="q0";
        switch(TokD.length()){
            case 1:{
                State="q1";
            }//Fin caso 1  
        }//fin switch
            if(State.equals("q1")){
                Simbolos=Simbolos+TokD+","+TokD+",Reservada, 'o'"+","+linea+"#";
            }
            else{
                 Simbolos=Simbolos+"Desconocido"+","+TokD+",,"+","+linea+"#";
            }
            State="q0";
    }//Fin Metodo TB 
    
    public void Operadores(String Cad){
     for(int i=0;i<Cad.length();i++){
         if((Cad.charAt(i)>=40 && Cad.charAt(i)<=47) || (Cad.charAt(i)>=60 && Cad.charAt(i)<=62) && (Cad.charAt(i)!=46)){
             Simbolos=Simbolos+"Operador Relacional"+","+Cad.charAt(i)+","+", Caracter "+Cad.charAt(i)+"#";
         }
     }
 }

    public void Numeros(String Cad){
        for(int i=0;i<Cad.length();i++){
            if((Cad.charAt(i)>=40 && Cad.charAt(i)<=47) || (Cad.charAt(i)>=60 && Cad.charAt(i)<=62) && (Cad.charAt(i)!=46)){
                Simbolos=Simbolos+"Desconocido"+","+Cad.charAt(i)+","+", ?? "+"#";
            }
        }
    }
    
    public void TP(String TokD, int linea){
        State = "q1";
        int tam=TokD.length();
        if( tam==5 ){
            switch(tam){
                case 5:
                    //Cerrar
                    if( TokD.substring(1, 2).equals("i") ){
                        State="q2";
                        if( TokD.substring(2, 3).equals("n") ){
                            State="q3";
                            if( TokD.substring(3, 4).equals("z") ){
                                State="q4";
                                if( TokD.substring(4, 5).equals("a") ){
                                    State="q5";
                                }else State="q7";break;//comparacion "a"
                            }else State="q7";break;//comparacion "r"
                        }else State="q7";break;//comparacion "r"
                    }else State="q7";break;//comparacion "e"
            }//fin de switch( tam )
        }//fin de if( tam==6 )
        switch( State ){
            case "q5"://Cerrar
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'P' seguida de 'i' seguida de 'n' seguida de 'z' seguida de 'a',"+linea+"#";
                break;
            default://si State no esta entre los estados validos
                Simbolos=Simbolos+"Desconocido"+","+TokD+""+"#";
                break;
        }//fin de switch( State )
        State="q0";
    }//Fin Método TC
        
    public void TI(String TokD,int linea){
        State = "q1";
        int tam=TokD.length();
        if( tam==3 ){
            switch(tam){
                case 3:
                    //Cerrar
                    if( TokD.substring(1, 2).equals("z") ){
                        State="q2";
                        if( TokD.substring(2, 3).equals("q") ){
                            State="q3";
                        }else State="q7";break;//comparacion "q"
                    }else //comparación "z"
                        if( TokD.substring(1, 2).equals("n") ){
                        State="q2";
                        if( TokD.substring(2, 3).equals("t") ){
                            State="q3";
                        }else State="q7";break;//comparación "t"
                    }else State="q7";break;//comparacion "n"
            }//fin de switch( tam )
        }//fin de if( tam==6 )
        switch( State ){
            case "q3"://Cerrar
                Simbolos=Simbolos+TokD+","+TokD+",Reservada,'I' seguida de 'z' seguida de 'q',"+linea+"#";
                break;
            default://si State no esta entre los estados validos
                Simbolos=Simbolos+"Desconocido"+","+TokD+""+"#";
                break;
        }//fin de switch( State )
        State="q0";
    }//Fin Método TC
   
}

