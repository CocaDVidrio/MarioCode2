package cod;

import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]

ComentarioDeUnaLinea = "#" {EntradaDeCaracter}* {TerminadorDeLinea}?
ComentarioMultilinea = \*\*\*([^*]|\*[^*]|\*\*[^*])*\*\*\*


/* Comentario */
Comentario = {ComentarioDeUnaLinea}|{ComentarioMultilinea}

/* Identificadores y números */
Letra = [A-Za-z]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*
Numero = 0 | [1-9][0-9]*
%%


/* Comentarios y espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }

/* Cadenas de texto */
\"[^\"]*\" { return textColor(yychar, yylength(), new Color(144, 231, 44)); }

/* Palabras reservadas */
moverMario | tuberia | mover | castillo | toad | POW 
    { return textColor(yychar, yylength(), new Color(0, 0, 255)); }

/* Tipos de datos */
mario | luigi | peach | toad  
    { return textColor(yychar, yylength(), new Color(255, 99, 188)); }

/* Palabras clave especiales */
inicio_nivel | fin_nivel  
    { return textColor(yychar, yylength(), new Color(255, 140, 0)); }

/* Caracteres especiales */
[.+\-*/%]  { return textColor(yychar, yylength(), new Color(182, 149, 192)); }
[=><!]=?   { return textColor(yychar, yylength(), new Color(192, 0, 191)); }
[{}()!]    { return textColor(yychar, yylength(), new Color(255, 181, 82)); }

/* Operadores lógicos */
and | or | not { return textColor(yychar, yylength(), new Color(184, 184, 184 )); }
";" | ":" | "$" | "," { /* Ignorar */ }

/* Identificador */
{Identificador}  { /* Ignorar */ }


. { /* Ignorar */ }