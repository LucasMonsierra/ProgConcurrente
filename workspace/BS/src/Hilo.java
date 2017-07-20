import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hilo extends Thread {
	
	public ArrayList<Integer> camino;
	public int posicion;
	
	private int lineaArchivo;
	private GestorDeMonitor monitor;
	
	public Hilo(int i, GestorDeMonitor mon){
		monitor=mon;
		posicion=0;
		camino= new ArrayList<>();
		lineaArchivo = i;
	}
	
	public void run(){
		leer_camino(lineaArchivo);
		boolean sigo=true;
		
		while(true){
		
			while((camino.size()>posicion)&&(sigo)){
				sigo=monitor.dispararTransicion(camino.get(posicion));
				try {
					sleep(5);
				} catch (Exception e) {
					// TODO: handle exception
				}
				posicion++;
//			System.out.println(currentThread() + "voy por el disparo" + posicion);
			}
		
			posicion=0;
			sigo=true;
		}

	}
	
	@SuppressWarnings("resource")
	public void leer_camino(int lineaArch){

		File f = new File("caminos.txt");
		Scanner s;
		try {
			s = new Scanner(f);
			int lim=lineaArch;
			for(int i=0;i<lim;i++)
				s.nextLine();
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter(" ");
			
			while(sl.hasNext()){
			camino.add(sl.nextInt());
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/* //CHANCHUIO PARA IMPRIMIR ARRAY
		 int tamanio=camino.size();
		 System.out.println("############ datos del arrayList ##################");
		 System.out.println("tamanio= "+tamanio);
		 System.out.print("camino: ");
		 for(int ind=0;ind<tamanio;ind++)
			 System.out.print(camino.get(ind));
		 System.out.println();
		 System.out.println("############### ############### ###############");
		 //CHANCHUIO PARA IMPRIMIR ARRAY*/
	}
	
}
