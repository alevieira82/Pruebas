package TestQueueFinal;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {

    private final BlockingQueue<Integer> queue;
    private Entero entero;

    public Consumidor(BlockingQueue<Integer> queue, Entero entero) {
        this.queue = queue;
        this.entero = entero;
    }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void consume(Integer x) {
    if(entero.get() < x.intValue()){
        System.out.println("Consumidor. valor : " + x.toString());
        }
        
    }
} 