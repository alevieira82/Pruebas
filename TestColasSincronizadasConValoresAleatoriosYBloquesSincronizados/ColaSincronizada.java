package TestColasSincronizadasConValoresAleatoriosYBloquesSincronizados;

public class ColaSincronizada {

	private volatile int [] arrayInts;
	private volatile int  sigEntrada, sigSalida, ocupado, tamano;
	
	public ColaSincronizada (int tam){
	  arrayInts = new int [tam];
	  tamano = tam;
	  ocupado = 0;
	  sigEntrada = 1;
	  sigSalida =1; 
	}

   public void almacenar ( int valor) {
	 synchronized (arrayInts)  {
	   while (ocupado < tamano) 
		   arrayInts [sigEntrada] = valor;
		   sigEntrada = (sigEntrada  + 1) % tamano;
		   ocupado ++ ;
		   System.out.println ("Status Cola Valor Almacenado " + valor);}
		   System.out.println ("Contenido del Buffer ");
		   for (int i = 0; i < arrayInts.length; i ++) {
			   System.out.print (arrayInts [i] );
			   
	   }
	 }
   
   public int extraer () {
	   int valorSacado = 0;
	   synchronized (arrayInts)  {
	   while (ocupado != 0 ) {
		   valorSacado = arrayInts [sigSalida];
		   sigSalida = (sigSalida + 1) % tamano;
		   ocupado--;
		   System.out.println ("Status Cola Valor extraido " + valorSacado);
	   }System.out.println ("Contenido del Buffer ");
		   for (int i = 0; i < arrayInts.length; i ++) {
			   System.out.println (arrayInts [i] );
			   }
		   
	     return valorSacado;   }
	}
	   

}

