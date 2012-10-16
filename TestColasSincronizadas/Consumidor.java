package TestColasSincronizadas;

public class Consumidor extends Thread {

 	private ColaSincronizada buffer;
 	
 	public Consumidor (ColaSincronizada buf) {
 		buffer = buf;
 	}
 	
 	public void run () {
 		int datoExtraido = 0;
 		while (datoExtraido < 10) {
 		datoExtraido = buffer.extraer();
 		System.out.println("Consumidor " + this.getId() + " extrajo: " + datoExtraido);
 		
 		}
 	//	System.out.println("Fin consumido " + this.getId() );
 		
 	}
}
