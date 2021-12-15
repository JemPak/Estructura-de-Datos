/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puerto;

import java.util.ArrayList;
import java.util.Scanner;

public class Puerto {
    ArrayList<pila> pilas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Puerto(Integer maxSeriales) {
        Integer serial = 1;
        for (int i = 0; i < 100; i++) {
            pilas.add(new pila());
            System.out.println("pila " + (i+1));
            for (int j = 0; j < 5; j++) {
                if (serial != (maxSeriales+1)) {
                    pilas.get(i).apilar(serial);
                    System.out.println("contenedor " + serial);
                    serial++;
                }
            }
        }
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
        
    public String cantidadContenedores(){
        Integer contenedores = 0;
        for (pila pila1 : pilas) {
            contenedores += pila1.getTamanio();
        }
        String response = "En el puerto se encuentran " + contenedores + " contenedores";
        return response;
    }
    
    public void imprimirPuerto(){
        for (int i = 0; i < 100; i++) {
            if (!pilas.get(i).esVacia()) {
                System.out.println("pila " + (i+1));   
                pilas.get(i).imprimirPila();                
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
                    // comprobar si se ubicó la totalidad de los contenedores
//                    if (index == porMover.size()) {
//                        break;
//                    }else{
//                        // agregar los contenedores restantes a la izq
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
//                    }
                }else{
                    //crear pila adicional
                    for (int j = 0; j < porMover.size(); j++) {
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
    
    public void options(){
        boolean var = true;
        while (var == true){
            System.out.println("1 ---- Ingresar Contenedor");
            System.out.println("2 ---- Extraer Contenedor");
            System.out.println("3 ---- Buscar Contenedor por Serial");
            System.out.println("4 ---- Imprimir Pilas del Puerto");
            System.out.println("5 ---- Total Contenedores en puerto");
            System.out.println("6 ---- para salir del menú principal");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Ingrese el serial del contenedor: ");
                    int serial = sc.nextInt();
                    System.out.println(serial);
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
                    System.out.println(cantidadContenedores());                    
                    System.out.println("aqui");
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
        Puerto puerto = new Puerto(contenedores);        
        puerto.options();        
        
    }
    
}
