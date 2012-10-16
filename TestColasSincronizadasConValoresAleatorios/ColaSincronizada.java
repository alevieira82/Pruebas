package TestColasSincronizadasConValoresAleatorios;

public class ColaSincronizada {

	private  int [] arrayInts;
	private  int sigEntrada, sigSalida, ocupado, tamano;
	
	public ColaSincronizada (int tam){
	  arrayInts = new int [tam];
	  tamano = tam;
	  ocupado = 0;
	  sigEntrada = 0;
	  sigSalida =0; 
	}

   public synchronized void almacenar ( int valor) {
	   try{
		   System.out.println ("/*------------Contenido del Buffer antes de almacenar------------*/ ");
		   for (int i = 0; i < arrayInts.length; i ++) {
			   System.out.print (arrayInts [i] );
			   }
		   	   System.out.println ("\n");
		   System.out.println ("/*---------------------------------------------------------------*/ ");

		   while (ocupado == tamano) {
			   System.out.println (" Buffer Lleno no puedo poner mas ");
			   wait();};
		   
		   arrayInts [sigEntrada] = valor;
		   sigEntrada = (sigEntrada  + 1) % tamano;
		   ocupado ++ ;
		   System.out.println ("Valor a almacenar en cola  " + valor);
		   System.out.println ("/*------------Contenido del Buffer despues de almacenar------------*/ ");
		 //  notifyAll();
		   for (int i = 0; i < arrayInts.length; i ++) {
			   System.out.print (arrayInts [i] );
			   }
	   	   System.out.println ("\n");
		   System.out.println ("/*---------------------------------------------------------------*/ ");
		   notifyAll();
		   }
	catch (InterruptedException e){}   
   	finally {}
   }
   
   public synchronized int extraer () {
	   System.out.println ("/*------------Contenido del Buffer antes de extraer------------*/ ");
	   for (int i = 0; i < arrayInts.length; i ++) {
		   System.out.print (arrayInts [i] );
		   }
	   System.out.println ("\n");
	   System.out.println ("/*---------------------------------------------------------------*/ ");

	   int valorSacado = 0 ;
	   try {
		   while (ocupado == 0 ){
			   System.out.println (" Buffer vacio no puedo sacar mas ");
			   wait ();}
		   valorSacado = arrayInts [sigSalida];
		   for (int i = 0; i < (arrayInts.length-1); i ++) {
			   arrayInts[i] = arrayInts[i + 1];
		   }
		   //arrayInts [sigSalida] = 0;
		   sigSalida = (sigSalida + 1) % tamano;
		   ocupado--;
		   System.out.println ("Valor a sacar de la cola " + valorSacado);
		   System.out.println ("Contenido del Buffer ");
		   System.out.println ("/*------------Contenido del Buffer despues de extraer------------*/ ");
		 //  notifyAll();
		   for (int i = 0; i < arrayInts.length; i ++) {
			   System.out.print (arrayInts [i] );
			   }
		   System.out.println ("\n");
		   System.out.println ("/*---------------------------------------------------------------*/ ");
		   
		   notifyAll();
		  
	   }
	   catch (InterruptedException e){}   
	   	finally {}
	    return valorSacado;
	   }

}

