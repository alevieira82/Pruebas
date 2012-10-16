package TestColasSincronizadasConValoresAleatorios;

public class Consumidor extends Thread {

 	private  ColaSincronizada buffer;
 	
 	public Consumidor (ColaSincronizada buf) {
 		buffer = buf;
 	}
 	
 	public void run () {
 		System.out.println("Corre Consumidor " + this.getId());
 		int datoExtraido;
 		for (int i=0; i<= 10; i++) {
 		//while (true) {
 		datoExtraido = buffer.extraer();
 		if (datoExtraido  == 2) {
 			System.out.println("Consumidor " + this.getId() + " extrajo*******************************: " + datoExtraido);
 		}
 		else {
 			System.out.println("Consumidor " + this.getId() + " no encontro valores 2");
 		}
 		}
 		System.out.println("Termino Consumidor " + this.getId());
 	}
}
