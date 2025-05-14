package AST;


/**
 *
 * @author gudja
 */

import java.util.List;

public class MethodCallNode extends ASTNode {
    private String methodName;
    private String argument;

    public MethodCallNode(String methodName, String argument) {
        this.methodName = methodName;
        this.argument = argument;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
