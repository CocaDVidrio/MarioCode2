package cod;

import static cod.Tokens.*;
import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column

L=[a-zA-Z_]+
D=[0-9]+
espacio=[ \t\r\n]+
Cadena = \"([^\"\\]|\\.)*\"
C = [\-|*.,;:_#$]
/* COMENTARIOS */
ComentarioDeUnaLinea = "#" {EntradaDeCaracter}* {TerminadorDeLinea}?
Comentario = {ComentarioDeUnaLinea}

/* IDENTIFICADOR */
Identificador = {L}({L}|{D})*
IdentificadorInvalido = {D}+{L}+
Numero = [0-9]+
NumeroFlotante = {Numero}"."{D}+

NumeroMalFormado = [0-9]+\.[^0-9]+ | \.[0-9]+[a-zA-Z_]+ | [0-9]+\.[0-9]*\.[0-9]+

%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}
("#"(.)*) {/*Ignore*/}

/* Valores en cadena */
{Cadena} {  
    return token(yytext(), "Cadena", yyline, yycolumn); 
}

/* Tipo de dato entero */
( Mario ) {return token(yytext(), "Mario", yyline, yycolumn);}

/* Tipo de dato flotante */
( Luigi ) {return token(yytext(), "Luigi", yyline, yycolumn);}

/* Tipo de dato String */
( Chomp ) {return token(yytext(), "Chomp", yyline, yycolumn);}

/* Palabra reservada If */
( Tuberia ) {return token(yytext(), "Tuberia", yyline, yycolumn);}

/* Palabra reservada For */
( Puente ) {return token(yytext(), "Puente", yyline, yycolumn);}

/* Métodos (acciones) */
( mover | saltar | retroceder | agacharse) {return token(yytext(), "Metodo", yyline, yycolumn);}

/* Estilos */
( mushroom | ffuego | tanuki | frog | capa) {return token(yytext(), "PowerUp", yyline, yycolumn);}

/* Operador Igual */
( "=" ) {return token(yytext(), "Igual", yyline, yycolumn);}

/* Operador Suma */
( "+" ) {return token(yytext(), "Suma", yyline, yycolumn);}

/* Operador Resta */
( "-" ) {return token(yytext(), "Resta", yyline, yycolumn);}

/* Operador Multiplicación */
( "*" ) {return token(yytext(), "Multiplicacion", yyline, yycolumn);}

/* Operador División */
( "/" ) {return token(yytext(), "Division", yyline, yycolumn);}

/* Operadores lógicos */
( "&&" | "||" | "!" | "&" | "|" ) {return token(yytext(), "Op_logico", yyline, yycolumn);}

/* Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return token(yytext(), "Op_relacional", yyline, yycolumn);}

/* Operadores Atribución */
( "+=" | "-="  | "*=" | "/=" | "%=" ) {return token(yytext(), "Op_atribucion", yyline, yycolumn);}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return token(yytext(), "Op_Incremento", yyline, yycolumn);}

/* Operadores Booleanos */
(true | false) {return token(yytext(), "Op_booleano", yyline, yycolumn);}

/* Paréntesis de apertura */
( "(" ) {return token(yytext(), "Parentesis_a", yyline, yycolumn);} 

/* Paréntesis de cierre */
( ")" ) {return token(yytext(), "Parentesis_c", yyline, yycolumn);}

/* Llave de apertura */
( "POW" ) {return token(yytext(), "Llave_a", yyline, yycolumn);}

/* Llave de cierre */
( "END" ) {return token(yytext(), "Llave_c", yyline, yycolumn);}

/* Corchete de apertura */
( "[" ) {return token(yytext(), "Corchete_a", yyline, yycolumn);}

/* Corchete de cierre */
( "]" ) {return token(yytext(), "Corchete_c", yyline, yycolumn);}

/* Marcador de inicio de algoritmo */
( "nivel" ) {return token(yytext(), "Main", yyline, yycolumn);}

( "," ) {return token(yytext(), "Coma", yyline, yycolumn);}

/* Punto y coma */
( ";" ) {return token(yytext(), "P_coma", yyline, yycolumn);}

/* Switch */
( Castillo ) {return token(yytext(), "Castillo", yyline, yycolumn);}

/* Case */
( Toad ) {return token(yytext(), "Toad", yyline, yycolumn);}

/* Default */
( Peach ) {return token(yytext(), "Peach", yyline, yycolumn);}

/* Identificador */
{Identificador} {return token(yytext(), "Identificador", yyline, yycolumn);}

/* Número */
{NumeroFlotante} { return token(yytext(), "NumeroFlotante", yyline, yycolumn); }

{Numero} {return token(yytext(), "Numero", yyline, yycolumn);}

{IdentificadorInvalido} { return token(yytext(), "IdentificadorInvalido", yyline, yycolumn); }

/* Error de análisis */
 . {return token(yytext(), "ERROR", yyline, yycolumn);}
