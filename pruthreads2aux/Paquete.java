
package pruthreads2aux;

/**
 * <p>Las instancias de esta clase son los paquetes que se ingresan en 
 * la cola de prioridades</p>
 * 
 * @author iconstenla
 */
public class Paquete {
	private String productor;
	private int prioridad;
	
	/**
	 * @param productor Nombre del productor del paquete 
	 * @param prioridad Prioridad del paquete
	 */
	public Paquete(String productor, int prioridad){
		super();
		this.productor = productor;
		this.prioridad = prioridad;
	}
	
	public String toString(){
		String desc = "Producto de: ["+productor+"] con prioridad: ["+ prioridad+"]"; 
		return desc;	}
	

	/**
	 * @return Returns the prioridad.
	 */
	public int getPrioridad() {
		return prioridad;
	}
	/**
	 * @param prioridad The prioridad to set.
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
}
