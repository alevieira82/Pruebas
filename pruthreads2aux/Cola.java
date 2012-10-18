package pruthreads2aux;
//package pruthreads2;
//
//import java.util.LinkedList;
//import java.util.NoSuchElementException;
//import java.util.concurrent.*;
//
///**
// * <p>Esta clase contiene la cola con prioridad. El acceso a la cola es thread safe</p>* 
// * 
// * @author iconstenla
// *
// */
//public class Cola {
//	
//	//private LinkedList colaConPrioridad = new LinkedList();
//   BlockingQueue<Paquete> colaConPrioridad = new LinkedBlockingQueue<Paquete>();
//	
////	private boolean lockHead = true;
////	private boolean lockTail = false;
////	
////	
////	private Object syncHead = new Object();
////	private Object syncTail = new Object();
////	private Object syncTailHead = new Object();
//	private int prioridad;
//	
//	/**
//	 * @param prioridad prioridad asignada a esta cola
//	 */
//	public Cola(int prioridad) {
//		super();		
//		this.prioridad=prioridad;
//	}	
//	
////	
////	public void agregar(Paquete paq) {
////		try {
////			colaConPrioridad.put(paq);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}		
////	}
////	
////	public Paquete sacarPrimerElemento() {
////		Paquete paq = null;
////		try {
////			paq = (Paquete) colaConPrioridad.take();
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} finally {
////			return paq;
////		}
////    }
////	/**
////	 * <p>Agrega un paquete a la cola y es thread safe</p>
////	 * 
////	 * @param paq Paquete que se desea agregar a la cola
////	 */
////	public void agregar(Paquete paq) {
////		synchronized (this) {						
////		
////				colaConPrioridad.put(paq);
////		
////				this.notifyAll();
////		}
////	}
////	
////	/**
////	 * <p>Quita y devuelve el primer elemento de la cola 
////	 * o null si no hay elementos en la cola y es thread safe.</p>
////	 * 
////	 * @return Devuelve null o el primer elemento de la cola
////	 */
////	public Paquete sacarPrimerElemento() {
////		synchronized(this) {						
////			Paquete paq = null;
////			try {
////				try {
////					paq = (Paquete) colaConPrioridad.take();
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////			} catch (NoSuchElementException e) {
////				return null;
////			}
////			this.notifyAll();			
////			return paq;
////		}
////	}
//}
