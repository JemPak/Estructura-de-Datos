
package pilas.colas;

import java.util.ArrayList;
import java.util.Scanner;


public class ejecutar {
    
    ArrayList<Pilas> pilas = new ArrayList<>();
    ArrayList<Colas> colas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ejecutar() {
        Integer contenedores = 498; // inicializar contenedores en 498
        Integer carros = 98; // inicializar carros en 98
        
        Integer serial_contenedor = 1;
        Integer serial_carro = 1;
        for (int i = 0; i < 100; i++) {
            pilas.add(new Pilas());
            for (int j = 0; j < 5; j++) {
                if (serial_contenedor != (contenedores+1)) {
                    pilas.get(i).insertar(serial_contenedor);
                    serial_contenedor++;
                }
            }
        }
        
        for (int i = 0; i < 10; i++) {
            colas.add(new Colas());
            for (int j = 0; j < 10; j++) {
                if (serial_carro != (carros+1)) {
                    colas.get(i).insertar(serial_carro);
                    serial_carro++;
                }
            }
        }
        
    }
    
    public void esta_el_auto(int serial_contenedor){
        for (int i = 0; i < 10; i++) {
            if (colas.get(i).se_encuentra(serial_contenedor)) {
                System.out.println("El auto con serial " + serial_contenedor + " se encuentra en la colas " + (i+1));
            }
        }
        System.out.println("No existe un auto con ese serial");
    }
    
    public void esta_el_contenedor(int serial_contenedor){
        for (int i = 0; i < 100; i++) {
            if (pilas.get(i).esta_en_pila(serial_contenedor)) {
                System.out.println("El contenedor se encuentra en la pila " + (i+1));
            }
        }
    }
    
    public String add_contenedor(int serial_contenedor){
        for (int i = 0; i < 100; i++) {
            if (pilas.get(i).getLength()< 5) {
                pilas.get(i).insertar(serial_contenedor);
                System.out.println("El contenedor se guardo en la Pila " + (i+1));
                return null;
            }            
        }
        System.out.println("No hay espacio disponible para ubicar el contenedor");
        return null;
    }
     
    public String add_auto(int serial_contenedor){
        for (int i = 0; i < 10; i++) {
            if (colas.get(i).getLength()< 10) {
                colas.get(i).insertar(serial_contenedor);
                System.out.println("El Automovil se guardo en la Colas " + (i+1));
            }            
        }
        System.out.println("No hay espacio disponible para ubicar el carro");
        return null;
    }
    
    public void cantidadContenedores(){
        Integer contenedores = 0;
        for (Pilas Pilas1 : pilas) {
            contenedores += Pilas1.getLength();
        }
        System.out.println("Se encuentran " + contenedores + " contenedores");
    }
    
    
    public void cantidadAutos(){
        Integer autos = 0;
        for (Colas Colas1 : colas) {
            autos += Colas1.getLength();
        }
        System.out.println("Se encuentran " + autos + " Automoviles");
    }
        
    public void mostrarPuerto(){
        System.out.println("#####contenedores#####");
        for (int i = 0; i < 100; i++) {
            pilas.get(i).mostrar_pila((i+1));
        }
        System.out.println("#####carros#####");
        for (int i = 0; i < 10; i++) {
            colas.get(i).imprimirCola((i+1));
        }
    }
    
    public String quitarContenedor(int serial_contenedor){
        for (int i = 0; i < 100; i++) {
            if (pilas.get(i).esta_en_pila(serial_contenedor)) {
                ArrayList<Integer> porMover = pilas.get(i).quitar(serial_contenedor);
                String mensaje = "el contenedor se quito de la Pila " + (i+1);
                if (porMover.isEmpty()) {                    
                    System.out.println(mensaje);
                    return null;
                }
                int espacios = -5;                                
                for (int j = 0; j < 100; j++) {
                    if (i != j) {
                        espacios += (5-pilas.get(j).getLength());                        
                    }
                }
                
                if (espacios >= porMover.size()) {
                    int index = 0;
                    for (int j = i+1; j < 100; j++) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                System.out.println(mensaje);
                                return null;
                            }
                            if (pilas.get(j).getLength()< 5) {
                                pilas.get(j).esta_en_pila(porMover.get(index));
                                mensaje += "\nse agrego el contenedor con serial " + porMover.get(index) + " a la Pilas " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                       
                    }
                    for (int j = i-1; j > 0; j--) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                System.out.println(mensaje);
                                return null;
                            }
                            if (pilas.get(j).getLength()< 5) {
                                pilas.get(j).esta_en_pila(porMover.get(index));
                                mensaje += "\nse agrego el contenedor con serial_contenedor " + porMover.get(index) + " a la Pilas " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                      
                    }
                }else{
                    for (int j = porMover.size()-1; j > -1 ; j--) {
                        pilas.get(i).esta_en_pila(porMover.get(j));
                    }
                    System.out.println("el puerto estaba lleno y se utilizó un apila extra para mover los contenedores");
                    return null;
                }
                System.out.println(mensaje);
                return null;
            }
        }
        return null;
    }
    
    public String quitarAuto(int serial_contenedor){
        for (int i = 0; i < 10; i++) {
            if (colas.get(i).se_encuentra(serial_contenedor)) {
                
                ArrayList<Integer> porMover = colas.get(i).extraer(serial_contenedor);
                String mensaje = "el automovil se extrajo de la Cola " + (i+1);
                if (porMover.isEmpty()) {                    
                    System.out.println(mensaje);
                    return null;
                }
                int espacios = 0;                                
                for (int j = 0; j < 10; j++) {
                    espacios += (10-colas.get(j).getLength());                        
                }
                
                if (espacios >= porMover.size()) {
                    int index = 0;
                    for (int j = i; j < 10; j++) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                System.out.println(mensaje);
                                return null;
                            }
                            if (colas.get(j).getLength()< 10) {
                                colas.get(j).insertar(porMover.get(index));
                                System.out.println(colas.get(j).getLength());
                                mensaje += "\nse agrego el automovil con serial " + porMover.get(index) + " a la Colas " + (j+1);
                                index++;
                            }else{
                                break;
                            }                 
                        }                       
                    }
                    for (int j = i-1; j > 0; j--) {  
                        while (true){                                                   
                            if (index == porMover.size()) {
                                System.out.println(mensaje);
                                return null;
                            }
                            if (colas.get(j).getLength()< 10) {
                                colas.get(j).insertar(porMover.get(index));
                                mensaje += "\nse agrego el Automovil con serial " + porMover.get(index) + " a la Colas " + (j+1);
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
                    mensaje += "El puerto estaba lleno, los automoviles se enColaron \nen una Colas extra y luego volvieron a su Colas original " + (i+1);
                }
                System.out.println(mensaje);
                return null;
            }
        }
        System.out.println("No se encontro el automovil con serial " + serial_contenedor + " en el puerto");
        return null;
    }
    
    public void menu(){
        System.out.println("aqui");
        boolean var = true;
        while (var == true){
            System.out.println("# 1 # Agregar Contenedor");
            System.out.println("# 2 # Quitar Contenedor");
            System.out.println("# 3 # Buscar Contenedor");
            System.out.println("# 4 # Mostrar Puerto");
            System.out.println("# 5 # Mostrar Pila ");
            System.out.println("# 6 # Mostrar Total Contenedores ");
            System.out.println("# 7 # Agregar Carro");
            System.out.println("# 8 # Quitar Carro");
            System.out.println("# 9 # Buscar Carro");
            System.out.println("# 10 # Imprimir Cola");
            System.out.println("# 11 # Total Carros");
            System.out.println("# 12 # Terminar ejecucion del Puerto");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Ingrese el serial del contenedor: ");
                    int serial_contenedor = sc.nextInt();
                    add_contenedor(serial_contenedor);
                    break;            
                case 2:
                    System.out.println("Ingrese el serial del contenedor a extraer: ");
                    int extraer = sc.nextInt();    
                    quitarContenedor(extraer);
                    break;            
                case 3:
                    System.out.println("Ingrese el serial del contenedor a buscar: ");
                    int buscar = sc.nextInt();   
                    esta_el_contenedor(buscar);
                    break;           
                case 4:
                    mostrarPuerto();
                    break;          
                case 5:
                    System.out.println("Ingrese la Pilas a consultar");
                    int Pilas = sc.nextInt();
                    for (int i = 0; i < 100; i++) {
                        if ((i+1) == Pilas) {
                            ArrayList<Integer> contenedores = pilas.get(i).mirar_containers();
                            for (Integer contenedore : contenedores) {
                                System.out.println(contenedore);
                            }
                        }
                    }
                    break; 
                case 6:
                    cantidadContenedores();
                    break;
                case 7:
                    System.out.println("Ingrese el serial del automovil: ");
                    int auto = sc.nextInt();
                    add_auto(auto);
                    break;
                case 8:
                    System.out.println("Ingrese el serial del automovil a extraer: ");
                    int aextraer = sc.nextInt();    
                    quitarAuto(aextraer);
                    break;
                case 9:
                    System.out.println("Ingrese el serial del automovil a buscar: ");
                    int abuscar = sc.nextInt();   
                    esta_el_auto(abuscar);
                    break;
                case 10:
                    System.out.println("Ingrese la Colas a consultar");
                    int Colas = sc.nextInt();
                    for (int i = 0; i < 10; i++) {
                        if ((i+1) == Colas) {
                            ArrayList<Integer> autos = colas.get(i).getCarros();
                            for (Integer auto1 : autos) {
                                System.out.println(auto1);
                            }
                        }
                    }
                    break;
                case 11:
                    cantidadAutos();
                    break;
                default:
                    var = false;
                    System.out.println("Gracias por utilizar nuestro programa, vuelve pronto !!");
            }
        }
    }
}
