
package estructuradedatos;

public class vehiculo {
    private String tipo_vehiculo;
    private int direccion_origen;
    private int giro ;
    
    public vehiculo(String tipe, int origen, int giro) {
        this.tipo_vehiculo = tipe;
        this.direccion_origen = origen;
        this.giro = giro;
    }
    
    public String get_tipo_vehiculo(){
        return this.tipo_vehiculo;
    }
    
    public int get_origen(){
        return this.direccion_origen;
    }
    
    public int get_giro(){
        return this.giro;
    }
    
    @Override
    public String toString() {
        String word = tipo_vehiculo + direccion_origen + giro;
        return word;
    }
 
}
