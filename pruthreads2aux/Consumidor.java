
package pruthreads2aux;

import java.util.concurrent.*;



/**
 * <p>Las instancias de está clase consumen los paquetes del treeMap
 * de mayor prioridad y lleva una estadistica de los paquetes de cada 
 * prioridad que se van consumiendo</p>
 * 
 * @author iconstenla
 */
public class Consumidor implements Runnable {
	
	private String nombre;
	private BlockingQueue <Node> colaProductos;
	private Paquete paq;
	private int delay;	

	
	/**
	 * @param nombre Nombre del consumidor
	 * @param delay Tiempo de sleep() entre paquetes consumidos 
	 * @param treeMap TreeMap del cual va a consumir paquetes
	 */
	public Consumidor( String nombre, int delay, BlockingQueue colaProductos){
		super();
		this.nombre = nombre;
		this.colaProductos = colaProductos;
		this.delay = delay;
		
	}
	
	
	public void run(){
		while(true){			
//			paq = treeMap.desencolar();
//			treeMap.incrementarEst(paq.getPrioridad());	
			paq = colaProductos.take();
			try {
				Thread.sleep(delay);
			}catch( InterruptedException e) {
				e.printStackTrace();				
			}
		}	
	}
	
	public String toString(){
		String desc = "Soy el consumidor: ["+nombre+"] y tengo el paquete: ["+paq+"]";
		return desc;
	}

}
