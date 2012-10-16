package TestQueueFinal;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
	
	private final BlockingQueue<Integer> queue;
	    int valor = 0;

	    public Productor(BlockingQueue<Integer> queue) {
	        this.queue = queue;
	    }

	    @Override
	    public void run() {
	        try {
	            while (true) {
	                queue.put(produce());
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    private Integer produce() {
	        valor++;
	        System.out.println("Productor. valor : " + valor);
	        try {
	            Thread.sleep((int) (Math.random() * 700));
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return Integer.valueOf(valor);
	    }
	} 


