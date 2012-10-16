package TestBasico;

	public class BuferSincronizado implements Bufer 
	{ 
	   private int bufer = -1; // compartido por los subprocesos productor y consumidor 
	   private int cuentaBuferOcupado = 0; // cuenta de b�feres ocupados 
	   // colocar valor en b�fer 
	   public synchronized void establecer( int valor ) 
	   { 
	      // obtener nombre del subproceso que llam� a este m�todo, para mostrarlo en pantalla 
	      String nombre = Thread.currentThread().getName(); 
	      // mientras no haya ubicaciones vac�as, colocar subproceso en estado de espera 
	      while ( cuentaBuferOcupado == 1 ) { 
	         // mostrar informaci�n del subproceso y del b�fer, despu�s esperar 
	         try { 
	            System.err.println( nombre + " trata de escribir." ); 
	            mostrarEstado( "Bufer lleno. " + nombre + " espera." ); 
	            wait(); 
	         } 
	         // si se interrumpi� el subproceso en espera, imprimir el rastreo de la pila 
	         catch ( InterruptedException excepcion ) { 
	            excepcion.printStackTrace(); 
	         } 
	      } // fin de instrucci�n while 
	      bufer = valor; // establecer nuevo valor de bufer 
	      // indicar que el productor no puede almacenar otro valor 
	      // sino hasta que el consumidor recupere el valor actual del b�fer 
	      ++cuentaBuferOcupado; 
	      mostrarEstado( nombre + " escribe " + bufer ); 
	      notify(); // indicar al subproceso en espera que entre al estado listo 
	   } // fin del m�todo establecer; se libera el bloqueo en BuferSincronizado 
	   // devolver valor de bufer 
	 
	   public synchronized int obtener() 
	   { 
	      // obtener nombre del subproceso que llam� a este m�todo, para mostrarlo en pantalla 
	      String nombre = Thread.currentThread().getName(); 
	      // mientras no haya datos que leer, colocar subproceso en estado de espera 
	      while ( cuentaBuferOcupado == 0 ) { 
	         // mostrar informaci�n del subproceso y del b�fer, despu�s esperar 
	         try { 
	            System.err.println( nombre + " trata de leer." ); 
	            mostrarEstado( "Bufer vacio. " + nombre + " espera." ); 
	            wait(); 
	         } 
	         // si se interrumpi� el subproceso en espera, imprimir el rastreo de la pila 
	         catch ( InterruptedException excepcion ) { 
	            excepcion.printStackTrace(); 
	         } 
	      } // fin de instrucci�n while 
	      // indicar que el productor puede almacenar otro valor 
	      // ya que el consumidor acaba de recuperar el valor de bufer 
	      --cuentaBuferOcupado; 
	      mostrarEstado( nombre + " lee " + bufer ); 
	      notify(); // indicar al subproceso en espera que est� listo para ejecutarse 
	      return bufer; 
	   } // fin del m�todo obtener; libera bloqueo en BuferSincronizado 
	   // mostrar la operaci�n actual y el estado del b�fer 
	   public void mostrarEstado( String operacion ) 
	   { 
	      StringBuffer lineaSalida = new StringBuffer( operacion ); 
	      lineaSalida.setLength( 40 ); 
	      lineaSalida.append( bufer + "\t\t" + cuentaBuferOcupado ); 
	      System.err.println( lineaSalida ); 
	      System.err.println(); 
	   } 
	} // fin de la clase BuferSincronizado