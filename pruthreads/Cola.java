package pruthreads;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * <p>Esta clase contiene la cola con prioridad. El acceso a la cola es thread safe</p>* 
 * 
 *
 *
 */
public class Cola {

	
	private LinkedList colaConPrioridad = new LinkedList();
	
//	private boolean lockHead = true;
//	private boolean lockTail = false;
//	
//	
//	private Object syncHead = new Object();
//	private Object syncTail = new Object();
//	private Object syncTailHead = new Object();
	private int prioridad;
	
	/**
	 * @param prioridad prioridad asignada a esta cola
	 */
	public Cola(int prioridad) {
		super();		
		this.prioridad=prioridad;
	}	
	
	/**
	 * <p>Agrega un paquete a la cola y es thread safe</p>
	 * 
	 * @param paq Paquete que se desea agregar a la cola
	 */
	public void agregar(Paquete paq) {
		synchronized (this) {						
			colaConPrioridad.add(paq);
			if (colaConPrioridad.size() == 1)
				this.notifyAll();
		}
	}
	
	/**
	 * <p>Quita y devuelve el primer elemento de la cola 
	 * o null si no hay elementos en la cola y es thread safe.</p>
	 * 
	 * @return Devuelve null o el primer elemento de la cola
	 */
	public Paquete sacarPrimerElemento() {
		synchronized(this) {						
			Paquete paq;
			try {
				paq = (Paquete) colaConPrioridad.removeFirst();
			} catch (NoSuchElementException e) {
				return null;
			}
			this.notifyAll();			
			return paq;
		}
	}
}
