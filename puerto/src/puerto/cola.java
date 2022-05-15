/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puerto;

import java.util.ArrayList;

public class cola {
    
    //Declaración de atributos
    private autos inicio;
    private autos termino;
    private Integer tamanio;

    //Constructor sin parametros
    public cola(){
        inicio=null;
        termino=null;
        tamanio = 0;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    //Metodo insertar
    public void insertar(int serial){
        autos auto = new autos();
        auto.setSerial(serial);
        auto.setNext(null);
        tamanio++;
        if(estaVacia()){
            inicio=auto;
            termino=auto;
        }else{
            termino.setNext(auto);
            termino=auto;
        }
    }

    //Metodo extraer dato
    public ArrayList<Integer> remover(int referencia){
        ArrayList<Integer> porMover = new ArrayList<>();
        if (buscar(referencia)) {
            while(referencia != inicio.getSerial()){
                porMover.add(extraer());
            }
            // Elimina el contenedor que coincide con el de referencia.
            extraer();
            return porMover;
        }
        return porMover;
    }
    
    public int extraer(){
        int dato = inicio.getSerial();
        inicio = inicio.getNext();
        tamanio--;
        return dato;
    }

    //Metodo para comprobar que la cola no esta vacia
    public boolean estaVacia(){
        boolean cola=false;
        if(inicio==null && termino==null){
           cola=true;
        }
        else{
           cola=false;
        }
        return cola;
    }

    //Metodo para contar los elementos de la cola
    public int contar(){
        int contador=0;
        autos c= this.inicio;
        while(c!=null){
            contador++;
            c = c.getNext();
        }
        return contador;
    }

    public boolean buscar(int referencia){
        // Crea una copia de la pila.
        autos aux = inicio;
        // Bandera para verificar si existe el elemento a buscar.
        boolean existe = false;
        // Recorre la pila hasta llegar encontrar el contenedor o llegar al final

        while(existe != true && aux != null){
            // Compara si el valor del contenedor es igual que al de referencia.
            if (referencia == aux.getSerial()) {
                // Cambia el valor de la bandera.
                existe = true;
            }
            else{
                // Avanza al siguiente contenedor.
                aux = aux.getNext();
            }
        }
        // Retorna el valor del indice.
        return existe;
    }

    public ArrayList<Integer> getAutos(){
        ArrayList<Integer> automoviles = new ArrayList<>();
        
        if (estaVacia()) return automoviles;
           
        autos aux = inicio;
        
        while(aux != null){
            // insertar al inicio del arraylist
            automoviles.add(aux.getSerial());                
            aux = aux.getNext();
        }
//        automoviles.add(aux.getSerial());                
        return automoviles;
    }
    
    public void imprimirCola(){
        if (inicio != null) {               
            autos aux = inicio;
            while (aux.getNext() != null) {
                System.out.println(aux.getSerial());
                aux = aux.getNext();            
            }
            System.out.println(aux.getSerial());
        }
    }
    
}
