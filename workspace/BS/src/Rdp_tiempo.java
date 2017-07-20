import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rdp_tiempo {
	private static int t=35;
	private int[] alfa;
	private int[] beta;
	private long[] sensible;
	public ArrayList<Integer> temporal;
			
	public Rdp_tiempo(){
		alfa=new int[t];
		beta=new int[t];
		sensible=new long[t];
		temporal= new ArrayList<>();
		for(int i=0;i<t;i++){
			alfa[i]=0;
			beta[i]=0;
			
		}
		Trans_temporal();
	}

	public long in_time(int trans){
		long ventana;
		
		if(alfa[trans]==0) return 0;
		else ventana=(System.currentTimeMillis()-sensible[trans]); //- alfa[trans];
		
		//long threadId = Thread.currentThread().getId();
		//System.out.println("Mi ventana es "+ventana+" mi alfa es "+alfa[trans]+" mi beta es "+beta[trans]+" Soy el hilo "+threadId);
		
		if(alfa[trans]>ventana){
			return (alfa[trans]-ventana);
		}
		else{
			if(beta[trans]>ventana){sensible[trans]=0;return 0;}
			else return -1;
		}
	}
	
	public void sensibilizar(int[] trans){
		for(int k=0;k<temporal.size();k++)
			{	
				if((trans[temporal.get(k)]==1)&&(sensible[temporal.get(k)]==0))
					sensible[temporal.get(k)]=System.currentTimeMillis();
				
//				if(trans[temporal.get(k)]==0)sensible[temporal.get(k)]=0;
//				System.out.println("Para la transicion "+temporal.get(k)+" el tiempo es" +sensible[temporal.get(k)]);
			}
	
	} 

	@SuppressWarnings("resource")
	public void Trans_temporal(){
		
		File f = new File("t_tiempo.txt");
		Scanner s;
		int i=0;
		
		try {			
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter(" ");
				temporal.add(sl.nextInt());
				alfa[temporal.get(i)]=sl.nextInt();
				beta[temporal.get(i)]=sl.nextInt();
				i++;
				}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
