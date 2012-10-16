package TestColasSincronizadasConValoresAleatoriosYBloquesSincronizados;

import java.util.ArrayList;

public class NumeroAleatorios {
    private int valorInicial;
    private int valorFinal;
    private ArrayList listaNumero;

    public NumeroAleatorios(int valorInicial, int valorFinal){
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        listaNumero = new ArrayList();
    }
    
    public int numeroAleatorio(){
        return (int)(Math.random()*(valorFinal-valorInicial+1)+valorInicial);
    }
    
    public int generar(){ // por si quisiera que sean todos los nros <>
        if(listaNumero.size() < (valorFinal - valorInicial) +1){//Aun no se han generado todos los numeros
            int numero = numeroAleatorio();//genero un numero
            if(listaNumero.isEmpty()){//si la lista esta vacia
                listaNumero.add(numero);
                return numero;
            }else{//si no esta vacia
                if(listaNumero.contains(numero)){//Si el numero que generé esta contenido en la lista
                    return generar();//recursivamente lo mando a generar otra vez
                }else{//Si no esta contenido en la lista
                    listaNumero.add(numero);
                    return numero;
                }
            }
        }else{// ya se generaron todos los numeros
            return -1;
        }
    }


public static void main(String[] args){
//Generando números aleatorios del 3 al 7 sin repetirse
    NumeroAleatorios na = new NumeroAleatorios(2,7);   
    for(int i = 0; i < 5;i++){
           // System.out.print(na.generar()+",");
    	 System.out.print(na.numeroAleatorio()+",");
    }

}
}