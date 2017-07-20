import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int eleccion=0,elec=0;
		
		while((eleccion<1) || (eleccion>3)){
			System.out.println ("1 Fifo");
			System.out.println ("2 Lifo");
			System.out.println ("3 Prioridad");
        	System.out.println ("Por favor introduzca el tipo de politica que desea:");
        	String entradaTeclado = "";
        	Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        	entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        	eleccion=Integer.parseInt(entradaTeclado);
		}
		
		while((elec<1) || (elec>2)){
			System.out.println ("1 Su red contiene brazos inhibidores");
			System.out.println ("2 Su red no contiene brazos inhibidores");
        	System.out.println ("Por favor introduzca el tipo de red:");
        	String entradaTeclado = "";
        	Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        	entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        	elec=Integer.parseInt(entradaTeclado);
		}
		
		GestorDeMonitor gestor = new GestorDeMonitor(eleccion,elec);
//		GestorDeMonitor gestor = new GestorDeMonitor(eleccion,2);
		
		Hilo hilo1 = new Hilo(0, gestor);
		Hilo hilo2 = new Hilo(1, gestor);
		Hilo hilo3 = new Hilo(2, gestor);
		Hilo hilo4 = new Hilo(3, gestor);
		Hilo hilo5 = new Hilo(4, gestor);
		Hilo hilo6 = new Hilo(5, gestor);
		Hilo hilo7 = new Hilo(6, gestor);
		Hilo hilo8 = new Hilo(7, gestor);
		Hilo hilo9 = new Hilo(8, gestor);
		Hilo hilo10 = new Hilo(9, gestor);
		Hilo hilo11 = new Hilo(10, gestor);
		Hilo hilo12 = new Hilo(11, gestor);
		Hilo hilo13 = new Hilo(12, gestor);
		Hilo hilo14 = new Hilo(13, gestor);
		Hilo hilo15 = new Hilo(14, gestor);
		Hilo hilo16 = new Hilo(15, gestor);
		Hilo hilo17 = new Hilo(16, gestor);
		Hilo hilo18 = new Hilo(17, gestor);
		Hilo hilo19 = new Hilo(18, gestor);
		Hilo hilo20 = new Hilo(19, gestor);
		Hilo hilo21 = new Hilo(20, gestor);
		Hilo hilo22 = new Hilo(21, gestor);
		Hilo hilo23 = new Hilo(22, gestor);
		hilo1.start();
		hilo2.start();		
		hilo3.start();
		hilo4.start();
		hilo5.start();
		hilo6.start();		
		hilo7.start();
		hilo8.start();
		hilo9.start();
		hilo10.start();		
		hilo11.start();
		hilo12.start();
		hilo13.start();
		hilo14.start();		
		hilo15.start();
		hilo16.start();
		hilo17.start();
		hilo18.start();
		hilo19.start();
		hilo20.start();
		hilo21.start();
		hilo22.start();
		hilo23.start();
	}

}