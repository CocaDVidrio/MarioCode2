package cod;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ExecuteJFlex {

/**
 *
 * @author Charly Ponce
     * @param args
 */
    public static void main(String[] args) throws Exception {
        String ruta1 = "C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\Lexer.flex";
        String ruta2 = "C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
    }
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        jflex.Main.generate(new String[]{ruta1});
        archivo = new File(ruta2);
        jflex.Main.generate(new String[]{ruta2});
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\sym.java"), 
                Paths.get("C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\sym.java")
        );
        Path rutaSin = Paths.get("C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\Sintax.java"), 
                Paths.get("C:\\Users\\P3DR0\\OneDrive\\Documentos\\Compilador\\Compilador\\Compilador\\src\\cod\\Sintax.java")
        );
    }
}