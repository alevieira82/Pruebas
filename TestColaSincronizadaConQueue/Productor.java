package TestColaSincronizadaConQueue;

import java.util.concurrent.BlockingQueue;

public class Productor extends Thread{

	private final BlockingQueue queue;
	
	public Productor (BlockingQueue q) {
		 queue = q;
	}
	
	public void run () 	{
		System.out.println("Corre Productor " + this.getId());
		for (int i=0; i<= 10; i++) {
		//while (true) {
			NumeroAleatorios na = new NumeroAleatorios(2,7);   
	     	int valor =  na.numeroAleatorio();
		    Paquete paquete =  new Paquete ("A", valor);
	    	System.out.println("Valor aleatorio generado: " + valor);
			try {
				queue.put(paquete);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			System.out.println("Productor produjo y almaceno valor aleatorio:" + valor);
		}
		System.out.println("Termino Productor " + this.getId());
	}
	

}
