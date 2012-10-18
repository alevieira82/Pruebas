package pruthreads2;

import java.util.concurrent.PriorityBlockingQueue;

import pruthreads.Paquete;

public class ChelePriorityBlockingQueue extends PriorityBlockingQueue<Paquete> {
	
	//private PriorityBlockingQueue<Paquete> back = new PriorityBlockingQueue <Paquete>();			
	private static final int MAX_PAQUETES = 100000;

	private Object syncCant = new Object();
	
	public void put(Paquete paq, int iPri) {
		boolean canAdd= false;		
			canAdd = super.size()<MAX_PAQUETES;
				if(canAdd)
				super.put(paq);	//agrega el paquete en la cola
					else
						//Wait y notify? -Mole?
	}
	


	//No deberia hacer que el wait tbn este sincronizado? -Mole?



			/**
			 * <p>Espera que se consuma algún paquete.</p>
			 * 
			 */
			private void waitConsume() {
				synchronized(super.size()) {
					try {
						syncCant.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			/**
			 * <p>Espera que se ingrese algún paquete en las colas de prioridad.</p> 
			 * 
			 */
			public void waitProduce() {
				try {																		
					mapColas.wait();					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/**
			 * <p>Notifica que se consumió un paquete de las colas de prioridad.</p>
			 * 
			 */
			public void notifyConsume() {
				synchronized(syncCant) {
					syncCant.notifyAll();
				}		
			}
			
			/**
			 * <p>Notifica que se produjo e introdujo un paquete en las colas 
			 * de prioridad.</p>
			 * 
			 */
			public void notifyProduce() {
				synchronized(mapColas) {
					mapColas.notifyAll();		
				}
			}
			
			

/*
	
	
	public void put (Paquete paquete) {
		synchronized(syncCant) {
		if (back.size() < MAX_CAPACITY) 
			back.put(paquete);
			//else throw new FuckOffException()
		}*/
	
}

