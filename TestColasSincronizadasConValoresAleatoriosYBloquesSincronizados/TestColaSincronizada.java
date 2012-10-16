package TestColasSincronizadasConValoresAleatoriosYBloquesSincronizados;

public class TestColaSincronizada {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    { 
	        // crear el objeto compartido utilizado por los subprocesos 
	        ColaSincronizada buffer = new ColaSincronizada(5);
	        Consumidor [] c;
	        // crear objetos productor y consumidor 
	        Productor productor = new Productor( buffer ); 
	        
	        c = new Consumidor [3];
	        
	        for (int i = 0; i < c.length; i ++)
	        	c[i] = new Consumidor ( buffer);
	        
	        productor.start();  // iniciar subproceso predictor 

	        for (int i = 0; i < c.length; i ++)
	        	c[i].start();
	        
	    } // fin de main 
	} // fin de 
}
