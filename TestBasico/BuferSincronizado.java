package TestBasico;

	public class BuferSincronizado implements Bufer 
	{ 
	   private int bufer = -1; // compartido por los subprocesos productor y consumidor 
	   private int cuentaBuferOcupado = 0; // cuenta de búferes ocupados 
	   // colocar valor en búfer 
	   public synchronized void establecer( int valor ) 
	   { 
	      // obtener nombre del subproceso que llamó a este método, para mostrarlo en pantalla 
	      String nombre = Thread.currentThread().getName(); 
	      // mientras no haya ubicaciones vacías, colocar subproceso en estado de espera 
	      while ( cuentaBuferOcupado == 1 ) { 
	         // mostrar información del subproceso y del búfer, después esperar 
	         try { 
	            System.err.println( nombre + " trata de escribir." ); 
	            mostrarEstado( "Bufer lleno. " + nombre + " espera." ); 
	            wait(); 
	         } 
	         // si se interrumpió el subproceso en espera, imprimir el rastreo de la pila 
	         catch ( InterruptedException excepcion ) { 
	            excepcion.printStackTrace(); 
	         } 
	      } // fin de instrucción while 
	      bufer = valor; // establecer nuevo valor de bufer 
	      // indicar que el productor no puede almacenar otro valor 
	      // sino hasta que el consumidor recupere el valor actual del búfer 
	      ++cuentaBuferOcupado; 
	      mostrarEstado( nombre + " escribe " + bufer ); 
	      notify(); // indicar al subproceso en espera que entre al estado listo 
	   } // fin del método establecer; se libera el bloqueo en BuferSincronizado 
	   // devolver valor de bufer 
	 
	   public synchronized int obtener() 
	   { 
	      // obtener nombre del subproceso que llamó a este método, para mostrarlo en pantalla 
	      String nombre = Thread.currentThread().getName(); 
	      // mientras no haya datos que leer, colocar subproceso en estado de espera 
	      while ( cuentaBuferOcupado == 0 ) { 
	         // mostrar información del subproceso y del búfer, después esperar 
	         try { 
	            System.err.println( nombre + " trata de leer." ); 
	            mostrarEstado( "Bufer vacio. " + nombre + " espera." ); 
	            wait(); 
	         } 
	         // si se interrumpió el subproceso en espera, imprimir el rastreo de la pila 
	         catch ( InterruptedException excepcion ) { 
	            excepcion.printStackTrace(); 
	         } 
	      } // fin de instrucción while 
	      // indicar que el productor puede almacenar otro valor 
	      // ya que el consumidor acaba de recuperar el valor de bufer 
	      --cuentaBuferOcupado; 
	      mostrarEstado( nombre + " lee " + bufer ); 
	      notify(); // indicar al subproceso en espera que esté listo para ejecutarse 
	      return bufer; 
	   } // fin del método obtener; libera bloqueo en BuferSincronizado 
	   // mostrar la operación actual y el estado del búfer 
	   public void mostrarEstado( String operacion ) 
	   { 
	      StringBuffer lineaSalida = new StringBuffer( operacion ); 
	      lineaSalida.setLength( 40 ); 
	      lineaSalida.append( bufer + "\t\t" + cuentaBuferOcupado ); 
	      System.err.println( lineaSalida ); 
	      System.err.println(); 
	   } 
	} // fin de la clase BuferSincronizado