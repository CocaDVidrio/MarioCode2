/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cod;

/**
 *
 * @author P3DR0
 */
import AST.*;
import compilerTools.Token;
import java.util.*;

public class Parser {
    private ArrayList<Token> tokens;
    private List<ASTNode> ast;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.ast = new ArrayList<>();
    }

    public List<ASTNode> parse() {
        parseBloque(ast, tokens);
        return ast;
    }

    private void parseBloque(List<ASTNode> destino, List<Token> bloque) {
        for (int i = 0; i < bloque.size(); i++) {
            Token token = bloque.get(i);

            if (token.getLexicalComp().equals("Tuberia")) {
                i = tuberiaanidada(destino, bloque, i);
                continue;
            }

            if (token.getLexeme().equals("Puente")) {
                i = puenteanidado(destino, bloque, i);
                continue;
            }

            if (token.getLexeme().equals("switch")) {
                i = switchanidado(destino, bloque, i);
                continue;
            }
        //Métodos
            if (token.getLexeme().equals("mover")) {
    if (i + 3 < bloque.size() &&
        bloque.get(i + 1).getLexeme().equals("(") &&
        bloque.get(i + 3).getLexeme().equals(")")) {

        String param = bloque.get(i + 2).getLexeme();
        destino.add(new MethodCallNode("mover", param));
        i += 4; // Saltar hasta después de ")"
        continue;
    }
}     
            if (token.getLexeme().equals("saltar")) {
    if (i + 3 < bloque.size() &&
        bloque.get(i + 1).getLexeme().equals("(") &&
        bloque.get(i + 3).getLexeme().equals(")")) {

        String param = bloque.get(i + 2).getLexeme();
        destino.add(new MethodCallNode("saltar", param));
        i += 4; // Saltar hasta después de ")"
        continue;
    }
}     
            
            if (token.getLexeme().equals("retroceder")) {
    if (i + 3 < bloque.size() &&
        bloque.get(i + 1).getLexeme().equals("(") &&
        bloque.get(i + 3).getLexeme().equals(")")) {

        String param = bloque.get(i + 2).getLexeme();
        destino.add(new MethodCallNode("retroceder", param));
        i += 4; // Saltar hasta después de ")"
        continue;
    }
}     
            
        if (token.getLexeme().equals("agacharse")) {
    if (i + 3 < bloque.size() &&
        bloque.get(i + 1).getLexeme().equals("(") &&
        bloque.get(i + 3).getLexeme().equals(")")) {

        String param = bloque.get(i + 2).getLexeme();
        destino.add(new MethodCallNode("agacharse", param));
        i += 4; // Saltar hasta después de ")"
        continue;
    }
}     
        
        // PowerUps
        if (token.getLexeme().equals("mushroom")) {
            destino.add(new PowerUpCallNode("mushroom"));
            continue;
        }
        
        if (token.getLexeme().equals("ffuego")) {
            destino.add(new PowerUpCallNode("ffuego"));
            continue;
        }
         
        if (token.getLexeme().equals("tanuki")) {
            destino.add(new PowerUpCallNode("tanuki"));
            continue;
        }
        
        if (token.getLexeme().equals("frog")) {
            destino.add(new PowerUpCallNode("frog"));
            continue;
        }
        
        if (token.getLexeme().equals("capa")) {
            destino.add(new PowerUpCallNode("capa"));
            continue;
        }
            if (token.getLexeme().equals("Mario") || token.getLexeme().equals("Luigi") || token.getLexeme().equals("Chomp")) {
                if (i + 1 < bloque.size()) {
                    destino.add(new VariableDeclarationNode(token.getLexeme(), bloque.get(i + 1).getLexeme()));
                    i++;
                }
            } else if (token.getLexicalComp().equals("Identificador") && i + 2 < bloque.size() && bloque.get(i + 1).getLexicalComp().equals("Igual")) {
                StringBuilder expr = new StringBuilder();
                int j = i + 2;
                while (j < bloque.size() && !bloque.get(j).getLexicalComp().equals("P_coma")) {
                    expr.append(bloque.get(j).getLexeme()).append(" ");
                    j++;
                }
                destino.add(new AssignmentNode(token.getLexeme(), expr.toString().trim()));
                i = j;
            }
        }
    }

    private int tuberiaanidada(List<ASTNode> destino, List<Token> tokens, int i) {
        StringBuilder condicion = new StringBuilder();
        int t = 2;

        while (i + t < tokens.size() && !tokens.get(i + t).getLexeme().equals(")")) {
            condicion.append(tokens.get(i + t).getLexeme());
            t++;
        }
        t++; // Salta ")"

        IfNode ifNode = new IfNode(condicion.toString());
        List<Token> cuerpo = new ArrayList<>();
        int j = i + t;
        while (j < tokens.size() && !tokens.get(j).getLexicalComp().equals("Llave_a")) {
            cuerpo.add(tokens.get(j));
            j++;
        }

        parseBloque(ifNode.getbody(), cuerpo);
        destino.add(ifNode);
        return j;
    }

    private int puenteanidado(List<ASTNode> destino, List<Token> tokens, int i) {
        String init = "", cond = "", inc = "";
        int j = i + 2;

        while (!tokens.get(j).getLexeme().equals(";")) init += tokens.get(j++).getLexeme();
        j++;
        while (!tokens.get(j).getLexeme().equals(";")) cond += tokens.get(j++).getLexeme();
        j++;
        while (!tokens.get(j).getLexeme().equals(")")) inc += tokens.get(j++).getLexeme();
        j++;

        ForNode forNode = new ForNode(init, cond, inc);
        List<Token> cuerpo = new ArrayList<>();

        while (j < tokens.size() && !tokens.get(j).getLexicalComp().equals("Llave_a")) {
            cuerpo.add(tokens.get(j));
            j++;
        }

        parseBloque(forNode.getbody(), cuerpo);
        destino.add(forNode);
        return j;
    }

    private int switchanidado(List<ASTNode> destino, List<Token> tokens, int i) {
        String variable = tokens.get(i + 2).getLexeme();
        int j = i + 4;

        SwitchNode switchNode = new SwitchNode(variable);
        while (j < tokens.size() && !tokens.get(j).getLexicalComp().equals("Llave_a")) {
            if (tokens.get(j).getLexeme().equals("case")) {
                String value = tokens.get(j + 1).getLexeme();
                CaseNode caseNode = new CaseNode(value);
                j += 3;

                List<Token> caseTokens = new ArrayList<>();
                while (j < tokens.size() && !tokens.get(j).getLexeme().equals("case") && !tokens.get(j).getLexicalComp().equals("Llave_a")) {
                    caseTokens.add(tokens.get(j));
                    j++;
                }

                parseBloque(caseNode.getbody(), caseTokens);
                switchNode.addCase(caseNode);
            } else {
                j++;
            }
        }

        destino.add(switchNode);
        return j;
    }
} // Fin de la clase Parser
