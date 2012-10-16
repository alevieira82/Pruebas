package TestBasico;

public class TestBuferCompartido { 
    public static void main( String [] args ) 
    { 
        // crear el objeto compartido utilizado por los subprocesos 
        //Bufer ubicacionCompartida = new BuferNoSincronizado(); 
        Bufer ubicacionCompartida = new BuferSincronizado(); 
        
        // crear objetos productor y consumidor 
        Productor productor = new Productor( ubicacionCompartida ); 
        Consumidor consumidor = new Consumidor( ubicacionCompartida ); 
        productor.start();  // iniciar subproceso productor 
        consumidor.start();  // iniciar subproceso consumidor 
    } // fin de main 
} // fin de la clase PruebaBuferCompartido