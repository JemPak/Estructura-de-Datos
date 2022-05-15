
package pilas.colas;

public class carro {
    private Integer serial;
    private carro next;

    public carro() {
        serial = 0;
        next = null;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public void setNext(carro next) {
        this.next = next;
    }

    public carro getNext() {
        return next;
    }
}
