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
// Nodo para IF (Ej: Tuberia(a < b) POW)
public class IfNode extends ASTNode {
    String condition;
    List<ASTNode> body;

   public IfNode(String condition) {
        this.condition = condition;
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
