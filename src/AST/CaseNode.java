package AST;

import java.util.ArrayList;
import java.util.List;

public class CaseNode extends ASTNode {
    String value;
    List<ASTNode> body;

    public CaseNode(String value) {
        this.value = value;
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