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
// Nodo para FOR (Ej: Puente(i=0; i<5; i++) POW)
public class ForNode extends ASTNode {
    String initialization;
    String condition;
    String increment;
    List<ASTNode> body;

   public ForNode(String initialization, String condition, String increment) {
        this.initialization = initialization;
        this.condition = condition;
        this.increment = increment;
        this.body = new ArrayList<>();
    }

   public void addStatement(ASTNode stmt) {
        body.add(stmt);
    }

   public List<ASTNode> getbody(){
       return body;
   }
   
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}