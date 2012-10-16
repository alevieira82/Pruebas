package TestColasSincronizadasConValoresAleatoriosYBloquesSincronizados;

public class Consumidor extends Thread {

 	private ColaSincronizada buffer;
 	
 	public Consumidor (ColaSincronizada buf) {
 		buffer = buf;
 	}
 	
 	public void run () {
 		int datoExtraido;
 		while (true) {
 			datoExtraido = buffer.extraer();
 		if (datoExtraido  == 2) {
 			System.out.println("*******Consumidor " + this.getId() + " extrajo: " + datoExtraido);
 		}
 		else {
 			System.out.println("++++++Consumidor " + this.getId() + " no encontro valores 2");
 		}
 		}
 	}
 	
}
