package TestBasico;
public class BuferNoSincronizado implements Bufer { 
   private int bufer = -1; // compartido por los subprocesos productor y consumidor 
   // colocar valor en bufer 
   public void establecer( int valor ) 
   { 
      System.err.println( Thread.currentThread().getName() + 
         " escribe " + valor ); 
  
      bufer = valor; 
   } 
   // devolver valor de bufer 
   public int obtener() 
   { 
      System.err.println( Thread.currentThread().getName() + 
         " lee " + bufer ); 
  
      return bufer; 
   } 
} // fin de la clase BuferNoSincronizado

