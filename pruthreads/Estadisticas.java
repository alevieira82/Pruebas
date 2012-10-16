
package pruthreads;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>Contiene un treeMap con la dupla (prioridad, cantidad) 
 * que lleva las estadisticas de la cantidad de paquetes de con la misma 
 * prioridad que se consumieron.</p>
 * <p>Es thread safe</p> 
 * 
 *
 *
 */
public class Estadisticas {
	
	private TreeMap tm = new TreeMap();
	private int iCounter = 0;
	private TreeMapDeColas mapColas;
	

	
	public Estadisticas(TreeMapDeColas mapColas) {
		super();
		this.mapColas = mapColas;
	}
	
	/**
	 * <p>Incrementa en el treeMap la cantidad de paquetes 
	 * consumidos de la prioridad (<code>iPrioridad</code>) pasada como parametro</p>
	 * 
	 * @param iPriority Prioridad del nuevo paquete consumido
	 */
	public void incrementPriority(int iPriority) {
		synchronized (this) {			
			Integer pri= new Integer(iPriority);
			Integer cant;
			
			if (tm.get(pri) == null) {
				cant = new Integer(0);
			}else {
				cant = (Integer) tm.get(pri);
			}			
			cant= new Integer(cant.intValue()+1);
			tm.put (pri, cant);			
			
			if((++iCounter % 100) == 0) {
				//synchronized(this) {
					System.out.println("Las estadisticas ahora son:\r"+this);
				//}
				System.out.println("La cantidad total de paquetes en las colas es: "+mapColas.getCantPaq());
			}
		}
	}
	
	public String toString() {
		String desc="";
		Iterator it= tm.entrySet().iterator();
		while(it.hasNext()) {	//recorre el treeMap
			Map.Entry entry= (Map.Entry)it.next();
			desc+= "(Prioridad ="+entry.getKey()+", Cantidad = "+entry.getValue()+")\t";
		}	
		return desc;
	}

}
