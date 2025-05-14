package cod;

import java_cup.runtime.Symbol;

%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+

espacio=[ \t\r\n]+
Cadena = \"([^\"\\]|\\.)*\"
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, (int)yyline, (int)yychar, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, (int)yyline, (int)yychar);
    }
%}
%%

/* Valores en cadena (String) */
{Cadena} {  
    return symbol(sym.Cadena, yytext()); 
}

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Tipo de dato entero */
( Mario ) {return symbol(sym.Mario, yytext());}

/* Tipo de dato flotante */
( Luigi ) {return symbol(sym.Luigi, yytext());}

/* Tipo de dato String */
( Chomp ) {return symbol(sym.Chomp, yytext());}

/* Palabra reservada Tuberia */
( Tuberia ) {return symbol(sym.Tuberia, yytext());}

/* Palabra reservada For */
( Puente ) {return symbol(sym.Puente, yytext());}

/* Métodos (acciones) */
( mover | saltar | retroceder | agacharse) {return symbol(sym.Metodo, yytext());}

/* Operador Igual */
( "=" ) {return symbol(sym.Igual, yytext());}

/* Operador Suma */
( "+" ) {return symbol(sym.Suma, yytext());}

/* Operador Resta */
( "-" ) {return symbol(sym.Resta, yytext());}

/* Operador Multiplicación */
( "*" ) {return symbol(sym.Multiplicacion, yytext());}

/* Operador División */
( "/" ) {return symbol(sym.Division, yytext());}

/* Operadores lógicos */
( "&&" | "||" | "!" | "&" | "|" ) {return symbol(sym.Op_logico, yytext());}

/* Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return symbol(sym.Op_relacional, yytext());}

/* Operadores Atribución */
( "+=" | "-="  | "*=" | "/=" | "%=" | "=" ) {return symbol(sym.Op_atribucion, yytext());}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return symbol(sym.Op_incremento, yytext());}

/* Operadores Booleanos */
( true | false ) {return symbol(sym.Op_booleano, yytext());}

/* Paréntesis de apertura */
( "(" ) {return symbol(sym.Parentesis_a, yytext());}

/* Paréntesis de cierre */
( ")" ) {return symbol(sym.Parentesis_c, yytext());}

/* Llave de apertura */
( "POW" ) {return symbol(sym.Llave_a, yytext());}

/* Llave de cierre */
( "END" ) {return symbol(sym.Llave_c, yytext());}

/* Corchete de apertura */
( "[" ) {return symbol(sym.Corchete_a, yytext());}

/* Corchete de cierre */
( "]" ) {return symbol(sym.Corchete_c, yytext());}

/* Marcador de inicio de algoritmo */
( "nivel" ) {return symbol(sym.Main, yytext());}

( "," ) {return symbol(sym.Coma, yytext());}

/* Punto y coma */
( ";" ) {return symbol(sym.P_coma, yytext());}

/* Switch */
( Castillo ) {return symbol(sym.Castillo, yytext());}

/* Case */
( Toad ) {return symbol(sym.Toad, yytext());}

/* Default */
( Peach ) {return symbol(sym.Peach, yytext());}

/* Modos */
( mushroom | ffuego | tanuki | frog | capa ) {return symbol(sym.PowerUp, yytext());}

/* Identificador */
{L}({L}|{D})* {return symbol(sym.Identificador, yytext());}

/* Número flotante o negativo */
("-"?{D}+"."{D}+) {return symbol(sym.NumeroFlotante, yytext());}

/* Número entero o negativo */
("-"?{D}+) {return symbol(sym.Numero, yytext());}

/* Error de análisis */
. {return symbol(sym.ERROR, yytext());}
