
package puerto;

import java.util.ArrayList;
import java.util.Scanner;

public class Puerto {
    ArrayList<pila> pilas = new ArrayList<>();
    ArrayList<cola> colas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Puerto(Integer maxContainer, Integer maxAutos) {
        Integer serial = 1;
        for (int i = 0; i < 100; i++) {
            pilas.add(new pila());
            System.out.println("pila " + (i+1));
            for (int j = 0; j < 5; j++) {
                if (serial != (maxContainer+1)) {
                    pilas.get(i).apilar(serial);
                    System.out.println("contenedor " + serial);
                    serial++;
                }
            }
        }
        Integer serial2 = 1;
        for (int i = 0; i < 10; i++) {
            colas.add(new cola());
            System.out.println("cola " + (i+1));
            for (int j = 0; j < 10; j++) {
                if (serial2 != (maxAutos+1)) {
                    colas.get(i).insertar(serial2);
                    System.out.println("Automovil " + serial2);
                    serial2++;
                }
            }
        }
        
    }
    
    public String buscarAuto(int serial){
        for (int i = 0; i < 10; i++) {
            if (colas.get(i).buscar(serial)) {
                String response = "El Automovil con serial " + serial + " se encuentra en la cola: " + (i+1);
                return response;
            }
        }
        String reponse = "No se encontró el Automovil con serial: " + serial + " en el puerto"; 
        return reponse;      
    }
    
    public String buscarContenedor(int serial){
        for (int i = 0; i < 100; i++) {
            if (pilas.get(i).buscar(serial)) {
                String response = "El contenedor con serial " + serial + " se encuentra en la pila: " + (i+1);
                return response;
            }
        }
        String reponse = "No se encontró el contenedor con serial: " + serial + " en el puerto"; 
        return reponse;      
    }
    
    public String agregarContenedor(int serial){
        for (int i = 0; i < 100; i++) {
            if (pilas.get(i).getTamanio() < 5) {
                pilas.get(i).apilar(serial);
                String response = "El contenedor con serial: " + serial + " se guardo en la pila " + (i+1);
                return response;
            }            
        }
        String response = "No se encontro un espacio disponible para ubicar el contenedor";
        return response;
    }
     
    public String agregarAuto(int serial){
        for (int i = 0; i < 10; i++) {
            if (colas.get(i).getTamanio() < 10) {
                colas.get(i).insertar(serial);
                String response = "El Automovil con serial: " + serial + " se guardo en la cola " + (i+1);
                return response;
            }            
        }
        String response = "No se encontro un espacio disponible para ubicar el Automovil";
        return response;
    }
    
    public String cantidadContenedores(){
        Integer contenedores = 0;
        for (pila pila1 : pilas) {
            contenedores += pila1.getTamanio();
        }
        String response = "En el puerto se encuentran " + contenedores + " contenedores";
        return response;
    }
    
    
    public String cantidadAutos(){
        Integer autos = 0;
        for (cola cola1 : colas) {
            autos += cola1.getTamanio();
        }
        String response = "En el puerto se encuentran " + autos + " Automoviles";
        return response;
    }
        
    public void imprimirPuerto(){
        System.out.println("#####CONTENEDORES####");
        for (int i = 0; i < 100; i++) {
            if (!pilas.get(i).esVacia()) {
                System.out.println("pila " + (i+1));   
                pilas.get(i).imprimirPila();                
            }
        }
        System.out.println("#####AUTOMOVILES####");
        for (int i = 0; i < 10; i++) {
            if (!colas.get(i).estaVacia()) {
                System.out.println("cola " + (i+1));   
                colas.get(i).imprimirCola();
            }
        }
    }
    
    
    public String extraerContenedor(int serial){
        for (int i = 0; i < 100; i++) {
            if (pilas.get(i).buscar(serial)) {
                
                ArrayList<Integer> porMover = pilas.get(i).remover(serial);
                String response = "el contenedor se extrajo de la pila " + (i+1);
                System.out.println(porMover.toString());
                if (porMover.isEmpty()) {                    
                    return response;
                }
                // analizar espacio para ingresar los otros contenedore
                int espacios = -5;                                
                // evaluar espacios disponibles
                for (int j = 0; j < 100; j++) {
                    if (i != j) {
                        espacios += (5-pilas.get(j).getTamanio());                        
                    }
                }
                
                if (espacios >= porMover.size()) {
                    int index = 0;
                    for (int j = i+1; j < 100; j++) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                return response;
                            }
                            if (pilas.get(j).getTamanio() < 5) {
                                pilas.get(j).apilar(porMover.get(index));
                                System.out.println(pilas.get(j).getTamanio());
                                response += "\nse agrego el contenedor con serial " + porMover.get(index) + " a la pila " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                       
                    }
                    for (int j = i-1; j > 0; j--) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                return response;
                            }
                            if (pilas.get(j).getTamanio() < 5) {
                                pilas.get(j).apilar(porMover.get(index));
                                response += "\nse agrego el contenedor con serial " + porMover.get(index) + " a la pila " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                      
                    }
                }else{
                    for (int j = porMover.size()-1; j > -1 ; j--) {
                        pilas.get(i).apilar(porMover.get(j));
                    }
                    response += "El puerto estaba lleno, los contenedores se apilaron \nen una pila extra y luego volvieron a su pila original " + (i+1);
                }
                return response;                        
            }
        }
        String response = "No se encontro el contenedor con serial " + serial + " en el puerto";
        return response;
    }
    
    public String extraerAuto(int serial){
        for (int i = 0; i < 10; i++) {
            if (colas.get(i).buscar(serial)) {
                
                ArrayList<Integer> porMover = colas.get(i).remover(serial);
                String response = "el automovil se extrajo de la cola " + (i+1);
                System.out.println(porMover.toString());
                if (porMover.isEmpty()) {                    
                    return response;
                }
                // analizar espacio para ingresar los otros autos
                int espacios = 0;                                
                // evaluar espacios disponibles
                for (int j = 0; j < 10; j++) {
                    espacios += (10-colas.get(j).getTamanio());                        
                }
                
                if (espacios >= porMover.size()) {
                    int index = 0;
                    for (int j = i; j < 10; j++) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                return response;
                            }
                            if (colas.get(j).getTamanio() < 10) {
                                colas.get(j).insertar(porMover.get(index));
                                System.out.println(colas.get(j).getTamanio());
                                response += "\nse agrego el automovil con serial " + porMover.get(index) + " a la cola " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                       
                    }
                    for (int j = i-1; j > 0; j--) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                return response;
                            }
                            if (colas.get(j).getTamanio() < 10) {
                                colas.get(j).insertar(porMover.get(index));
                                response += "\nse agrego el Automovil con serial " + porMover.get(index) + " a la cola " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                      
                    }
                }else{
                    for (int j = 0; j < porMover.size() ; j--) {
                        colas.get(i).insertar(porMover.get(j));
                    }
                    response += "El puerto estaba lleno, los automoviles se encolaron \nen una cola extra y luego volvieron a su cola original " + (i+1);
                }
                return response;                        
            }
        }
        String response = "No se encontro el automovil con serial " + serial + " en el puerto";
        return response;
    }
    
    public void options(){
        boolean var = true;
        while (var == true){
            System.out.println("1 ---- Ingresar Contenedor");
            System.out.println("2 ---- Extraer Contenedor");
            System.out.println("3 ---- Buscar Contenedor por Serial");
            System.out.println("4 ---- Imprimir Puerto");
            System.out.println("5 ---- Imprimir contenedores por pila");
            System.out.println("6 ---- Total Contenedores en puerto");
            System.out.println("7 ---- Ingresar Automovil");
            System.out.println("8 ---- Extraer Automovil");
            System.out.println("9 ---- Buscar Automovil por Serial");
            System.out.println("10 ---- Imprimir Automoviles por cola");
            System.out.println("11 ---- Total Automoviles en puerto");
            System.out.println("12 ---- para salir del menú principal");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Ingrese el serial del contenedor: ");
                    int serial = sc.nextInt();
                    System.out.println(agregarContenedor(serial));                    
                    break;            
                case 2:
                    System.out.println("Ingrese el serial del contenedor a extraer: ");
                    int extraer = sc.nextInt();    
                    System.out.println(extraerContenedor(extraer));                    
                    break;            
                case 3:
                    System.out.println("Ingrese el serial del contenedor a buscar: ");
                    int buscar = sc.nextInt();   
                    System.out.println(buscarContenedor(buscar));                    
                    break;           
                case 4:
                    imprimirPuerto();
                    break;            // salir de la aplicacion
                case 5:
                    System.out.println("Ingrese la pila a consultar");
                    int pila = sc.nextInt();
                    for (int i = 0; i < 100; i++) {
                        if ((i+1) == pila) {
                            String contenedores = pilas.get(i).getContenedores().toString();
                            System.out.println(contenedores);
                        }
                    }
                    break; 
                case 6:
                    System.out.println(cantidadContenedores());                    
                    break;
                case 7:
                    System.out.println("Ingrese el serial del automovil: ");
                    int auto = sc.nextInt();
                    System.out.println(auto);
                    System.out.println(agregarAuto(auto));      
                    break;
                case 8:
                    System.out.println("Ingrese el serial del automovil a extraer: ");
                    int aextraer = sc.nextInt();    
                    System.out.println(extraerAuto(aextraer)); 
                    break;
                case 9:
                    System.out.println("Ingrese el serial del automovil a buscar: ");
                    int abuscar = sc.nextInt();   
                    System.out.println(buscarAuto(abuscar));     
                    break;
                case 10:
                    System.out.println("Ingrese la cola a consultar");
                    int cola = sc.nextInt();
                    System.out.println("### La cola contiene los autos ###");
                    for (int i = 0; i < 10; i++) {
                        if ((i+1) == cola) {
                            ArrayList<Integer> autos = colas.get(i).getAutos();
                            for (Integer auto1 : autos) {
                                System.out.println(auto1);
                            }
                        }
                    }
                    System.out.println("###############################");
                    break;
                case 11:
                    System.out.println(cantidadAutos());                    
                    break;
                default:
                    var = false;
                    System.out.println("Salida Exitos del programa");
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad inicial de contenedores");
        int contenedores = sc.nextInt();
        System.out.println("Ingrese la cantidad inicial de Automoviles");
        int autos = sc.nextInt();
        Puerto puerto = new Puerto(contenedores, autos);        
        puerto.options();        
        
    }
    
}
