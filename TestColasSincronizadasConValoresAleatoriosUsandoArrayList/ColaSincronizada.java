package TestColasSincronizadasConValoresAleatoriosUsandoArrayList;

import java.util.ArrayList;
import java.util.List;

public class ColaSincronizada {

	//private volatile int [] arrayInts;
	private  List <Paquete> arrayList;
	private  int sigEntrada, sigSalida, ocupado, tamano;
	
	public ColaSincronizada (int tam){
	  //arrayInts = new int [tam];
	  arrayList = new ArrayList <Paquete> (tam);
	  tamano = tam;
	  ocupado = 0;
	  sigEntrada = 0;
	  sigSalida =0; 
	}

   public synchronized void almacenar ( int valor) {
	   try{
		   System.out.println ("/*------------Contenido del Buffer antes de almacenar------------*/ ");
		   for (int i = 0; i < arrayList.size(); i ++) {
			   System.out.print (arrayList.get(i).toString() );
			   }
		   	   System.out.println ("\n");
		   System.out.println ("/*---------------------------------------------------------------*/ ");

		   while (ocupado == tamano) {
			   System.out.println (" Buffer Lleno no puedo poner mas ");
			   wait();};
		    Paquete paquete =  new Paquete ("A", valor);
			//arrayList.add(sigEntrada, paquete);
		    arrayList.add(paquete);
		   //sigEntrada = (sigEntrada  + 1) % tamano;
		   ocupado ++ ;
		   System.out.println ("Valor a almacenar en cola  " + valor);
		   System.out.println ("/*------------Contenido del Buffer despues de almacenar------------*/ ");
		 //  notifyAll();
		   for (int i = 0; i < arrayList.size(); i ++) {
			   System.out.print (arrayList.get(i).toString() );
			   }
	   	   System.out.println ("\n");
		   System.out.println ("/*---------------------------------------------------------------*/ ");
		   notifyAll();
		   }
	catch (InterruptedException e){
		System.out.println ("Almacenamiento Interrumpida");
	}   
   	finally {}
   }
   
   public synchronized Paquete extraer () {
	   System.out.println ("/*------------Contenido del Buffer antes de extraer------------*/ ");
	   for (int i = 0; i < arrayList.size(); i ++) {
		   System.out.print (arrayList.get(i).toString() );
		   }
	   System.out.println ("\n");
	   System.out.println ("/*---------------------------------------------------------------*/ ");
         //  Si hay gente para almacenar me bloqueao
	   Paquete valorSacado = null ;
	   try {
		   while (ocupado == 0 ){
			   System.out.println (" Buffer vacio no puedo sacar mas ");
			   wait ();}
	//	   valorSacado = arrayList.get(sigSalida);
		  // for (int i = 0; i < (arrayList.size()-1); i ++) {
			 //  Paquete aux = arrayList.get(i+1);
		   int pos = arrayList.size()-1;
		   valorSacado = arrayList.remove(pos); //add(i,aux); // =  arrayList.get(i+1);
		   //}
		   //arrayInts [sigSalida] = 0;
		   //sigSalida = (sigSalida + 1) % tamano;
		   ocupado--;
		   System.out.println ("Valor a sacar de la cola " + valorSacado);
		   System.out.println ("Contenido del Buffer ");
		   System.out.println ("/*------------Contenido del Buffer despues de extraer------------*/ ");
		 //  notifyAll();
		   for (int i = 0; i < arrayList.size(); i ++) {
			   System.out.print (arrayList.get(i).toString() );
			   }
		   System.out.println ("\n");
		   System.out.println ("/*---------------------------------------------------------------*/ ");
		   
		   notifyAll();
		  
	   }
	   catch (InterruptedException e){
		   System.out.println ("Extraccion Interrumpida");
	   }   
	   	finally {}
	    return valorSacado;
	   }

}

