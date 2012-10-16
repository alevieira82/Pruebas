package pruthreads2;

import java.util.Random;

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
	private TreeMapDeColas treeMap;
	private int delay;
	private static Random ran = new Random(); //Semilla
	
	/**  
	 * @param nombre Nombre del productor
	 * @param delay Tiempo que permanece en sleep() entre produccion de paquetes
	 * @param treeMap treeMap de colas en el que ingresa los paquetes este productor
	 */
	public Productor(String nombre, int delay, TreeMapDeColas treeMap){
		super();
		this.nombre = nombre;
		this.delay = delay;
		this.treeMap=treeMap;
	}
	
	
	/**
	 * @return Devuelve un número entero al azar entre 1 y <code>MAX_PRIORIDADES</code>
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
			
			treeMap.encolar(prod, prod.getPrioridad());
			
			try{
				Thread.sleep(delay);
			}catch( InterruptedException e) {
				e.printStackTrace();
			}
		}				
	}
}
