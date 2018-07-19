
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author basix
 */
public class Interface extends javax.swing.JFrame {
  
    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtExp.addCaretListener( new CaretListener() {
            public void caretUpdate( CaretEvent e ) {
                int pos = e.getDot();
                try {
                   int row = txtExp.getLineOfOffset( pos ) + 1;
                   int col = pos - txtExp.getLineStartOffset( row - 1 ) + 1;
                   lblLinea.setText("Línea: " + row + " Columna: " + col );      
                }catch( BadLocationException exc ){
                   System.out.println(exc);  
                }
                }
        });
    }
      private javax.swing.table.DefaultTableModel model;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlPrincipal = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSimbolos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExp = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        ErrCon = new javax.swing.JTextArea();
        lblLinea = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRecorridos = new javax.swing.JTextArea();
        pnlAutomatas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Compilar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 171, 40));

        tblSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema", "Reservada", "Patron", "Linea", "Valor", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSimbolos);

        pnlPrincipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1100, 201));

        txtExp.setColumns(20);
        txtExp.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtExp.setRows(5);
        txtExp.setText("task MET (Int a) {\nBrazo.Elevar(Encendido);\nInt b=1+a;\n}");
        jScrollPane1.setViewportView(txtExp);

        pnlPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 658, 267));

        ErrCon.setEditable(false);
        ErrCon.setColumns(20);
        ErrCon.setFont(new java.awt.Font("Monospaced", 3, 13)); // NOI18N
        ErrCon.setForeground(new java.awt.Color(255, 0, 0));
        ErrCon.setRows(5);
        jScrollPane3.setViewportView(ErrCon);

        pnlPrincipal.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 430, 267));

        lblLinea.setText("-");
        pnlPrincipal.add(lblLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 300, 20));

        txtRecorridos.setEditable(false);
        txtRecorridos.setColumns(20);
        txtRecorridos.setRows(5);
        jScrollPane4.setViewportView(txtRecorridos);

        pnlPrincipal.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 660, 140));

        jTabbedPane1.addTab("Crane", pnlPrincipal);

        pnlAutomatas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Autómatas/36888015_1940402215991074_6451147642889043968_n.png"))); // NOI18N
        pnlAutomatas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Autómatas/36943751_1940411155990180_5741661917953392640_n.png"))); // NOI18N
        pnlAutomatas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, -1));

        jTabbedPane1.addTab("Base,Deslizar y Brazo", pnlAutomatas);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Autómatas/36854876_1940402239324405_7208416846087716864_n.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPane1.addTab("tab3", jPanel1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AutomatasSintactico U=new AutomatasSintactico();
        Analisis_Lexico A = new Analisis_Lexico();
         Sintáctico Syntax=new Sintáctico();
        
        String Err="",Recorrido="";
        model = (javax.swing.table.DefaultTableModel)tblSimbolos.getModel();
        model.setRowCount(0);
        String Consola="";
        String lineas[]=txtExp.getText().split("\n");
       
        for(int i=0;i<lineas.length;i++){
            if(!lineas[i].equals("")){
            String ts[]=A.Compile(lineas[i],(i+1)).split("#");
          
                for(int Q=0;Q<ts.length;Q++){
                    String Row[]=ts[Q].split(",");
                    if(Row[0].equals("Desconocido") || Row[0].equals("Elemento Desconocido")){
                       Err=Err+"Error Léxico;; Simbolo Desconocido: "+Row[1]+" Encontrado en la línea "+Row[4]+"\n";
                       System.out.print(Row);
                    }
                    else{
                    model.addRow(Row);
                    }
                }
            }   
        }
        if(!(Err.equals(""))){
            ErrCon.setText(Err);
        } 
        else{ErrCon.setText("Build Succesful");}
       
        if(Err.equals("")){
           
            String Error=Syntax.cLlavesPar(txtExp.getText());
            if(Error!=null){Err=Err+Error+"#";}

            
            String comandos="";
            for(int q=0;q<lineas.length;q++){
                if(!lineas[q].equals("")){ 
                    comandos=comandos+lineas[q]+"#";}
            }
            String Ins[]=comandos.split("#");
            for(int w=0;w<Ins.length;w++){
                if(!Ins[w].equals("")){ 
                    String Palabras[]=Ins[w].split(" "); 
                    if(Palabras[0].equals("task")){ 
                        U.caja=txtExp.getText();
                        Recorrido=Recorrido+U.Recorrido(Ins[w]);
                        if(Syntax.validaTask(Ins[w])==false){
                            Err=Err+"Error en la declaración del Método#";
                        }
                    }else{
                        Err=Err+Syntax.MoverInstruccion(Ins[w]);
                        Recorrido=Recorrido+U.Recorrido(Ins[w]);


                    } 
                }
            }
            if(!Err.equals("")){
                String Errores[]=Err.split("#");
                Err="";
                for(int l=0;l<Errores.length;l++){
                   Err=Err+Errores[l]+"\n";
                }
                ErrCon.setText(Err);
            }else{
                ErrCon.setText("Build Succesful");
            }
        }
        String rec[]=Recorrido.split("#");
        String cad="";
        for(int i=0;i<rec.length;i++){
            cad=cad+rec[i]+"\n";
        }
        txtRecorridos.setText(cad);
        
        Semantico s = new Semantico(tblSimbolos.getModel());
        Err = s.unicidad();
        if( Err.equals("") ){
            tblSimbolos.setModel(s.m);
            ErrCon.setText("Build Succesful");
        }else{
            ErrCon.setText(err(Err));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private String err(String err){
        String[] S = err.split("#");
        String t="";
        for( int i=0; i<S.length; i++ ){
            t="Error semántico;; "+S[i]+"\n";
        }
        return t;
    }
    
    private String mostrar(String[] s){
        String temp="";
        for(int i=0; i<s.length; i++){
            if(!s[i].equals(""))
                temp=temp+s[i]+" | ";
        }
        return temp;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ErrCon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JPanel pnlAutomatas;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblSimbolos;
    private javax.swing.JTextArea txtExp;
    private javax.swing.JTextArea txtRecorridos;
    // End of variables declaration//GEN-END:variables
}
