package pilas.colas;

import java.util.ArrayList;

public class Colas {
    
    private carro start;
    private carro last;
    private Integer length;

    public Colas(){
        start=null;
        last=null;
        length = 0;
    }

    public Integer getLength() {
        return length;
    }

    public void insertar(int serial){
        carro auto = new carro();
        auto.setSerial(serial);
        auto.setNext(null);
        length++;
        if(isEmpty()){
            start=auto;
            last=auto;
        }else{
            last.setNext(auto);
            last=auto;
        }
    }

    public ArrayList<Integer> extraer(int a_buscar){
        ArrayList<Integer> antes_de = new ArrayList<>();
        if (se_encuentra(a_buscar)) {
            while(a_buscar != start.getSerial()){
                antes_de.add(quitar());
            }
            quitar();
            return antes_de;
        }
        return antes_de;
    }
    
    public int quitar(){
        int dato = start.getSerial();
        start = start.getNext();
        length--;
        return dato;
    }

    public boolean isEmpty(){
        boolean cola=false;
        if(start==null && last==null){
           cola=true;
        }
        else{
           cola=false;
        }
        return cola;
    }

    public ArrayList<Integer> getCarros(){
        ArrayList<Integer> autos = new ArrayList<>();
        
        if (isEmpty()) {
            return autos;
        }
        carro aux = start;
        while(aux != null){
            autos.add(aux.getSerial());                
            aux = aux.getNext();
        }
        return autos;
    }

    
    public int contar(){
        int contador=0;
        carro c= this.start;
        while(c!=null){
            contador++;
            c = c.getNext();
        }
        return contador;
    }

    public void imprimirCola(Integer num){
        System.out.println("cola # " + (num) + " #");  
        if (start != null) {               
            carro aux = start;
            while (aux.getNext() != null) {
                System.out.println(aux.getSerial());
                aux = aux.getNext();            
            }
            System.out.println(aux.getSerial());
        }
    }

        
    public boolean se_encuentra(int a_buscar){
        carro aux = start;
        boolean existe = false;
        while(existe != true && aux != null){
            if (a_buscar == aux.getSerial()) {
                existe = true;
            }
            else{
                aux = aux.getNext();
            }
        }
        return existe;
    }    
    
}
