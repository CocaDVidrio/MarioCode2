/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cod;

/**
 *
 * @author gudja
 */
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

            Map<String, Integer> variableSlots = new HashMap<>();
             int[] nextSlot = {0};

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.matches("[a-zA-Z][a-zA-Z0-9_]*\\s*=\\s*\\d+(\\.\\d+)?")) {
                    String[] parts = line.split("=");
                    String var = parts[0].trim();
                    String val = parts[1].trim();
                    Integer slot = variableSlots.get(var);
                    if (slot == null) {
                        slot = nextSlot[0]++;
                        variableSlots.put(var, slot);
                    }

                    if (val.contains(".")) {
                        out.println("    ldc2_w " + val);
                        out.println("    dstore " + slot);
                    } else {
                        out.println("    ldc " + val);
                        out.println("    istore " + slot);
                    }

                } else if (line.startsWith("call ")) {
                    if (line.contains(",")) {
                        String[] parts = line.substring(5).split(",");
                        String method = parts[0].trim();
                        String arg = parts[1].trim();
                        out.println("    ldc " + arg);
                        out.println("    invokestatic Game/" + method + "(I)V");
                    } else {
                        String method = line.substring(5).trim();
                        out.println("    invokestatic Game/" + method + "()V");
                    }

                } else if (line.matches("ifFalse \\w+<\\w+ goto \\w+")) {
                    String[] parts = line.split(" ");
                    String[] cond = parts[1].split("<");
                    String left = cond[0];
                    String right = cond[1];
                    String label = parts[3];

                    out.println("    iload " + variableSlots.get(left));
                    out.println("    iload " + variableSlots.get(right));
                    out.println("    if_icmpge " + label);

                } else if (line.matches("ifFalse \\w+<\\d+ goto \\w+")) {
                    String[] parts = line.split(" ");
                    String[] cond = parts[1].split("<");
                    String left = cond[0];
                    String right = cond[1];
                    String label = parts[3];

                    out.println("    iload " + variableSlots.get(left));
                    out.println("    ldc " + right);
                    out.println("    if_icmpge " + label);

                } else if (line.matches("\\w+\\+\\+;?")) {
                    String var = line.replace("++", "").trim();
                    Integer slot = variableSlots.get(var);
                    if (slot == null) {
                    slot = nextSlot[0]++;
                    variableSlots.put(var, slot);
                        }
                    out.println("    iinc " + slot + " 1");

                } else if (line.matches("goto \\w+")) {
                    out.println("    " + line);

                } else if (line.matches("\\w+:")) {
                    out.println(line);

                } else {
                    System.out.println("Instrucción no reconocida: " + line);
                }
            }

            out.println("    return");
            out.println(".end method");
        }
    }
}
