
package puerto;

public class autos {
     //Declaracion de atributos
 private Integer serial;
 private autos next;

    public autos() {
        serial = 0;
        next = null;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public void setNext(autos next) {
        this.next = next;
    }

    public autos getNext() {
        return next;
    }
    
    
}
