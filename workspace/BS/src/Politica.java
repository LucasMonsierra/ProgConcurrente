import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Politica {

	private int politica;
	private int [] poli;
	public Politica(int i){
		politica=i;
		poli=new int[35];
		leer();
		//System.out.println("Cree politica "+i);
	}
	
	public int elegir(int[] vec){
		//System.out.println("Entre a politica");

		switch (politica) {
			case 1: return fifo(vec);
			case 2: return lifo(vec);
			case 3: return politic(vec);
			default:return -1;
		}
	}
	
	public int fifo(int [] vec){
		int aux=0;
		int disparo=-1;
		int k;
		
		//System.out.println("Entre a la fifo");
		
		for(k=0;k<vec.length;k++){	
			if(vec[k] != 0){
				aux=vec[k];
				disparo=k;
			}
			if(vec[k]==100000000) return k;
		}
		
		for(int i=0; i<vec.length;i++)
		{	
			if((vec[i]<aux) && (vec[i]!=0)) {
				aux=vec[i];
				disparo=i;
			}
		}
		return disparo;
		
	}
	
	public int lifo(int [] vec){
		int aux=vec[0];
		int disparo=0;
		int flag=0;
		for(int i=0; i<vec.length ;i++)
			if(vec[i]>aux) {
				aux=vec[i];
				disparo=i;
				flag=1;
			}
	
		if(flag==0)
			if(vec[0]==0) return -1;
				
		return disparo;
	}
		
	private int politic(int[] vec) {
		int aux[]=new int[vec.length];
		int ran=-1;
		//Random rn = new Random();
		int k;
			
		for(k=0;k<vec.length;k++){
				
			if(vec[k]>0) vec[k]=1;
				
			aux[k]=vec[k]*poli[k];
			
		}
		//System.out.println("-----------------------------------Prioridades--------------------------------");
		
//		for(int i=0; i<vec.length;i++){
//			System.out.print(poli[i]+" ");
//		}
//		System.out.println("");
//		for(int i=0; i<vec.length;i++){
//			System.out.print(aux[i]+" ");
//		}
//		System.out.println("");
//		for(int i=0; i<vec.length;i++){
//			System.out.print(vec[i]+" ");
//		}
//			System.out.println("");
		
		ran=lifo(aux);	
		recalcular(4,5,ran);
		recalcular(7,8,ran);
		recalcular(31,33,ran);
		
//		if((ran==4)||(ran==5)){
//			int num=((rn.nextInt()%6)+ 25);
//			//if(num<0) num=(-num);
//			poli[4]=num;
//			num=((rn.nextInt()%6)+ 25);
//			poli[5]=num;
//		}
		return ran;
	}
	
	public void recalcular(int i, int j, int ran){
		Random rn = new Random();
		
		if((ran==i)||(ran==j)){
			int num=((rn.nextInt()%6)+ 25);
			//if(num<0) num=(-num);
			poli[i]=num;
			num=((rn.nextInt()%6)+ 25);
			poli[j]=num;
		}
		
	}


	public void leer(){
		File f = new File("politica.txt");
		Scanner s;
		int pri=36;
				System.out.println("cargando datos ");
				try {			
					s = new Scanner(f);
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						int i=0;
						while(sl.hasNext()){
							//CARGAR DATOS
							poli[sl.nextInt()]= pri;
							pri--;
						}
					
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		
		
	}
}
