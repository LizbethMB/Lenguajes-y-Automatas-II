
import java.lang.reflect.Array;
import java.util.Vector;
import static javax.swing.JOptionPane.showMessageDialog;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismae
 */
public class Sintáctico {
    Pila cLP=new Pila();//Variable para llevar el control de las llaves y parentesis
    String State="q0";
    String cad="";
    public String cLlavesPar(String C){//Metodo para llevar el control de las llaves y parentesis
        String Llaves="";
        boolean b;
        String S[]=C.split("\n");
        for(int j=0; j<S.length; j++){
            for(int i=0; i<S[j].length(); i++){
                //Llaves
                if(S[j].charAt(i) == 123 ){
                    b = cLP.apilar("{");
                    if(b==false){
                        return "No se pudo añadir \"{\"  a la pila en fila "+(j+1)+" columa: "+(i+1);
                    }
                }else if( S[j].charAt(i) == 125 ){
                    b = cLP.retirar("{");
                    if(b==false){
                        return "Error de llaves en fila "+(j+1)+" columa: "+(i+1);
                    }
                }
                //Parentesis
                if(S[j].charAt(i) == 40 ){
                    b = cLP.apilar("(");
                    if(b==false){
                        return "No se pudo añadir \"(\" a la pila en fila "+(j+1)+" columa: "+(i+1);
                    }
                }else if( S[j].substring(i,i+1).equals(")") ){
                    b = cLP.retirar("(");
                    if(b==false){
                        return "Error de parentesis en fila "+(j+1)+" columa: "+(i+1);
                    }
                }
            }
        }
        //si esta vacia retorna verdadero, osea que todo esta cerrado como debe de ser
        if(cLP.estaVacia())
            return null;
        else
            return "Falta cerrar: "+cLP.mostrado();
    }
    
    public String MoverInstruccion(String Texto){
        String Valida="";//Bandera para saber si hay errores en la instruccion de movimiento
        String state="q0";//Estado inicial en 0

        int Aux=0,Aux2=0,Aux3=0;
        for(int i=0;i<Texto.length();i++){
            if(Texto.substring(i,i+1).equals("(")){Aux++;}
            if(Texto.substring(i,i+1).equals(".")){Aux2++;}
            if(Texto.substring(i,i+1).equals(")")){Aux3++;}
        }
        //System.out.println(Texto);
        //System.out.println("1: "+Aux+" 2: "+Aux2+" 3: "+Aux3);
        if((Aux==1 && Aux2==1 && Aux3==1)){
            String Uno[]=Texto.split("\\."); //Hacemos un split con el punto de la instruccion
            String Instruccion[]=new String[5]; // Hacemos una arreglo donde guardaremos todas las partes de la instruccion
            Instruccion[0]=Uno[0]; //La primera palabra se mantendrá igual
            Instruccion[1]=Texto.substring(Texto.indexOf(".")+1, Texto.indexOf("(")); // El segun elemento de la Instruccion serán los siguientes tres caracteres despues del motor seleccionado
            Instruccion[2]=Texto.substring(Texto.indexOf("("),Texto.indexOf("(")+1); //El tercer elemento de nuestra instruccion será el primer caracter despues de la dirección (Tres caracteres seleccionados)
            Instruccion[3]=Texto.substring(Texto.indexOf("(")+1,Texto.indexOf(")"));
            Instruccion[4]=Texto.substring(Texto.length()-2, Texto.length()-1); //El ultimo elemento de nuestra instruccion será el ultimo caracter antes del punto y coma
            switch(Instruccion[0]){
                case "Base":
                   
                    state="q1"+"----->";
                    cad=cad+state;
                    if(Instruccion[1].equals("Der")){
                        state="q6";
                        cad=cad+"----->"+state; 

                    }else{
                        if(Instruccion[1].equals("Izq")){
                            state="q2";
                               cad=cad+"----->"+state; 
                        }else{
                            state="q9"; 
                              cad=cad+"----->"+state;
                            Valida=Valida+"Error Sintactico;; El motor "+Instruccion[0]+ " solo puede girar a la derecha o Izquierda#";
                            break;
                        }
                    }
                    if(Instruccion[2].equals("(")){
                        state="q3";
                   cad=cad+"----->"+state;
                    }else{
                        state="q9";Valida=Valida+"Error Sintactico;; Elemento despues del parentesis#"; 
                      cad=cad+"----->"+state;
                        break;
                    }
                    if(Instruccion[3].equals("Encendido")){
                        state="q4";
                       cad=cad+"----->"+state;
                    }else{
                        if(Instruccion[3].equals("Apagado")){
                            state="q7";
                             cad=cad+"----->"+state;
                        } else{
                            state="q9";
                              cad=cad+"----->"+state;
                            Valida=Valida+"Error Sintactico;; El estado de movimiento para "+Instruccion[0]+"debe ser Encendido o Apagado#";
                            break;
                        }
                    }
                    if(Instruccion[4].equals(")")){
                        state="q5";
                           cad=cad+"----->"+state;
                    }else{
                        state="q9"; Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final#";
                           cad=cad+"----->"+state;
                        break;
                    }
                    break;
                case "Deslizar":
                    state="q11";
                    if(Instruccion[1].equals("Der")){
                        state="q8";
                    }else{
                        if(Instruccion[1].equals("Izq")){
                            state="q3";
                        }else{
                            state="q10";
                            Valida=Valida+"Error Sintactico;; El Deslizador solo puede moverse a la Derecha O Izquierda#";
                            break;
                        }
                    }
                    if(Instruccion[2].equals("(")){
                        state="q4";
                    }else{
                        state="q10";
                        Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final#";
                        break;
                    }
                    if(Instruccion[3].equals("Encendido")){
                        state="q5";
                    }else{
                       if(Instruccion[3].equals("Apagado")){
                            state="q9";
                        } else{
                            state="q10";
                            Valida=Valida+"Error Sintactico;; El estado de movimiento para "+Instruccion[0]+"  debe ser Encendido o Apagado#"; 
                            break;
                        }
                    }
                    if(Instruccion[4].equals(")")){
                        state="q6";
                    }else{
                        state="q10";
                        Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final#";
                        break;
                    }
                    break;
                case "Pinza":
                    state="q1";
                    if(Instruccion[1].equals("Arriba")){
                        state="q8";
                    }else{
                        if(Instruccion[1].equals("Abajo")){
                            state="q3";
                        }else{
                            if(Instruccion[1].equals("Abrir")){
                                state="q9";
                            }else{
                                if(Instruccion[1].equals("Cerrar")){
                                    state="q12";
                                }else{
                                    state="q13";
                                    Valida=Valida+"Error Sintactico;; Indicar Direccion o Accion de la Pinza (Arriba | Abajo | Abrir | Cerrar)#";
                                    break;
                                }
                            }
                       } 
                    }
                    if(state.equals("q8")|| state.equals("q3")){
                        if(Instruccion[2].equals("(")){
                            state="q4";
                        }else{
                            state="q13";
                            Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final#";
                            break;
                        }
                        if(Instruccion[3].equals("Encendido")){
                            state="q5";
                        }else{
                            if(Instruccion[3].equals("Apagado")){
                                state="q9";
                            } else{
                                state="q10";
                                Valida=Valida+"Error Sintactico;; El estado del movimiento para "+Instruccion[0]+" debe ser Encendido o Apagado#";
                                break;
                            }
                        }  
                        if(Instruccion[4].equals(")")){
                            state="q6";
                        }else{
                            state="q13";
                            Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final#";
                            break;
                        }
                    break;
                    }else{
                        if(Instruccion[2].equals("(")){
                            state="q10";
                        }else{
                            state="q13"; Valida=Valida+"Error Sintactico;; Elemento despues del parnetesis final#"; 
                            break;
                        }
                    if(Instruccion[3].equals("")){
                        state="q10";
                    }else{
                        state="q13";Valida=Valida+"Error Sintactico;; Las acciones Abrir o Cerrar de "+Instruccion[0]+" no necesitan estado#"; 
                        break;
                    }
                    if(Instruccion[4].equals(")")){
                        state="q11";
                    }else{
                        state="q13";Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final";
                        break;
                    }
                    break;           
                }
                case "Brazo":
                    state="q1";
                    if(Instruccion[1].equals("Elevar")){
                        state="q8";
                    }else{
                        if(Instruccion[1].equals("Descender")){
                            state="q3";
                        }else{
                            state="q10";Valida=Valida+"Error Sintactico;; El brazo solo puede Elevarse y Descender#";
                            break;
                        }
                    }
                    if(Instruccion[2].equals("(")){
                        state="q4";
                    }else{
                        System.out.println("Error en Instruccion[2]");
                        state="q10"; Valida=Valida+"Error Sintactico;; Elemento despues del parentesis final#";
                        break;
                    }
                    if(Instruccion[3].equals("Encendido")){
                        state="q5";
                    }else{
                        if(Instruccion[3].equals("Apagado")){
                            state="q9";
                        } else{
                            state="q10";
                            System.out.println("Error en Instruccion[3]");
                            Valida=Valida+"Error Sintactico;; El estado del movimiento para "+Instruccion[0]+" debe ser Encendido o Apagado#";
                            break;
                        }
                    }
                    if(Instruccion[4].equals(")")){
                        state="q6";
                    }else{
                        state="q10";
                        System.out.println("Error en Instruccion[4]: "+Instruccion[4]);
                        Valida=Valida+"Error Sintactico;; Elemento fuera de lugar#";
                        break;
                    }
                break;
                default: 
                    Valida=Valida+"Error Sintactico;; Instruccion no Valida,"+Instruccion[0]+" No es un motor valido #";
                    break;
            }
        }//Fin Si no hubo errores de tamaño
        else{
            String Uno[]=Texto.split(" ");
            //System.out.println(Uno.length);
            //if(Uno.length==4){ //Hacemos un split con el punto de la instruccion
                //String Instruccion[]=new String[4]; // Hacemos una arreglo donde guardaremos todas las partes de la instruccion
                if(Uno[0].equals("Int") || Uno[0].equals("String") || Uno[0].equals("Double") ){
                    if( tipoDatos(Texto)==false){
                        Valida=Valida+"Error Sintactico;; Expresión no valida#";
                    }
                }
            /*}*/else if(!Uno[0].equals("}") && !Uno[0].equals(")")){
                System.out.println("Encuentra else: "+mostrar(Uno));
                Valida=Valida+"Error Sintactico;; Instruccion no Valida, Debe ser movimiento o declaracion#";
            }
        }
        return Valida;      
    }//Final metodo 
    
    public String[] generaArray(String Exp){
        boolean letra=false, num=false, opR=false;
        String Simbolos="";
        for(int i=0; i<Exp.length(); i++){
            //validación de espacio
            if( Exp.charAt(i)==32 || Exp.charAt(i)==44 ){
                if( letra==true || num==true || opR==true ){//verifica si se estaba escribiendo algo antes
                    letra = num = opR = false;//lo que sea que este escribiendo lo pasa a que se dejo de escribir
                    Simbolos = Simbolos+"#";
                }
            }
            //validación de coma
            if( Exp.charAt(i)==44 ){
                if( letra==true || num==true || opR==true ){//verifica si se estaba escribiendo algo antes
                    letra = num = opR = false;//lo que sea que este escribiendo lo pasa a que se dejo de escribir
                    Simbolos = Simbolos+"#,#";
                }
            }
            //validación de punto y coma
            if( Exp.charAt(i)==59 ){
                if( letra==true || num==true || opR==true ){//verifica si se estaba escribiendo algo antes
                    letra = num = opR = false;//lo que sea que este escribiendo lo pasa a que se dejo de escribir
                    Simbolos = Simbolos+"#;#";
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
    
    private String mostrar(String[] s){
        String temp="";
        for(int i=0; i<s.length; i++){
            if(!s[i].equals(""))
                temp=temp+s[i]+" | ";
        }
        return temp;
    }
    
    public boolean validaTask(String Q){
        String[] Texto = generaArray(Q);
        this.State="q1";
        if( esNomMet(Texto[1]) ){
            this.State="q2";
            if(Texto[2].equals("(")){
                this.State="q3";
                String S[] = recortarArray(Texto, 3);
                S = param(S);
                if(S!=null){
                    if( S[1].equals("{") ){
                        S=recortarArray(S, 1);//asignamos a S todo lo que este después de ;
                        this.State="q8";
                        
                        //AQUI VA EL CÓDIGO DE SEPARAR TODO POR ;
                        
                        
                        
                    }
                }else
                    this.State="q9";
            }
        }else{
            this.State="q9";
        }
        return this.State.equals("q8");
    }
    
    private String[] param(String[] S){//devuelve el arreglo de lo que falta por analizar
        String tempState="q0";
        switch(S[0]){
            case "String":
                tempState="q1";
                break;
            case "Int":
                tempState="q1";
                break;
            case "Double":
                tempState="q1";
                break;
            case ")":
                return recortarArray(S, 1);//ya no hay parametros por analizar y devuelve lo que falta analizar
            default:
                tempState="q3";
                break;
        }
        if( esId(S[1]) && tempState.equals("q1") ){//si el estado anterior fue q1 y la siguiente palabra es una variable
            tempState="q2";
            if(S[2].equals("&")){//evalua si el sigiente valor es una ,
                return param(recortarArray(S, 3));//vuelve a analizar lo que falta como parametro
            }else if(S[2].equals(")") ){
                return recortarArray(S, 2);//ya no hay parametros por analizar y devuelve lo que falta analizar
            }else{
                tempState="q3";//si no es coma entonces es un valor no valido
            }
        }else{//si no es variable o el estado anterior no era q1
            tempState="q3";
        }
        if(tempState.equals("q2")){
            return recortarArray(S, 2);//Devuelve lo que falta por analizar
        }else{
            return null;
        }
    }
    
    private boolean esNomMet(String S){
        if(S==null || S.equals("")){
            return false;
        }
        for(int k=0;k<S.length();k++){
            if( !esLetraMay(S.charAt(k)) ){
                return false;
            }
        }
        return true;
    }
    
    public boolean tipoDatos(String Q){//String,Int,Double
        String S[] = generaArray(Q);
        String tempState="q0";
        switch(S[0]){
            case "String":
                tempState="q1";
                break;
            case "Int":
                tempState="q1";
                break;
            case "Double":
                tempState="q1";
                break;
            default:
                tempState="q6";
                break;
        }
        if( tempState.equals("q1") && esId(S[1]) ){
            tempState="q2";
            if(S.length==3){
                if(S[2].equals(";")){
                    tempState="q3";
                }
            }else{
                if(S[2].equals("=")){
                    //EXPRECION
                    if( validaExp(recortarArray(S, 3))==true ){
                        tempState="q5";
                    }else{
                        tempState="q6";
                    }
                    
                }
            }
        }
        switch(tempState){
            case "q3":
                return true;
            case "q5":
                return true;
            default:
                return false;
        }
    }
    
    private String[] recortarArray(String[] A, int desde){
        //System.out.println("Original: "+mostrar(A));
        String[] S = new String[A.length-desde];
        int c=0;
        for(int i=desde; i<A.length; i++){
            S[c] = A[i];
            c++;
        }
        //System.out.println("Recortado: "+mostrar(S));
        return S;
    }
    
    private boolean validaExp(String[] S){
        //Variable para saber que fue lo ultimo analizado
        boolean b=false;//false-> operador, true->id o numero
        if(S[0].equals("\"")){
            if(!S[S.length-1].equals("\"")){
                System.out.println("Expresión String no valida");
                return false;
            }
        }else{
            for(int i=0; i<S.length; i++){
                if( b==true ){
                    b=false;
                    if( !S[i].equals("+") && !S[i].equals("-") && !S[i].equals("*") && !S[i].equals("/") && !S[i].equals(";") ){
                        return false;
                    }
                }else{
                    b=true;
                    if( !esNum(S[i]) && !esId(S[i]) ){
                        return false;
                    }
                }
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
    
    private boolean esLetraMin(char l){ //devuelve verdadero si es una letra minuscula
        return l>=97 && l<=122;
    }
    
    private boolean esLetraMay(char l){
        return l>=65 && l<=90;
    }

    private boolean esNum(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
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
    
    private boolean esOpR(char l){//devuelve verdadero si es un operador relacional ( ) < = > & { }
        //125,123,40,41
        if( (l>=60 && l<=62) || l==38 || l==40 || l==41 || l==46 || l==42 || l==43 || l==47 || l==123 || l==125)
            return true;
        else
            return false;
    }
    
    private boolean esOpA(String l){//Es operador de agrupación
        if( !l.equals("(") && !l.equals(")") && !l.equals("{") && !l.equals("}") ){
            return false;
        }else
            return true;
    }
    
 }
