package TestColaSincronizadaConQueue;

import java.util.concurrent.BlockingQueue;

public class Consumidor extends Thread {

	private final BlockingQueue queue;
 	
 	public Consumidor (BlockingQueue q) {
 		queue = q;
 	}
 	
 	public void run () {
 		System.out.println("Corre Consumidor " + this.getId());
 		Paquete datoExtraido;
 		for (int i=0; i<= 10; i++) {
 		//while (true) {
 	//	datoExtraido = buffer.extraer();
 		try {
			datoExtraido = (Paquete) queue.take();
			if (Integer.valueOf(datoExtraido.getValor()) == 2) {
	 			System.out.println("Consumidor " + this.getId() + " extrajo*******************************: " + datoExtraido);
	 		}
	 		else {
	 			System.out.println("Consumidor " + this.getId() + " no encontro valores 2");
	 		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		}
 		System.out.println("Termino Consumidor " + this.getId());
 		
 		
 	}
}
