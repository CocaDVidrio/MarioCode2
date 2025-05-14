package cod;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class TabladeSimbolos {
 private final String[] palabras;

    public TabladeSimbolos() {
     palabras = new String[]{
    "sensor",
    "temperatura",
    "infrarrojo",
    "hora",
    "beber",
    "alimentar",
    "encender",
    "apagar",
    "pantalla",
    "nuevo",
    "funciona",
    "inicio_granja",
    "fin_granja",
    "edd",
    "sdd",
    "alarma",
    "detener",
    "esperar",
    "configurar",
    "devuelve",
    "con_retorno",
    "definir",
    "leer_sensor",
    "si",
    "contrario",
    "contrario_si",
    "segun",
    "caso",
    "termina",
    "fin_segun",
    "para",
    "hasta",
    "incrementa",
    "fin_para",
    "mientras",
    "fin_mientras",
    "funcion",
    "fin_funcion",
    "inicio_programa",
    "fin_programa",
    "dispositivos",
    "fin_dispositivos",
    "entero",
    "flotante",
    "logico",
    "cadena",
    "estructura",
    "enumeraciones",
    "estados",
    "evento",
    "suma",
    "resta",
    "multiplicacion",
    "division",
    "mayor",
    "menor",
    "menorigual",
    "mayorigual",
    "igual",
    "diferente",
    "asignacion",
    "puntocoma",
    "punto",
    "coma",
    "and",
    "or",
    "par_abre",
    "par_cierra",
    "llave_abre",
    "llave_cierra",
    "gato",
    "comilla_doble",
    "diagonal"
};
    }
 
  public boolean isReservada(String s){
      for(int i = 0; i < 5; i++){
          if(palabras[i].equals(s)){
              return true;
          }
      }
      
      return false;
  }
  
}
