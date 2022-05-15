
package pilas.colas;

import java.util.ArrayList;


public class Pilas {
    
    private container inicio;
    private Integer longitud = 0;

    public Pilas(){
        this.inicio = null;
        this.longitud = 0;
    }

    public boolean isEmpty(){
        return inicio == null;
    }

    public Integer getLength(){
        return longitud;
    }
    
    public void mostrar_pila(Integer num){
        System.out.println("Pila # " + num + " #");
        if (inicio != null) {               
            container aux = inicio;
            while (aux.getNext()!= null) {
                System.out.println(aux.getSerial());
                aux = aux.getNext();            
            }
            System.out.println(aux.getSerial());
        }
    }
    
    public boolean esta_en_pila(int a_buscar){
        container aux = inicio;
        boolean existe = false;

        while(existe != true && aux != null){
            if (a_buscar == aux.getSerial()) {
                existe = true;
                break;
            }
            aux = aux.getNext();
            
        }
        return existe;
    }

    public void insertar(Integer value){
        container nuevo = new container();
        nuevo.setSerial(value);
        if (isEmpty()) {
            inicio = nuevo;
        }
        else{
            nuevo.setNext(inicio);
            inicio = nuevo;
        }
        longitud += 1;
    }

    public void retirar(){
        if (isEmpty() == false) {
            inicio = inicio.getNext();
            longitud--;
        }
    }

    public ArrayList<Integer> mirar_containers(){
        ArrayList<Integer> containeres = new ArrayList<>();
        if (isEmpty()){
            return containeres;
        }
        container aux = inicio;
        while(aux != null){
            containeres.add(0, aux.getSerial());                
            aux = aux.getNext();
        }
        return containeres;
    }
    
    public ArrayList<Integer> quitar(int a_buscar){
        ArrayList<Integer> encima = new ArrayList<>();
        if (esta_en_pila(a_buscar)) {
            while(a_buscar != inicio.getSerial()){
                encima.add(inicio.getSerial());
                retirar();
            }
            retirar();
            return encima;
        }
        return encima;
    }
}
