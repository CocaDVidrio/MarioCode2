/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AST;

/**
 *
 * @author gudja
 */
 interface ASTVisitor {
    void visit(VariableDeclarationNode node);
    void visit(AssignmentNode node);
    void visit(MethodCallNode node);
    void visit(PowerUpCallNode node);
    void visit(IfNode node);
    void visit(ForNode node);
    void visit(SwitchNode node);
    void visit(CaseNode node);
}
