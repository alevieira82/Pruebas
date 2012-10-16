package TestColasSincronizadas;

public class Productor extends Thread{

	private ColaSincronizada buffer;
	
	public Productor (ColaSincronizada buf) {
		buffer = buf;
	}
	
	public void run () 	{
		int valor = 0;
		while (true) {
			buffer.almacenar(valor);
			System.out.println("Productor produjo:" + valor);
			valor ++;
		}
		
	}	
}
