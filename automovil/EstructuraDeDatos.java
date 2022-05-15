
package estructuradedatos;

import java.util.*;
public class EstructuraDeDatos {
    private ArrayList<vehiculo> vehiculos = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);
 
    public void options(){
        boolean var = true;
        while (var == true){
            System.out.println("1 ---- Registrar Vehículo");
            System.out.println("2 ---- Tabla de datos");
            System.out.println("3 ---- Busqueda de Registro por Indice");
            System.out.println("4 ---- Borrar Registo por Indice");
            System.out.println("5 ---- para salir del menú principal");
            int option = sc.nextInt();

            switch (option) {
            //registrar vehiculo, se llama a la funcion agregar_vehiculo
                case 1:
                    agregar_vehiculo();
                    break;
            //Tabla de datos, se llama a la funcion mostrar_datos
                case 2:
                    mostrar_datos();
                    break;
            //Busqueda por indice, se llama a la funcion busqueda
                case 3:
                    filtro_indice();
                    break;
            //Borrar indice, se llama a la funcion borrar
                case 4:
                    borrar_por_indice();
                    break;
            // salir de la aplicacion
                default:
                    var = false;
                    System.out.println("Salida Exitos del programa");
                    break;
            }
        }
    }
    public void filtro_indice(){
        System.out.println("Ingrese el indice del registro a buscar: ");
        int indice = sc.nextInt();
        vehiculo vehiculo1 = vehiculos.get(indice);
        System.out.println("La busqueda Arrojó: " + vehiculo1.toString());
    }
    
    public void borrar_por_indice(){
        System.out.println("Ingrese el indice del registro a borrar: ");
        int indice = sc.nextInt();
        vehiculo vehiculo1 = vehiculos.remove(indice);
        System.out.println("Se borró de la lista el registro: " + vehiculo1.toString());
    }
    
    public void agregar_vehiculo(){
        System.out.println("Digite 1 si es camion.");
        System.out.println("Digite 2 si es una motocicleta.");
        System.out.println("Digite 3 si es un automovil. ");
        int option = sc.nextInt();
        String type = "";
        System.out.println("Digite La ruta de origen del Vehìculo");
        int origen = sc.nextInt();
        System.out.println("Digite La direccion de giro del Vehìculo");
        int giro = sc.nextInt();
        
        if (origen != 1 && origen != 2 && origen != 3) {
            System.out.println("Opcion no valida");
        }
        switch (option) {
        //registrar vehiculo tipo camion
            case 1:
                type = "C";
                vehiculos.add(new camion(type, origen, giro));
                break;
        //registrar vehiculo tipo motocicleta
            case 2:
                type = "M";
                vehiculos.add(new moto(type, origen, giro));
                break;
        //registrar vehiculo tipo automovil
            case 3:
                type = "A";
                vehiculos.add(new automovil(type, origen, giro));
                break;
            default:
                System.out.println("Opcion no valida, intene nuevamente !!");
                break;
        }
        
    }
    
    public void mostrar_datos(){
        // datos de los camiones
        int camiones = 0;
        int camiones_giro1 = 0;
        int camiones_giro2 = 0;
        int camiones_giro3 =0;
        // datos de los automoviles
        int automoviles = 0;
        int auto_giro1 = 0;
        int auto_giro2 = 0;
        int auto_giro3 =0;
        // datos de las motos
        int motos = 0;
        int moto_giro1 = 0;
        int moto_giro2 = 0;
        int moto_giro3 =0;
        
        
        // se recorre la lista de vehiculos una vez y de ahí se sacan todos los datos
        for (vehiculo vehiculo1 : vehiculos) {
            if (vehiculo1 instanceof camion) {
                camiones++; //C22
                if (vehiculo1.get_origen() != vehiculo1.get_giro()) {
                    if (vehiculo1.get_giro() == 1) {
                        camiones_giro1++;
                    }else if (vehiculo1.get_giro() == 2) {
                        camiones_giro2++;
                    }else{
                        camiones_giro3++;
                    }
                }
            }else if (vehiculo1 instanceof automovil) {
                automoviles++;
                if (vehiculo1.get_origen() != vehiculo1.get_giro()) {
                    if (vehiculo1.get_giro() == 1) {
                        auto_giro1++;
                    }else if (vehiculo1.get_giro() == 2) {
                        auto_giro2++;
                    }else{
                        auto_giro3++;
                    }
                }
            }else{
                motos++;
                if (vehiculo1.get_origen() != vehiculo1.get_giro()) {
                    if (vehiculo1.get_giro() == 1) {
                        moto_giro1++;
                    }else if (vehiculo1.get_giro() == 2) {
                        moto_giro2++;
                    }else{
                        moto_giro3++;
                    }
                }
            }
        }
        System.out.println("######### Tabla de Datos Recolectados #########");
        System.out.println("Total Registro de Vehiculos: " + vehiculos.size());
        int indice = 0;
        for (vehiculo vehiculo1 : vehiculos) { 
            System.out.println(indice + " - " + vehiculo1.toString());
            indice++;
        }
        System.out.println("----------------------------------------------");
        System.out.println("Total de camiones: " + camiones);
        System.out.println("Total de automoviles: " + automoviles);
        System.out.println("Total de motos: " + motos);
        System.out.println("----------------------------------------------");
        System.out.println("Giros realizados hacía la dirección 1: " + (camiones_giro1+auto_giro1+moto_giro1) + " giros");
        System.out.println("Giros realizados hacía la dirección 2: " + (camiones_giro2+auto_giro2+moto_giro2) + " giros");
        System.out.println("Giros realizados hacía la dirección 3: " + (camiones_giro3+auto_giro3+moto_giro3) + " giros");
        System.out.println("----------------------------------------------");
        System.out.println("Los vehiculos tipo Camión realizarón: " + (camiones_giro1+camiones_giro2+camiones_giro3) + " giros");
        System.out.println("Los vehiculos tipo Automovil realizarón: " + (auto_giro1+auto_giro2+auto_giro3) + " giros");
        System.out.println("Los vehiculos tipo Motocicleta realizarón: " + (moto_giro1+moto_giro2+moto_giro3) + " giros");
        System.out.println("----------------------------------------------");
    }
    
    public static void main(String[] args) {
        EstructuraDeDatos ED = new EstructuraDeDatos();
        ED.options();
    }
    
}
