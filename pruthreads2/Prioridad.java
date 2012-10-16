package pruthreads2;

public class Prioridad implements Comparable<> {
    
    public int numeroPrioridad;
    public Paquete paquete;
    
    public int compareTo(Chele other) {
            return numeroPrioridad - other.numeroPrioridad;
            
    }

}