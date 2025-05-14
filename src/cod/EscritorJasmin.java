/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cod;

/**
 *
 * @author gudja
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class EscritorJasmin {
     public static void main(String[] args) {
        String archivo = "HelloWorld.j";

        try (PrintWriter out = new PrintWriter(new FileWriter(archivo))) {
            out.println(".class public HelloWorld");
            out.println(".super java/lang/Object");
            out.println();
            out.println(".method public static main([Ljava/lang/String;)V");
            out.println("    .limit stack 2");
            out.println("    .limit locals 1");
            out.println();
            out.println("    getstatic java/lang/System/out Ljava/io/PrintStream;");
            out.println("    ldc \"Hola desde bytecode Jasmin!\"");
            out.println("    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
            out.println();
            out.println("    return");
            out.println(".end method");

            System.out.println("Archivo HelloWorld.j generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
