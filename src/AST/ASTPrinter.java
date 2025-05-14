package AST;

public class ASTPrinter implements ASTVisitor {
    private int indent = 0;

    private void printIndent() {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }

    @Override
    public void visit(VariableDeclarationNode node) {
        printIndent();
        System.out.println("Declarar " + node.type + " " + node.name + ";");
    }

    @Override
    public void visit(AssignmentNode node) {
        printIndent();
        System.out.println("Asignación: " + node.variable + " = " + node.value);
    }
    
        @Override
public void visit(MethodCallNode node) {
    printIndent();
    System.out.println("Llamada a metodo: " + node.getMethodName()+"("+node.getArgument()+")");
}

@Override
public void visit(PowerUpCallNode node) {
    printIndent();
    System.out.println("Llamada a powerup: " + node.getPowerup() + ";");
}

    @Override
    public void visit(IfNode node) {
        System.out.println("IF (" + node.condition + ") {");
        for (ASTNode stmt : node.body) stmt.accept(this);
        System.out.println("}");
    }

    @Override
    public void visit(ForNode node) {
        System.out.println("FOR (" + node.initialization + "; " + node.condition + "; " + node.increment + ") {");
        for (ASTNode stmt : node.body) stmt.accept(this);
        System.out.println("}");
    }

    @Override
    public void visit(SwitchNode node) {
        System.out.println("SWITCH (" + node.variable + ") {");
        for (CaseNode caseNode : node.cases) caseNode.accept(this);
        System.out.println("}");
    }

    @Override
    public void visit(CaseNode node) {
        System.out.println("CASE " + node.value + ": {");
        for (ASTNode stmt : node.body) stmt.accept(this);
        System.out.println("}");
    }


}