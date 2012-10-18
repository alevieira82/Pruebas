package pruthreads2aux;

public class Node implements Comparable<Node> {
    
    private int priority;
    private Paquete paquete;
    
    
    public Node(int priority, Paquete paquete) {
            this.priority = priority;
            this.paquete = paquete;
    }

    public int getPriority() {
            return priority;
    }

    public Paquete getPaquete() {
            return paquete;
    }

    public int compareTo(Node o) {
            return priority - o.getPriority();
    }

}