package pruthreads2;

import java.util.Random;
import java.util.concurrent.*;

/**
 * <p>Las instancias de esta clase producen paquetes con 
 * prioridades aleatorias y los ingresan en las colas de prioridades. 
 * Si el limite de paquetes (<code>MAX_PAQUETES</code>) es alcanzado entran en wait()</p>
 * 
 * 
 * @author iconstenla
 * 
 */
public class Productor implements Runnable{
	
	public static final int MAX_PRIORIDADES = 10;
	private String nombre;
	private BlockingQueue<Paquete> colaProductos;
	private int delay;
	private static Random ran = new Random(); //Semilla
	
	/**  
	 * @param nombre Nombre del productor
	 * @param delay Tiempo que permanece en sleep() entre produccion de paquetes
	 * @param treeMap treeMap de colas en el que ingresa los paquetes este productor
	 */
	public Productor(String nombre, int delay, BlockingQueue<Paquete> colaProductos){
		this.nombre = nombre;
		this.delay = delay;
		this.colaProductos=colaProductos;
	}
	
	
	/**
	 * @return Devuelve un n�mero entero al azar entre 1 y <code>MAX_PRIORIDADES</code>
	 */
	public int getRandomNumber() {
		synchronized(ran) {
			return ran.nextInt(MAX_PRIORIDADES);
		}
	}
	
	public void run(){
		
		int iRandom;
				
		while(true){
			iRandom = getRandomNumber();
			Paquete prod = new Paquete(nombre, iRandom);					
			
			try {
				colaProductos.put(prod);
				Thread.sleep(delay);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}				
	}
}
