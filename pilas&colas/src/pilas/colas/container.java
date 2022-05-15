
package pilas.colas;


public class container {
    
    private Integer serial;
    private container next;

    public void container(){
        this.serial = 0;
        this.next = null;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public container getNext() {
        return next;
    }

    public void setNext(container next) {
        this.next = next;
    }
    
}
