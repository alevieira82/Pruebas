package pruthreads2aux;

import java.util.concurrent.*;

public class test {

public static void main(String[] args){
		
		//TreeMapDeColas treeMap = new TreeMapDeColas();
		BlockingQueue <Node> colaProductos = new PriorityBlockingQueue<Node>();
		
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
