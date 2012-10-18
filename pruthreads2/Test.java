package pruthreads2;

import java.util.concurrent.*;

public class Test {

public static void main(String[] args){
		
		//TreeMapDeColas treeMap = new TreeMapDeColas();
		BlockingQueue<Paquete> colaProductos = new PriorityBlockingQueue<Paquete>();
		
		int cantProductores = 300;
		int cantConsumidores = 5;
		
		Runnable[] p = 
			new Runnable[cantProductores];
		Runnable[] c = 
			new Runnable[cantConsumidores];
		
		//crea los productores
		for (int i = 0; i < p.length; i++) {
			p[i] = new Productor("P"+i, 5+i, colaProductos); 
		}
		//crea los consumidores
		for (int i = 0; i < c.length; i++) {
			c[i] = new Consumidor("C"+i, 5+i, colaProductos); 
		}				
		//corre los consumidores
		for (int i = 0; i < c.length; i++)
			new Thread(c[i]).start();
		//corre los productores
		for (int i = 0; i < p.length; i++)
			new Thread(p[i]).start();
		
	}
}
