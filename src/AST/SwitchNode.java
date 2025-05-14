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
public class SwitchNode extends ASTNode {
    String variable;
    List<CaseNode> cases;

   public SwitchNode(String variable) {
        this.variable = variable;
        this.cases = new ArrayList<>();
    }

   public void addCase(CaseNode caseNode) {
        cases.add(caseNode);
    }

    @Override
   public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}