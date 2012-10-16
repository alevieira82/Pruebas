package TestColasSincronizadasConValoresAleatoriosUsandoArrayList;

public class Paquete {

	public Paquete(String id, int valor) {
		super();
		this.id = id;
		this.valor = valor;
	}
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	private int valor;
	
	public String toString(){
	return String.valueOf(valor);
	 }
}
