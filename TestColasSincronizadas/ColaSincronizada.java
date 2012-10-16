package TestColasSincronizadas;

public class ColaSincronizada {

	private int [] arrayInts;
	private int sigEntrada, sigSalida, ocupado, tamano;
	
	public ColaSincronizada (int tam){
	  arrayInts = new int [tam];
	  tamano = tam;
	  ocupado = 0;
	  sigEntrada = 1;
	  sigSalida =1; 
	}

   public synchronized void almacenar ( int valor) {
	   try{
		   while (ocupado == tamano) wait();
		   arrayInts [sigEntrada] = valor;
		   sigEntrada = (sigEntrada  + 1) % tamano;
		   ocupado ++ ;
		   notify();
		   }
	catch (InterruptedException e){}   
   	finally {}
   }
   
   public synchronized int extraer () {
	   int valorSacado = 0 ;
	   try {
		   while (ocupado == 0 ) wait ();
		   valorSacado = arrayInts [sigSalida];
		   sigSalida = (sigSalida + 1) % tamano;
		   ocupado--;
		   notify();
	   }
	   catch (InterruptedException e){}   
	   	finally {}
	    return valorSacado;
	   }

}

