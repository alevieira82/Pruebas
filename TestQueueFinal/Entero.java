package TestQueueFinal;

public class Entero {

    private int contenido;
    private boolean disponible = false;

    public synchronized void put(int content) {

        contenido = content;
        disponible = true;
        // notificar que ya hay dato.
        notifyAll();
    }

    public synchronized int get() {
        while (!disponible) {
            try {
                // espera a que el productor coloque un valor
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;
        // notificar que el valor ha sido consumido
        notifyAll();
        return contenido;
    }
  }