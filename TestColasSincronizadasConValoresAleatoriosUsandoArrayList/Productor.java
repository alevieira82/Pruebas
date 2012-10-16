package TestColasSincronizadasConValoresAleatoriosUsandoArrayList;

public class Productor extends Thread{

	private  ColaSincronizada buffer;
	
	public Productor (ColaSincronizada buf) {
		buffer = buf;
	}
	
	public void run () 	{
		System.out.println("Corre Productor " + this.getId());
		for (int i=0; i<= 10; i++) {
		//while (true) {
			NumeroAleatorios na = new NumeroAleatorios(2,7);   
	     	int valor =  na.numeroAleatorio();
	    	System.out.println("Valor aleatorio generado: " + valor);
			buffer.almacenar(valor);
			System.out.println("Productor produjo y almaceno valor aleatorio:" + valor);
		}
		System.out.println("Termino Productor " + this.getId());
	}
	

}
