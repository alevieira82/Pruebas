package TestQueueFinal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.*;


public class testMain {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//        BlockingQueue<Integer> colaProductos = new LinkedBlockingQueue<Integer>();
//        Entero entero = new Entero();
//        Productor p = new Productor(colaProductos);
//        Consumidor c1 = new Consumidor(colaProductos, entero);
//        GeneradorCriterio g = new GeneradorCriterio(entero);
//        new Thread(p).start();
//        new Thread(c1).start();
//        new Thread(g).start();
//      }
//	
	public static void main(String[] args) {
        BlockingQueue<Integer> colaProductos = new ArrayBlockingQueue<Integer>(5);
        Entero entero = new Entero();
//        Productor p = new Productor(colaProductos);
//        Consumidor c1 = new Consumidor(colaProductos, entero);
        GeneradorCriterio g = new GeneradorCriterio(entero);
//        new Thread(p).start();
//        new Thread(c1).start();
        new Thread(g).start();
        
        Consumidor [] c;
        Productor  [] p;

        c = new Consumidor [3];
        p = new Productor [3];
        
        for (int i = 0; i < c.length; i ++)
        	c[i] = new Consumidor ( colaProductos, entero);
        
        for (int i = 0; i < p.length; i ++)
        	p[i] = new Productor ( colaProductos);
     
        for (int i = 0; i < p.length; i ++)
        	new Thread(p[i]).start();
        
        for (int i = 0; i < c.length; i ++)
        	new Thread(c[i]).start();
        
      }
}
