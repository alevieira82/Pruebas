
package pruthreads;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * <p>Las instancias de esta clase contienen un treeMap de las duplas (prioridad, cola).
 * A medidad que se ingresan paquetes con distintas prioridades se agregan las referencias 
 * a las colas de prioridades al treeMap.</p>
 * <p>La mayor prioridad es 0 y la cantidad se parametriza desde <code>MAX_PAQUETES</code></p> 
 * <p>El acceso al treeMap y cantPaq es thread safe.</p>
 * 
 * 
 * 
 */
public class TreeMapDeColas {
	
	/**
	 * Comment for <code>MAX_PAQUETES</code>
	 * <p>Cantidad máxima de paquetes que en total en todas las colas de prioridades.</p>
	 */
	private static final int MAX_PAQUETES = 100000;
	
	/**
	 * Comment for <code>mapColas</code>
	 * <p>Este es el treeMap que contiene las duplas(prioridad, cola). Este objeto se usa como monitor
	 * para el accesos al mismo.</p>
	 */
	private TreeMap  mapColas = new TreeMap();
	
	
	/**
	 * Comment for <code>est</code>
	 * <p>Contiene estadisticas de las colas de prioridades. Es thread safe</p>
	 */
	private Estadisticas est = new Estadisticas (this);
	
	/**
	 * Comment for <code>syncCant</code>
	 * <p>Monitor para la sincronizacion de la variable cantPaq.</p>
	 */
	private Object syncCant = new Object();
	
	
	/**
	 * Comment for <code>cantPaq</code>
	 * <p>Cantidad de paquetes que se encuentran actualmente en toda 
	 * las colas de prioridad.</p>
	 * <p>Los accesos a esta variable son thread safe</p>
	 */
	private int cantPaq = 0;
	

	/**
	 * <p>Ingresa un paquete en la cola correspondiente a la prioridad (<code>iPri</code> 
	 * (si no existe la crea) y en caso que se alcance el limite de 
	 * paquetes total entra en wait().</p>  
	 * 
	 * @param paq El paquete a ingresar en la cola
	 * @param iPri Prioridad del paquete a ingresar en la cola
	 */	
	public void encolar(Paquete paq, int iPri) {
		boolean canAdd= false;		
		while(true) {
			synchronized(mapColas) {
				synchronized(syncCant) {
					canAdd = getCantPaq()<MAX_PAQUETES;
					if(canAdd)
						setCantPaq(getCantPaq()+1);	//incrementa cantidad de paquetes
				}
				if(canAdd)  
					getCola(iPri).agregar(paq);	//agrega el paquete en la cola
				System.out.print("puse");//Notifica paquete ingresado 
			}
			if(canAdd)
				break;			
			waitConsume();		//Se alcanzó la cantidad máxima de paquetes
		}		

		notifyProduce();	

	}
	
	/**
	 * <p>Sincroniza el acceso a la treeMap para sacar un elemento
	 * llamando a <code>sacarPrimero()</code>. En caso que no haya ningun elemento
	 *  en las colas entre en wait().</p> 
	 * @return Devuelve el paquete desencolado
	 */
	public Paquete desencolar() {
		Paquete p = null;
		synchronized(mapColas) {
			while(p==null){
				p = sacarPrimero(); 
				if(p!=null)	
					break;
				waitProduce();	//No hay paquetes en ninguna cola	
			}
		}
		System.out.print("Saque");
		notifyConsume();	//Notifica paquete consumido
	
		return p;
	}
	
	
	/**  
	 * <p>Devuelve el primer paquete de la cola con mayor prioridad. 
	 * Si no hay paquetes en ninguna cola devuelve null</p>
	 * 
	 * @return Devuelve null o el primer paquete de la cola más prioritaria
	 */
	public Paquete sacarPrimero(){
		Paquete p = null;
		Iterator it= mapColas.entrySet().iterator();
		while(it.hasNext()) {	//recorre el treeMap
			Map.Entry entry= (Map.Entry)it.next();
			Cola cola=(Cola)entry.getValue();
			p = cola.sacarPrimerElemento();
			if(p!= null) {
				synchronized(syncCant) {		
					setCantPaq(getCantPaq() - 1);	//decrementa la cantidad total de paquetes
				}					
				break;
			}
		}
		return p;
	}
	
	/**
	 * <p>Devuelve la cola en la que se almacenan los paquetes de 
	 * prioridad <code>iPrioridad</code></p>
	 * 
	 * @param iPrioridad Prioridad de la cola buscada
	 * @return La cola buscada
	 */
	private Cola getCola(int iPrioridad) {
		Integer key= new Integer(iPrioridad);
		Cola cola= (Cola)mapColas.get(key);
		if(cola == null) {	//La cola de prioridades todavia no existe
			cola = new Cola(iPrioridad);
			mapColas.put(key, cola);	//ingresa nueva cola de prioridades
		}
		return cola;
	}
	
	
	/**
	 * <p>Espera que se consuma algún paquete.</p>
	 * 
	 */
	private void waitConsume() {
		synchronized(syncCant) {
			try {
				syncCant.wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>Espera que se ingrese algún paquete en las colas de prioridad.</p> 
	 * 
	 */
	public void waitProduce() {
		try {																		
			mapColas.wait();					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Notifica que se consumió un paquete de las colas de prioridad.</p>
	 * 
	 */
	public void notifyConsume() {
		synchronized(syncCant) {
			syncCant.notifyAll();
		}		
	}
	
	/**
	 * <p>Notifica que se produjo e introdujo un paquete en las colas 
	 * de prioridad.</p>
	 * 
	 */
	public void notifyProduce() {
		synchronized(mapColas) {
			mapColas.notifyAll();		
		}
	}
	
	/**
	 * 
	 * <p>Incrementa la cantidad de paquetes consumidos de cada prioridad. Es thread safe.</p>
	 * 
	 * @param iPrioridad prioridad consumida
	 */
	public void incrementarEst(int iPrioridad) {
		synchronized(est) {
			est.incrementPriority(iPrioridad);
		}
	}
	
	/**
	 * @param cantPaq The cantPaq to set.
	 */
	private  void setCantPaq(int cantPaq) {
		this.cantPaq = cantPaq;
	}

	/**
	 * @return Returns the cantPaq.
	 */
	public  int getCantPaq() {
		synchronized(syncCant) {
			return cantPaq;
		}
	}		

	public static void main(String[] args){
		
		TreeMapDeColas treeMap = new TreeMapDeColas();
				
		int cantProductores = 300;
		int cantConsumidores = 5;
		
		Runnable[] p = 
			new Runnable[cantProductores];
		Runnable[] c = 
			new Runnable[cantConsumidores];
		
		//crea los productores
		for (int i = 0; i < p.length; i++) {
			p[i] = new Productor("P"+i, 5+i, treeMap); 
		}
		//crea los consumidores
		for (int i = 0; i < c.length; i++) {
			c[i] = new Consumidor("C"+i, 5+i, treeMap); 
		}				
		//corre los consumidores
		for (int i = 0; i < c.length; i++)
			new Thread(c[i]).start();
		//corre los productores
		for (int i = 0; i < p.length; i++)
			new Thread(p[i]).start();
		
	}
}