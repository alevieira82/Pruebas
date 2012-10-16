package TestColaSincronizadaConQueue;

import java.util.concurrent.*;

public class TestColaSincronizada {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    { 
	        // crear el objeto compartido utilizado por los subprocesos 
	    	BlockingQueue buffer = new ArrayBlockingQueue(5);
	        Consumidor [] c;
	        Productor  [] p;
	        // crear objetos productor y consumidor 
	      //  Productor productor = new Productor( buffer ); 
	        
	        c = new Consumidor [3];
	        p = new Productor [3];
	        
	        for (int i = 0; i < c.length; i ++)
	        	c[i] = new Consumidor ( buffer);
	        
	        for (int i = 0; i < p.length; i ++)
	        	p[i] = new Productor ( buffer);
	     
	        for (int i = 0; i < p.length; i ++)
	        	p[i].start();
	        
	        //productor.start();  // iniciar subproceso predictor 

	        for (int i = 0; i < c.length; i ++)
	        	c[i].start();
	        
	    } // fin de main 
	} // fin de 
}
