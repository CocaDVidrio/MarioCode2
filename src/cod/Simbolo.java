/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cod;

/**
 *
 * @author P3DR0
 */
public class Simbolo {
    private String nombre;
    private String tipo;
    private String valor;
    private String lineaDeclaracion;
    String lineaReferencia;
    private String ambito;

    public Simbolo(String nombre, String tipo, String valor, String lineaDeclaracion, String lineaReferencia, String ambito) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.lineaDeclaracion = lineaDeclaracion;
        this.lineaReferencia = lineaReferencia;
        this.ambito = ambito;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public String getLineaDeclaracion() {
        return lineaDeclaracion;
    }

    public String getLineaReferencia() {
        return lineaReferencia;
    }

    public String getAmbito() {
        return ambito;
    }
    
    public String addLinea(String linea){
            return lineaReferencia+ ", "+linea;
    }
    
    public void setValor(String val){
        valor = val;
    }
}
