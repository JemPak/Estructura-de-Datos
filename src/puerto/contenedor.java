/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puerto;

/**
 *
 * @author JUAN JOSE MONSALVE
 */

public class contenedor {

    private Integer serial;
    // Variable para enlazar los nodos.
    private contenedor siguiente;

    /**
     * Constructor que inicializamos el valor de las variables.
     */
    public void contenedor(){
        this.serial = 0;
        this.siguiente = null;
    }

    // Métodos get y set para los atributos.

    public int getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public contenedor getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(contenedor siguiente) {
        this.siguiente = siguiente;
    }
}
