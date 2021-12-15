
package puerto;


import java.util.ArrayList;

public class pila {

    private contenedor inicio;
    // Variable para registrar el tamaño de la pila.
    private Integer tamanio = 0;
    /**
     * Constructor por defecto.
     */
    public pila(){
        this.inicio = null;
        this.tamanio = 0;
    }
    /**
     * Consulta si la pila esta vacia.
     * @return true si el primer contenedor (inicio), no apunta a otro contenedor.
     */
    public boolean esVacia(){
        return inicio == null;
    }

    public Integer getTamanio(){
        return tamanio;
    }

    public void apilar(Integer valor){
        // Define un nuevo contenedor.
        contenedor nuevo = new contenedor();
        // Agrega al serial al contenedor.
        nuevo.setSerial(valor);
        // Consulta si la pila esta vacia.
        if (esVacia()) {
            // Inicializa la pila con el nuevo valor.
            inicio = nuevo;
        }
        // Caso contrario agrega el nuevo contenedor al inicio de la pila.
        else{
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        // Incrementa el contador del tamaño.
        tamanio += 1;
    }
    /**
     * Elimina el elemento que se encuentra en el tope de la pila.
     * organizar metodo para retornar una lista
     */
    public void retirar(){
        if (!esVacia()) {
            // Asigna como primer contenedor al siguiente de la pila.
            inicio = inicio.getSiguiente();
            // Decrementa el contador del tamaño de la pila
            tamanio--;
        }
    }
    /**
     * Consulta el valor del contenedor que se encuentra en la cima de la pila
     * @return valor del contenedor.
     * @throws Exception
     */
    public int cima() throws Exception{
        if(!esVacia()){
            return inicio.getSerial();
        } else {
            throw new Exception("La pila se encuentra vacia.");
        }
    }
    /**
     * Busca un elemento en la pila.
     * @param referencia valor del serial del contenedor a buscar.
     * @return numero entre 1 y 5 si el valor de referencia existe en la pila.
     */
    public boolean buscar(int referencia){
        // Crea una copia de la pila.
        contenedor aux = inicio;
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
                aux = aux.getSiguiente();
            }
        }
        // Retorna el valor del indice.
        return existe;
    }
    /**
     * Elimina un contenedor de la pila ubicado por su valor.
     * @param referencia valor de referencia para ubicar el contenedor.
     * @return ArrayList con los seriales a organizar en otro lugar
     */
    public ArrayList<Integer> remover(int referencia){
        // arraylist para guardar los contenedores a mover
        ArrayList<Integer> porMover = new ArrayList<>();
        // Consulta si el valor existe en la pila.
        if (buscar(referencia)) {
            // Recorre la pila hasta llegar al contenedor que tenga el valor
            // igual que el de referencia.
            while(referencia != inicio.getSerial()){
                porMover.add(inicio.getSerial());
                // Elimina el contenedor del tope de la pila hasta llegar al contenedor
                // que se desea eliminar.
                retirar();
            }
            // Elimina el contenedor que coincide con el de referencia.
            retirar();
            return porMover;
        }
        return porMover;
    }

    public ArrayList<Integer> getContenedores(){
        ArrayList<Integer> contenedores = new ArrayList<>();
        
        if (esVacia()) return contenedores;
           
        contenedor aux = inicio;
        
        while(aux != null){
            // insertar al inicio del arraylist
            contenedores.add(0, aux.getSerial());                
            aux = aux.getSiguiente();
        }
//        contenedores.add(0, aux.getSerial());                
        return contenedores;
    }
    
    public void imprimirPila(){
        if (inicio != null) {               
            contenedor aux = inicio;
            while (aux.getSiguiente() != null) {
                System.out.println(aux.getSerial());
                aux = aux.getSiguiente();            
            }
            System.out.println(aux.getSerial());
        }
    }

}
