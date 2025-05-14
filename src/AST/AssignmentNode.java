package AST;

public class AssignmentNode extends ASTNode {
    String variable;
    String value;

    public AssignmentNode(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
