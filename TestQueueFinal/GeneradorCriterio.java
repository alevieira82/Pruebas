package TestQueueFinal;

public class GeneradorCriterio implements Runnable {

	    private Entero entero;
	    private int valor;

	    public GeneradorCriterio(Entero entero) {
	        this.entero = entero;
	    }

	    @Override
	    public void run() {
	        while (true) {
	            try {
	                entero.put(generar());
	                Thread.sleep(3000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    
	    private int generar() {
	        valor = (int) (Math.random() * 100);
	        System.out.println("Criterio. El valor del criterio es: " + valor);
	        return valor;
	    }

	} 


