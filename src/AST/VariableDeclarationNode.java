package AST;

public class VariableDeclarationNode extends ASTNode {
    String type;
    String name;

    public VariableDeclarationNode(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}