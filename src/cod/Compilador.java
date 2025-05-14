package cod;

import AST.ASTNode;
import AST.ASTPrinter;
import AST.TACGenerator;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.List;
import java.util.Map;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java_cup.runtime.Symbol;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<Token> tokene;
    private ArrayList<Token> tokenI;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
        for (int i = 0; i < 80 * 300; i++){
            System.out.println("\b");
        }
    }

    private void init() {
        title = "MarioCode";
        setLocationRelativeTo(null);
       
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".mCod");

        addWindowListener(new WindowAdapter() {// Cerrar ventana
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });

        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            //colorAnalysis();
        });

        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });

        tokens = new ArrayList<>();
        tokene = new ArrayList<>();
        tokenI = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        
        
        
        Functions.setAutocompleterJTextComponent(new String[]{"if", "float", "double", "bool", "int", "string", "unit", "none", "in", "def", "return", "switch", "else", "while", "var", "vac"}, jtpCode, () -> {
            timerKeyReleased.restart();
        });

        for (int i = 0; i < 80 * 300; i++){
            System.out.println("\b");
        }
        
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        rootPanel.setBackground(Color.red);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/Bloque_1.png"))); // NOI18N
        btnAbrir.setText("Abrir");
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/Bloque_2.png"))); // NOI18N
        btnAbrir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/Bloque_2.png"))); // NOI18N
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/level_up_1.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/level_up (1).png"))); // NOI18N
        btnNuevo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/level_up (1).png"))); // NOI18N
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

//        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/GuardarIcono.png"))); // NOI18N
        btnGuardar.setText("Guardar");
//        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/CompilarPressed.png"))); // NOI18N
//        btnGuardar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/GuardarLayer.png"))); // NOI18N
//        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

//        btnGuardarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/GuardarComoIcono.png"))); // NOI18N
        btnGuardarC.setText("Guardar como");
//        btnGuardarC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//        btnGuardarC.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/GuardarComoPressed.png"))); // NOI18N
//        btnGuardarC.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/GuardarComoLayer.png"))); // NOI18N
//        btnGuardarC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAbrir)
                        .addComponent(btnNuevo))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/estrella_1.png"))); // NOI18N
        btnCompilar.setText("Compilar");
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/estrella (1).png"))); // NOI18N
        btnCompilar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/estrella (1).png"))); // NOI18N
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/Mushroom_1.png"))); // NOI18N
        btnEjecutar.setText("Tabla Símbolos");
        btnEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEjecutar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/Mushroom (1).png"))); // NOI18N
        btnEjecutar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Icon/Mushroom (1).png"))); // NOI18N
        btnEjecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCompilar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEjecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 142, Short.MAX_VALUE)
               // .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        //.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        getContentPane().add(rootPanel);

        pack();
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        directorio.New();
        clearFields();
    }

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {
        if (directorio.Open()) {
           // colorAnalysis();
            clearFields();
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        jtaOutputConsole.setText("");
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
        
        // abre nueva ventana para la tabla de tokens
        int sizeErrors = errors.size();
        if (sizeErrors < 0){
        JFrame frame = new JFrame("Tabla de tokens");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(jScrollPane3);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        //btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
               // CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
               // System.out.println(codeBlock);
               // ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
               // System.out.println(blocksOfCode);

            }
        }
        
                ts = new TS(tokens);
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        syntacticAnalysis();
        fillTableTokens();
        semanticAnalysis();
        errores();
        printConsole();
        printCI();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
         jtaOutputConsole.setText("");
          Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            tokene.clear();
            while (true) {
                Token token = lexer.yylex();
                System.out.println("Token: "+token);
                if (token == null) {
                    break;
                }
                tokens.add(token);
                if (token.getLexicalComp().equals("IdentificadorInvalido") || token.getLexicalComp().equals("NumInvalido") )  tokene.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
  
          String  ST = jtpCode.getText();   
          Sintax   s = new cod.Sintax(new cod.LexerCup(new StringReader(ST))); 
          
        try {
             s.parse();

              } catch (Exception ex) {
            Symbol sym = s.getS();
            jtaOutputConsole.append("Error de sintaxis. Linea: " + (sym.left + 1) + " Columna: " + (sym.right + 1) + ", Texto: \"" + sym.value + "\" \n");
            jtaOutputConsole.setForeground(Color.red);
        } 
    }
    
  private void semanticAnalysis() {
    jtaOutputConsole.append("\nIniciando análisis semántico...\n");

    Map<String, String> variableTypes = new HashMap<>();
    List<String> semanticErrors = new ArrayList<>();
    List<Simbolo> simbolos = new ArrayList<>();
    int cont = 0;
    int am = 0;

    // Análisis semántico para las declaraciones de variables
    for (int i = 0; i < tokens.size(); i++) {
        Token current = tokens.get(i);

        if (current.getLexeme().equals("POW") && tokens.get(i - 1).getLexeme().equals(")")) {
            am++;
        }
        if (current.getLexeme().equals("POW") && !tokens.get(i - 1).getLexeme().equals(")")) {
            am--;
        }

        if (current.getLexeme().equals("Mario") || current.getLexeme().equals("Luigi") || current.getLexeme().equals("Chomp")) {
            String tipoDato = current.getLexeme();
            int j = i + 1;
            if (j < tokens.size() && tokens.get(j).getLexicalComp().equals("Identificador")) {
    while (j < tokens.size()) {
        Token variable = tokens.get(j);

        if (!variable.getLexicalComp().equals("Identificador")) {
            semanticErrors.add("Error: Se esperaba un identificador después de '" + tipoDato + "' en la línea " + current.getLine());
            break;
        }

        if (variableTypes.containsKey(variable.getLexeme())) {
            semanticErrors.add("Error: La variable '" + variable.getLexeme() + "' en la línea " + variable.getLine() + " ya ha sido declarada.");
        } else {
            variableTypes.put(variable.getLexeme(), tipoDato);
            String ambito = (am >= 2) ? "Local" : "Global";
            Simbolo s = new Simbolo(variable.getLexeme(), tipoDato, "", "" + variable.getLine(), "" + variable.getLine(), ambito);
            simbolos.add(cont++, s);
        }

        j++;

        // Verificar si hay una coma o punto y coma
        if (j < tokens.size()) {
            Token next = tokens.get(j);
            if (next.getLexicalComp().equals("Coma")) {
                j++; // Continuar al siguiente identificador
                continue;
            } else if (next.getLexicalComp().equals("P_coma")) {
                i = j; // Finalizar la declaración
                break;
            } else {
                semanticErrors.add("Error: Se esperaba ',' o ';' después del identificador en la línea " + next.getLine());
                break;
            }
        } else {
            semanticErrors.add("Error: Declaración incompleta después de '" + variable.getLexeme() + "' en la línea " + variable.getLine());
            break;
                }
            }
        } else {
            semanticErrors.add("Error: Se esperaba un identificador después de '" + tipoDato + "' en la línea " + current.getLine());
        }

        }
    }

    // Verificar asignaciones
    for (int i = 0; i < tokens.size(); i++) {
        Token current = tokens.get(i);

        if (current.getLexicalComp().equals("Identificador") && i + 1 < tokens.size() && tokens.get(i + 1).getLexicalComp().equals("Igual")) {
            String varName = current.getLexeme();

            if (!variableTypes.containsKey(varName)) {
                semanticErrors.add("Error: La variable '" + varName + "' en la línea " + current.getLine() + " no ha sido declarada.");
            } else {
                String expectedType = variableTypes.get(varName);
                int j = i + 2;
                String valorAsignado = "";
                Stack<String> pilaOperandos = new Stack<>();
                Stack<String> pilaOperadores = new Stack<>();

                while (j < tokens.size() && !tokens.get(j).getLexicalComp().equals("P_coma")) {
                    Token token = tokens.get(j);

                    if (token.getLexicalComp().equals("Numero") || token.getLexicalComp().equals("Identificador")) {
                        pilaOperandos.push(token.getLexeme());
                    } else if (token.getLexicalComp().equals("Suma") || token.getLexicalComp().equals("Resta") ||
                               token.getLexicalComp().equals("Multiplicacion") || token.getLexicalComp().equals("Division")) {
                        while (!pilaOperadores.isEmpty() && precedencia(pilaOperadores.peek()) >= precedencia(token.getLexeme())) {
                            evaluarExpresion(pilaOperandos, pilaOperadores, simbolos, semanticErrors);
                        }
                        pilaOperadores.push(token.getLexeme());
                    }

                    j++;
                }

                while (!pilaOperadores.isEmpty()) {
                    evaluarExpresion(pilaOperandos, pilaOperadores, simbolos, semanticErrors);
                }

                if (!pilaOperandos.isEmpty()) {
                    valorAsignado = pilaOperandos.pop();
                    if(pilaOperandos.isEmpty() && semanticErrors.isEmpty()){
                        for (Simbolo sim : simbolos) {
                            if (sim.getNombre().equals(varName)) {
                                sim.lineaReferencia += "," + current.getLine();
                                sim.setValor(valorAsignado);
                            }
                        }
                    }

                }

            }
        } else if (current.getLexicalComp().equals("Identificador") && !variableTypes.containsKey(current.getLexeme())) {
            semanticErrors.add("Error: La variable '" + current.getLexeme() + "' en la línea " + current.getLine() + " no ha sido declarada.");
        }
    }

    // 4. Mostrar errores semánticos en la consola
    if (!semanticErrors.isEmpty()) {
        jtaOutputConsole.append("\nErrores semánticos detectados:\n");
        for (String error : semanticErrors) {
            jtaOutputConsole.append(error + "\n");
        }
        jtaOutputConsole.setForeground(Color.RED);
    } else {
        jtaOutputConsole.append("Análisis semántico exitoso. No se encontraron errores.\n");
        jtaOutputConsole.setForeground(Color.GREEN);
    }
    jtaOutputConsole.setForeground(Color.BLACK);

    System.out.println("ID" + " TIPO, VALOR, LINEA DE DECLARACION, LINEA DE REFERENCIA, AMBITO");

    for (Simbolo s : simbolos) {
        System.out.println(s.getNombre() + ", " + s.getTipo() + ", " + s.getValor() + ", " + s.getLineaDeclaracion() + ", " + s.getLineaReferencia() + "," + s.getAmbito());
    }
}
  
  
  private int precedencia(String operador) {
    if (operador.equals("+") || operador.equals("-")) return 1;
    if (operador.equals("*") || operador.equals("/")) return 2;
    return 0;
}

private boolean esCompatibleConjuntamente(String tipoEsperado, Set<String> tiposEncontrados) {
    for (String tipo : tiposEncontrados) {
        if (!esCompatible(tipoEsperado, tipo)) {
            return false;
        }
    }
    return true;
}

private void evaluarExpresion(Stack<String> operandos, Stack<String> operadores, List<Simbolo> simbolos, List<String> errores) {
    if (operandos.size() < 2 || operadores.isEmpty()) {
        errores.add("Error: Expresión aritmética inválida.");
        return;
    }

    String operando2 = operandos.pop();
    String operando1 = operandos.pop();
    String operador = operadores.pop();

    int valor1 = obtenerValor(operando1, simbolos);
    int valor2 = obtenerValor(operando2, simbolos);
    int resultado = 0;

    switch (operador) {
        case "+": resultado = valor1 + valor2; break;
        case "-": resultado = valor1 - valor2; break;
        case "*": resultado = valor1 * valor2; break;
        case "/": resultado = valor1 / valor2; break;
    }

    operandos.push(String.valueOf(resultado));
}

private int obtenerValor(String operando, List<Simbolo> simbolos) {
    for (Simbolo sim : simbolos) {
        if (sim.getNombre().equals(operando)) {
            return Integer.parseInt(sim.getValor());
        }
    }
    return Integer.parseInt(operando);
}

// Método auxiliar para obtener el tipo de un valor (números, cadenas, booleanos)
private String obtenerTipoValor(Token token) {
    if (token.getLexicalComp().equals("Numero")) return "Mario";
    if (token.getLexicalComp().equals("NumeroFlotante")) return "Luigi";
    if (token.getLexicalComp().equals("Cadena")) return "Chomp";
    if (token.getLexicalComp().equals("Op_booleano")) return "boolean";
    return "desconocido";
}

// Método auxiliar para verificar compatibilidad de tipos
private boolean esCompatible(String tipoVariable, String tipoValor) {
    if (tipoVariable.equals(tipoValor)) {
        return true;
    }
    if (tipoVariable.equals("Mario") && tipoValor.equals("int")) {
        return true;
    }
    if (tipoVariable.equals("Luigi") && tipoValor.equals("float")) {
        return true;
    }
    return false;
}

    private void printCI() {
        System.out.println("Seccion del de 3 direcciones");
        
        Parser par = new Parser(tokens);
         List<ASTNode> ast = par.parse();
    
    // Imprimir AST
    ASTPrinter printer = new ASTPrinter();
    for (ASTNode node : ast) {
        node.accept(printer);
    }
    // Generar TAC
    TACGenerator tacGenerator = new TACGenerator();
    for (ASTNode node : ast) {
        node.accept(tacGenerator);
    }
    // Imprimir TAC
    System.out.println("Código de Tres Direcciones (TAC):");
    for (String tac : tacGenerator.getTACCode()) {
        System.out.println(tac);
    }
}
    
    
    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
          
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.append("Compilación de Script terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.append("Compilación de Script terminada...\n");
        }
        jtaOutputConsole.setCaretPosition(0);
    }
    //funcion para imprimir los errores en la consola
    private void errores(){
        if(!tokene.isEmpty()){
            for(int i = 0; i < tokene.size();i++ ){
                jtaOutputConsole.append("Error en la linea: " + tokene.get(i).getLine() +","+ tokene.get(i).getColumn()+  
                        ", De tipo: [" + tokene.get(i).getLexicalComp() + "] \n");
            }     
        }
    }
    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
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
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());

            } catch (UnsupportedLookAndFeelException ex) {

                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }


    public TS ts;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    
}
