package AST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TACGenerator implements ASTVisitor {

    private List<String> tacCode = new ArrayList<>();
    private int tempVarCount = 0; // Contador de variables temporales

    private String newTemp() {
        return "t" + (tempVarCount++);
    }

    public List<String> getTACCode() {
        return tacCode;
    }

    @Override
    public void visit(VariableDeclarationNode node) {
        // No genera código, solo se usa en el análisis de tipos
    }

    @Override
    public void visit(AssignmentNode node) {
        String expression = node.value;
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();
        List<String> tokens = tokenizeExpression(expression);

        for (String token : tokens) {
            if (isNumeric(token) || isVariable(token)) {
                operands.push(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    generateTACInstruction(operands, operators);
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            generateTACInstruction(operands, operators);
        }

        if (!operands.isEmpty()) {
            tacCode.add(node.variable + " = " + operands.pop());
        }
    }

    private List<String> tokenizeExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        String[] parts = expression.split("(?<=[-+*/])|(?=[-+*/])");
        for (String part : parts) {
            tokens.add(part.trim());
        }
        return tokens;
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isVariable(String str) {
        return str.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    private boolean isOperator(String str) {
        return str.matches("[+\\-*/]");
    }

    private int precedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) return 1;
        if (operator.equals("*") || operator.equals("/")) return 2;
        return 0;
    }

    private void generateTACInstruction(Stack<String> operands, Stack<String> operators) {
        String operand2 = operands.pop();
        String operand1 = operands.pop();
        String operator = operators.pop();
        String tempVar = newTemp();
        tacCode.add(tempVar + " = " + operand1 + " " + operator + " " + operand2);
        operands.push(tempVar);
    }

    @Override
    public void visit(IfNode node) {
        String labelFalse = "L" + tempVarCount++;
        tacCode.add("ifFalse " + node.condition + " goto " + labelFalse);
        for (ASTNode stmt : node.body) {
            stmt.accept(this);
        }
        tacCode.add(labelFalse + ":");
    }

    @Override
    public void visit(ForNode node) {
        String labelStart = "L" + tempVarCount++;
        String labelEnd = "L" + tempVarCount++;

        tacCode.add(node.initialization);
        tacCode.add(labelStart + ":");
        tacCode.add("ifFalse " + node.condition + " goto " + labelEnd);
        for (ASTNode stmt : node.body) {
            stmt.accept(this);
        }
        tacCode.add(node.increment);
        tacCode.add("goto " + labelStart);
        tacCode.add(labelEnd + ":");
    }

    @Override
    public void visit(SwitchNode node) {
        String labelEnd = "L" + tempVarCount++;
        for (CaseNode caseNode : node.cases) {
            String caseLabel = "L" + tempVarCount++;
            tacCode.add("if " + node.variable + " == " + caseNode.value + " goto " + caseLabel);
            caseNode.accept(this);
            tacCode.add(caseLabel + ":");
        }
        tacCode.add(labelEnd + ":");
    }

    @Override
    public void visit(CaseNode node) {
        for (ASTNode stmt : node.body) {
            stmt.accept(this);
        }
    }

    
        @Override
    public void visit(MethodCallNode node) {
       String args = String.join(", ", node.getArgument());
    tacCode.add("call " + node.getMethodName() + (args.isEmpty() ? "" : ", " + args));
    }

    @Override
    public void visit(PowerUpCallNode node) {
        tacCode.add("call " + node.getPowerup());
    }
}