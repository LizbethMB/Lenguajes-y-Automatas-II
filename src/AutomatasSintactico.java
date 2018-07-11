/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author basix
 */
public class AutomatasSintactico {
    String State="";//para task
    String tempState="";//para parametros
    Pila cLP=new Pila();//Variable para llevar el control de las llaves y parentesis
    String caja="";
   
    public String Recorrido(String Texto){ //Comenntario Git
     String state="q0";   
     String Valida="";
        
     int Aux=0,Aux2=0,Aux3=0;
         for(int i=0;i<Texto.length();i++){
           if(Texto.substring(i,i+1).equals("(")){Aux++;}
             if(Texto.substring(i,i+1).equals(".")){Aux2++;}
               if(Texto.substring(i,i+1).equals(")")){Aux3++;}
            }
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
                    Valida="Automata Base: "+Valida+state; state="q1"; Valida=Valida+"-->"+state;
                    if(Instruccion[1].equals("Der")){
                        state="q6"; Valida=Valida+"-->"+state; 
                    }else{
                        if(Instruccion[1].equals("Izq")){
                            state="q2"; Valida=Valida+"-->"+state;
                        }else{
                            state="q9"; 
                            Valida=Valida+"-->"+state; break;
                        }
                    }
                    if(Instruccion[2].equals("(")){
                        state="q3"; Valida=Valida+"-->"+state;
                    }else{
                        state="q9";Valida=Valida+"-->"+state; 
                        break;
                    }
                    if(Instruccion[3].equals("Encendido")){
                        state="q6"; Valida=Valida+"-->"+state;
                    }else{
                        if(Instruccion[3].equals("Apagado")){
                            state="q7"; Valida=Valida+"-->"+state;
                        } else{
                            state="q9";
                            Valida=Valida+"-->"+state;
                            break;
                        }
                    }
                    if(Instruccion[4].equals(")")){
                        state="q4"; Valida=Valida+"-->"+state;
                    }else{
                        state="q9"; Valida=Valida+"-->"+state;
                        break;
                    }
                    break;
                case "Deslizar":
                    Valida="Automata Deslizar: "+Valida+state;
                    state="q8";
                    Valida=Valida+"-->"+state;
                    if(Instruccion[1].equals("Der")){
                        state="q6"; Valida=Valida+"-->"+state;
                    }else{
                        if(Instruccion[1].equals("Izq")){
                            state="q2"; Valida=Valida+"-->"+state;
                        }else{
                            state="q9";
                            Valida=Valida+"-->"+state;
                            break;
                        }
                    }
                    if(Instruccion[2].equals("(")){
                        state="q3";
                    }else{
                        state="q9";
                        Valida=Valida+"-->"+state;
                        break;
                    }
                    if(Instruccion[3].equals("Encendido")){
                        state="q4"; Valida=Valida+"-->"+state;
                    }else{
                       if(Instruccion[3].equals("Apagado")){
                            state="q7"; Valida=Valida+"-->"+state;
                        } else{
                            state="q9";
                            Valida=Valida+"-->"+state;
                            break;
                        }
                    }
                    if(Instruccion[4].equals(")")){
                        state="q5"; Valida=Valida+"-->"+state;
                    }else{
                        state="q9";
                        Valida=Valida+"-->"+state;
                        break;
                    }
                    break;
                case "Pinza":
                    Valida="Automata Pinza: "+Valida+state;
                    state="q1"; Valida=Valida+"-->"+state;
                    if(Instruccion[1].equals("Arriba")){
                        state="q6"; Valida=Valida+"-->"+state;
                    }else{
                        if(Instruccion[1].equals("Abajo")){
                            state="q2"; Valida=Valida+"-->"+state;
                        }else{
                            if(Instruccion[1].equals("Abrir")){
                                state="q7"; Valida=Valida+"-->"+state;
                            }else{
                                if(Instruccion[1].equals("Cerrar")){
                                    state="q9"; Valida=Valida+"-->"+state;
                                }else{
                                    state="q10";
                                    Valida=Valida+"-->"+state;
                                    break;
                                }
                            }
                       } 
                    }
                    if(state.equals("q2")|| state.equals("q6")){
                        if(Instruccion[2].equals("(")){
                            state="q3"; Valida=Valida+"-->"+state;
                        }else{
                            state="q10";
                            Valida=Valida+"-->"+state;
                            break;
                        }
                        if(Instruccion[3].equals("Encendido")){
                            state="q4"; Valida=Valida+"-->"+state;
                        }else{
                            if(Instruccion[3].equals("Apagado")){
                                state="q11"; Valida=Valida+"-->"+state;
                            } else{
                                state="q10";
                                Valida=Valida+"-->"+state;
                                break;
                            }
                        }  
                        if(Instruccion[4].equals(")")){
                            state="q5"; Valida=Valida+"-->"+state;
                        }else{
                            state="q10";
                            Valida=Valida+"-->"+state;
                            break;
                        }
                    break;
                    }else{
                        if(Instruccion[2].equals("(")){
                            state="q8"; Valida=Valida+"-->"+state;
                        }else{
                            state="q10"; Valida=Valida+"-->"+state;
                            break;
                        }
                    /*if(Instruccion[3].equals("")){
                        state="q10"; Valida=Valida+"-->"+state;
                    }else{
                        state="q13";Valida=Valida+"-->"+state; 
                        break;
                    }*/
                    if(Instruccion[4].equals(")")){
                        state="q5"; Valida=Valida+"-->"+state;
                    }else{
                        state="q10"; Valida=Valida+"-->"+state;
                        break;
                    }
                    break;           
                }
                case "Brazo":
                    Valida="Automata Brazo: "+Valida+state;
                    state="q1"; Valida=Valida+"-->"+state;
                    if(Instruccion[1].equals("Elevar")){
                        state="q2"; Valida=Valida+"-->"+state;
                    }else{
                        if(Instruccion[1].equals("Descender")){
                            state="q6"; Valida=Valida+"-->"+state;
                        }else{
                            state="q8";Valida=Valida+"-->"+state;
                            break;
                        }
                    }
                    if(Instruccion[2].equals("(")){
                        state="q3"; Valida=Valida+"-->"+state;
                
                    }else{
                        state="q8"; Valida=Valida+"-->"+state;
                        break;
                    }
                    if(Instruccion[3].equals("Encendido")){
                        state="q4"; Valida=Valida+"-->"+state;
                    }else{
                        if(Instruccion[3].equals("Apagado")){
                            state="q7"; Valida=Valida+"-->"+state;
                        } else{
                            state="q8";
                            Valida=Valida+"-->"+state;
                            break;
                        }
                    }
                    if(Instruccion[4].equals(")")){
                        state="q5"; Valida=Valida+"-->"+state;
                    }else{
                        state="q8";
                        Valida=Valida+"-->"+state;
                        break;
                    }
                break;
                default: 
                    Valida=Valida+"Error, Automata para "+Instruccion[0]+" no encontrado#";
        
                     break;
            }
        }//Fin Si no hubo errores de tamaño
        else{
            String Uno[]=Texto.split(" ");
            if(Uno[0].equals("Int")){
                Valida=Valida+tipoDatos(Texto)+"#";
            }else if(Uno[0].equals("task")){
                Valida="Automata task: ";
                    Valida=Valida+validaTask(Texto)+"\nAutomata parametros: "+tempState;
            }else if(!Uno[0].equals("}") && !Uno[0].equals(")")){
                Valida=Texto+" No es una instrucción valida";
            }
        }
        return Valida+"#";      

    }
    
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
    
    public String validaTask(String Q){
        String[] Texto = generaArray(Q);
        State+="q0-->";
        State+="q1-->";
        if( esNomMet(Texto[1]) ){
            State+="q2-->";
            if(Texto[2].equals("(")){
                State+="q3-->";
                String S[] = recortarArray(Texto, 3);
                S = param(S);
                if(S!=null){
                    State+="q5-->";
                    if( S[1].equals("{") ){
                        S=recortarArray(S, 1);//asignamos a S todo lo que este después de ;
                        this.State+="q6-->q7";
                        if(cLlavesPar(caja)==null){
                            this.State+="-->q8";
                        }
                        //AQUI VA EL CÓDIGO DE SEPARAR TODO POR ;
                    }
                }else
                    State+="q9-->";
            }
        }else{
            State+="q9-->";
        }
        return State;
    }
    
    private String[] param(String[] S){//devuelve el arreglo de lo que falta por analizar
        State+="q4-->";
        tempState+="q0-->";
        switch(S[0]){
            case "String":
                tempState+="q1-->";
                break;
            case "Int":
                tempState+="q1-->";
                break;
            case "Double":
                tempState+="q1-->";
                break;
            case ")":
                return recortarArray(S, 1);//ya no hay parametros por analizar y devuelve lo que falta analizar
            default:
                tempState+="q3-->";
                break;
        }
        if( esId(S[1]) && tempState.equals("q0-->q1-->") ){//si el estado anterior fue q1 y la siguiente palabra es una variable
            tempState+="q2";
            if(S[2].equals("&")){//evalua si el sigiente valor es una ,
                return param(recortarArray(S, 3));//vuelve a analizar lo que falta como parametro
            }else if(S[2].equals(")") ){
                return recortarArray(S, 2);//ya no hay parametros por analizar y devuelve lo que falta analizar
            }else{
                tempState+="-->q3-->";//si no es coma entonces es un valor no valido
            }
        }else{//si no es variable o el estado anterior no era q1
            tempState+="-->q3-->";
        }
        if(tempState.equals("q0-->q1-->q2")){
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
    
    private boolean esLetraMay(char l){
        return l>=65 && l<=90;
    }
    
    public String tipoDatos(String Q){//String,Int,Double
        String S[] = generaArray(Q);
        String tempState="q0",recorrido="";
        switch(S[0]){
            case "String":
                recorrido=recorrido+tempState;
                recorrido=recorrido+tempState;
                tempState="q1";
                recorrido=recorrido+tempState;
                break;
            case "Int":
                recorrido=recorrido+tempState;
                tempState="q1";
                recorrido=recorrido+tempState;
                break;
            case "Double":
                recorrido=recorrido+tempState;
                tempState="q1"; recorrido=recorrido+tempState;
                break;
            default:
                tempState="q6"; recorrido=recorrido+tempState;
                break;
        }
        if( tempState.equals("q1") && esId(S[1]) ){
            tempState="q2"; recorrido=recorrido+tempState;
            if(S.length==3){
                if(S[2].equals(";")){
                    tempState="q3"; recorrido=recorrido+tempState;
                }
            }else{
                if(S[2].equals("=")){
                    //EXPRECION
                    if( validaExp(recortarArray(S, 3))==true ){
                        tempState="q5"; recorrido=recorrido+tempState;
                    }else{
                        tempState="q6"; recorrido=recorrido+tempState;
                    }
                    
                }
            }
        }
return tempState+"#";
    }//FIN METODO TIPODATOS
    
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
    
     private String mostrar(String[] s){
        String temp="";
        for(int i=0; i<s.length; i++){
            if(!s[i].equals(""))
                temp=temp+s[i]+" | ";
        }
        return temp;
    }
     
    private boolean validaExp(String[] S){
        //Variable para saber que fue lo ultimo analizado
        boolean b=false;//false-> operador, true->id o numero
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
        return true;
    }
    
    private boolean esNum(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
     
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
}//FIN CLASE
 
 
