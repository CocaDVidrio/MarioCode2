package cod;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class GeneradorJasmin {

  public static void generarArchivo(String inputPath, String outputPath) throws IOException {
    List<String> lines = Files.readAllLines(new File(inputPath).toPath());

    try (PrintWriter out = new PrintWriter(new FileWriter(outputPath))) {
        out.println(".class public Programa");
        out.println(".super java/lang/Object");
        out.println();
        out.println(".method public static main([Ljava/lang/String;)V");
        out.println("    .limit stack 10");
        out.println("    .limit locals 10");
        out.println();
        out.println("Label0:");

        Map<String, Integer> variableSlots = new HashMap<>();
        Map<String, String> variableTypes = new HashMap<>();
        int[] nextSlot = {1};

        List<String> varDeclarations = new ArrayList<>();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Asignaciones: a = 3  o  b = 1.5
            if (line.matches("[a-zA-Z][a-zA-Z0-9_]*\\s*=\\s*\\d+(\\.\\d+)?")) {
                String[] parts = line.split("=");
                String var = parts[0].trim();
                String val = parts[1].trim();
                Integer slot = variableSlots.get(var);
                if (slot == null) {
                    slot = nextSlot[0]++;
                    variableSlots.put(var, slot);
                    String type = val.contains(".") ? "D" : "I";
                    variableTypes.put(var, type);
                    varDeclarations.add("    .var " + slot + " is " + var + " " + type + " from Label0 to Label1");
                }

                if (val.contains(".")) {
                    out.println("    ldc2_w " + val);
                    out.println("    dstore " + slot);
                } else {
                    out.println("    ldc " + val);
                    out.println("    istore " + slot);
                }

            // call agacharse, 3
            } else if (line.startsWith("call ")) {
                if (line.contains(",")) {
                    String[] parts = line.substring(5).split(",");
                    String method = parts[0].trim();
                    String arg = parts[1].trim().replaceAll("[^\\d.\\-]", "");
                    out.println("    ldc " + arg);
                    out.println("    invokestatic Programa/" + method + "(I)V");
                } else {
                    String method = line.substring(5).trim();
                    out.println("    invokestatic Programa/" + method + "()V");
                }

            // ifFalse a<b goto L0
            } else if (line.matches("ifFalse \\w+<\\w+ goto \\w+") || line.matches("ifFalse \\w+<\\d+ goto \\w+")) {
                String[] parts = line.split(" ");
                String[] cond = parts[1].split("<");
                String left = cond[0].trim();
                String right = cond[1].trim();
                String label = parts[3];

                Integer leftSlot = variableSlots.get(left);
                if (leftSlot == null) {
                    leftSlot = nextSlot[0]++;
                    variableSlots.put(left, leftSlot);
                    variableTypes.put(left, "I");
                    varDeclarations.add("    .var " + leftSlot + " is " + left + " I from Label0 to Label1");
                }
                out.println("    iload " + leftSlot);

                if (right.matches("\\d+")) {
                    out.println("    ldc " + right);
                } else {
                    Integer rightSlot = variableSlots.get(right);
                    if (rightSlot == null) {
                        rightSlot = nextSlot[0]++;
                        variableSlots.put(right, rightSlot);
                        variableTypes.put(right, "I");
                        varDeclarations.add("    .var " + rightSlot + " is " + right + " I from Label0 to Label1");
                    }
                    out.println("    iload " + rightSlot);
                }

                out.println("    if_icmpge " + label);

            // i++;
            } else if (line.matches("\\w+\\+\\+;?")) {
                String var = line.replace("++", "").replace(";", "").trim();
                Integer slot = variableSlots.get(var);
                if (slot == null) {
                    slot = nextSlot[0]++;
                    variableSlots.put(var, slot);
                    variableTypes.put(var, "I");
                    varDeclarations.add("    .var " + slot + " is " + var + " I from Label0 to Label1");
                }
                out.println("    iinc " + slot + " 1");

            // goto L1
            } else if (line.matches("goto \\w+")) {
                out.println("    " + line);

            // L1:
            } else if (line.matches("\\w+:")) {
                out.println(line);

            } else {
                System.out.println("Instrucción no reconocida: " + line);
            }
        }

        out.println("    return");
        out.println("Label1:");
        for (String decl : varDeclarations) {
            out.println(decl);
        }
        out.println(".end method");
    }
}

}
