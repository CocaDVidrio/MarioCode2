/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AST;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gudja
 */
public class CompilerTest {
    public static void main(String[] args) {
        List<ASTNode> ast = new ArrayList<>();

        // Variables
        ast.add(new VariableDeclarationNode("Mario", "a"));
        ast.add(new VariableDeclarationNode("Mario", "b"));
        ast.add(new PowerUpCallNode("mushroom"));
        ast.add(new MethodCallNode("agacharse","3"));
        ast.add(new AssignmentNode("a", "3"));
        ast.add(new AssignmentNode("b", "5"));
        

        // IF (Tuberia)
        IfNode ifNode = new IfNode("a < b");
        ifNode.addStatement(new VariableDeclarationNode("Luigi", "c"));
        ifNode.addStatement(new AssignmentNode("c", "1.5"));
        ast.add(ifNode);
        ast.add(new VariableDeclarationNode("Mario", "i")); // Declarar i antes del for
        // FOR (Puente)
        ForNode forNode = new ForNode("i=0", "i<5", "i++");
        ast.add(forNode);

        // Switch (Castillo)
        SwitchNode switchNode = new SwitchNode("opcion");
        CaseNode case1 = new CaseNode("1");
        case1.addStatement(new AssignmentNode("opcion", "1"));

        CaseNode case2 = new CaseNode("2");
        case2.addStatement(new AssignmentNode("opcion", "2"));

        CaseNode case3 = new CaseNode("3");
        case3.addStatement(new AssignmentNode("opcion", "3"));

        switchNode.addCase(case1);
        switchNode.addCase(case2);
        switchNode.addCase(case3);
        ast.add(switchNode);

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
}

