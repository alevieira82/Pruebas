package TestBasico;
//	 El método run de Productor controla un subproceso que 
	// almacena los valores de 1 a 5 en ubicacionCompartida. 
	public class Productor extends Thread { 
	   private Bufer ubicacionCompartida; // referencia al objeto compartido 
	   // constructor 
	   public Productor( Bufer compartido ) 
	   { 
	       super( "Productor" ); 
	       ubicacionCompartida = compartido; 
	   } 
	   // almacenar valores de 1 a 4 en ubicacionCompartida 
	   public void run() 
	   { 
	      for ( int cuenta = 1; cuenta <= 4; cuenta++ ) { 
	         // estar inactivo de 0 a 3 segundos y luego colocar valor en Bufer 
	         try { 
	            Thread.sleep( ( int ) ( Math.random() * 3001 ) ); 
	            ubicacionCompartida.establecer( cuenta ); 
	         } 
	         // si se interrumpió el subproceso inactivo, imprimir rastreo de pila 
	         catch ( InterruptedException excepcion ) { 
	            excepcion.printStackTrace(); 
	         } 
	      } // fin de instrucción for 
	      System.err.println( getName() + " termino de producir." + 
	         "\nTerminando " + getName() + "."); 
	   } // fin del método run 
	} // fin de la clase Productor