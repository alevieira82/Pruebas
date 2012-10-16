package TestColasSincronizadasConValoresAleatoriosYBloquesSincronizados;


public class Productor extends Thread{

	private ColaSincronizada buffer;
	
	public Productor (ColaSincronizada buf) {
		buffer = buf;
	}
	
	public void run () 	{
		while (true) {
    		NumeroAleatorios na = new NumeroAleatorios(2,7);   
         	int valor =  na.numeroAleatorio();
        	System.out.println("Valor aleatorio generado: " + valor);
     		buffer.almacenar(valor);
			System.out.println("Productor almaceno valor aleatorio: " + valor);
		}
		
	
		}
	

}
