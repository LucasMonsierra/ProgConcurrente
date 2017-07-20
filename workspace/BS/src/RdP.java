//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.FileReader;
import java.util.Scanner;


public class RdP {
	private static int COL=35;
	private static int FIL=29;
	private int[] enabled;
	private int[] vectorQ;
	private int[][] matrixI;
	private int[][] matrixlI;
	private int[][] matrixH;
	private int[] marking;
	private int[] vectorB;
	@SuppressWarnings("unused")
	private int[] vectorEX;
	private int[] peso;
	private int[] pesoh;
	private Rdp_tiempo Rtemporal;
	private int sinh;
	
	public RdP(int brazos){
		
		enabled 	= 	new int[COL];
		vectorQ 	= 	new int[FIL];
		matrixI 	= 	new int[FIL][COL];
		matrixlI 	= 	new int[FIL][COL];
		matrixH 	= 	new int[FIL][COL];
		marking 	= 	new int[FIL];
		vectorB 	= 	new int[COL];
		vectorEX	= 	new int[FIL];
		peso		=	new int[COL];
		pesoh		=	new int[COL];
		Rtemporal 	=	new Rdp_tiempo();
		sinh=brazos;
		borrarViejos();
		leerArchivo("marking");
		leerArchivo("matrixH");
		leerArchivo("matrixI");
		leerArchivo("matrixlI");
//		imprimir("marking");
//		imprimir("matrixlI");
		
		
		
		int aux[]=new int[FIL];
		for(int i=0;i<FIL;i++)aux[i]=1;
			
		peso=vector_matriz(aux,matrixlI);
		
		
		pesoh=vector_matriz(aux,matrixH);
		
		
		sensibilizadas();
		if(sinh==1){vectorEX();}
		
	}

	public long actualizarMarcado(int shot){
		int shoot[]=new int[COL];
		long ventana;
		//veo si t esta sens
		//y si lo está, actualizo Ec de estado
		long threadId = Thread.currentThread().getId();
		System.out.println("Soy el hilo "+threadId+" Trato de disparar t"+shot);
		
		if(enabled[shot]==1){

//			if((enabled[15]==1)||(enabled[21]==1)|| (enabled[22]==1))
//				if((shot==15)&&(shot!=21)&&(shot!=22))
//					return -2;
			
			ventana=Rtemporal.in_time(shot);
			
			if(ventana==0){
				shoot[shot]=1;
				Ecuacion(shoot);//aca va el vector de disparo	
				threadId = Thread.currentThread().getId();
				System.out.println("Soy el hilo "+threadId+" Transicion disparada t"+shot);
				sensibilizadas();
				if(sinh==1){vectorEX();}
				escribirArchivo(shot);
				escribirMarcado();
				
			}
			
			return ventana;
		}
		else{
			return -2;
		}
	}
	
	public void Ecuacion(int[] shoot){
		int[] aux;
		aux=new int[FIL];
		
		if(sinh==1)aux=matriz_vector(vector_vector(shoot,enabled),matrixI); 
		else aux=matriz_vector(shoot,matrixI);
		
		for(int i=0;i<FIL;i++)
			marking[i]=marking[i] + aux[i];	
		
	} 
	
	public int[] get_enabled(){
		return enabled;
	}
	
	public int[] get_marking(){
		return marking;
	}
	
	public void sensibilizadas(){
		int i;
		int[] aux=new int[COL];
		
		//imprimir("marking");
		
		//aux=vector_matriz(marking,matrixlI);
		
		for(int j=0;j<COL;j++)
			for (i=0;i<FIL;i++)
				if( (marking[i]>0) && (matrixlI[i][j])>0) aux[j]+=1;	
		
		
		
		for(i=0;i<COL;i++)
				if(aux[i]>=peso[i])
					enabled[i]=1;
				else
					enabled[i]=0;
		//if(sinh!=1)imprimir("enabled");
		Rtemporal.sensibilizar(enabled);
	}
	
	public void vectorEX(){
		
		for(int i=0;i<FIL;i++)
			if(marking[i]==0) vectorQ[i]=1;
			else	vectorQ[i]=0;
		
		vectorB=vector_matriz(vectorQ,matrixH);
		
		for(int i=0;i<COL;i++)
			if(vectorB[i]==pesoh[i])vectorB[i]=1;
			else vectorB[i]=0;
	
//		System.out.println("------------------------------Vector PH-----------------------------");
//		for(int i=0;i<COL;i++)
//			System.out.print(pesoh[i]);
//		System.out.println();
//		
//		System.out.println("------------------------------Vector B-----------------------------");
//		for(int i=0;i<COL;i++)
//			System.out.print(vectorB[i]);
//		System.out.println();
		
		//imprimir("enabled");
		enabled=vector_vector(enabled,vectorB);
		//imprimir("enabled");
	}	
	
	public int[] matriz_vector(int[] b,int [][] c){
		int i,j;
		int[]a;
		a=new int[FIL];
		
		for (i=0;i<FIL;i++)
			for(j=0;j<COL;j++)	
				a[i] += b[j]*c[i][j];
	
			return a;
	}
	
	public int[] vector_matriz(int[]b,int[][] c){
		int i,j;
		int[]a;
		a=new int[COL];
		
		for(j=0;j<COL;j++)
			for (i=0;i<FIL;i++)
				a[j] += b[i]*c[i][j];	
		
//		System.out.println("-----------------------Multiplicacion--------------------");
//		for(int k=0;k<COL;k++)System.out.print(a[k]);
//		System.out.println();
		
		return a;
		
	}
	
	public int[] vector_vector(int[] b,int[] c){
		int i;
		int[] a;
		a=new int[COL];
		for(i=0;i<COL;i++)
			a[i]=b[i] & c[i];
		
		return a;
	}
		
	@SuppressWarnings("resource")
	public void leerArchivo(String nombre){
		System.out.println("Leyendo archivo... "+nombre+".txt");
		File f = new File(nombre+".txt");
		Scanner s;
		
		switch(nombre){
		//**************************************************
			case "enabled":
				System.out.println("cargando datos "+nombre);
				try {			
					s = new Scanner(f);
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						int i=0;
						while(sl.hasNext()){
							//CARGAR DATOS
							enabled[i] = sl.nextInt();
							i++;
						}
					
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
		//**************************************************
			case "marking":
				System.out.println("cargando datos "+nombre);
				try {			
					s = new Scanner(f);
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						int i=0;
						while(sl.hasNext()){
							//CARGAR DATOS
							marking[i] = sl.nextInt();
							i++;
						}
					
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
		//**************************************************
			case "vectorQ":
				System.out.println("cargando datos "+nombre);
				try {			
					s = new Scanner(f);
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						int i=0;
						while(sl.hasNext()){
							//CARGAR DATOS
							vectorQ[i] = sl.nextInt();
							i++;
						}
					
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
		//**************************************************
			case "matrixH":
				System.out.println("cargando datos "+nombre);
				try {			
					s = new Scanner(f);
					int i=0,j=0;
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						while(sl.hasNext()){
							//System.out.println(sl.nextInt());
							matrixH[i][j] = sl.nextInt();
							j++;
						}
						i++;
						j=0;
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
		//**************************************************
			case "matrixI":
				System.out.println("cargando datos "+nombre);
				try {			
					s = new Scanner(f);
					int i=0,j=0;
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						while(sl.hasNext()){
							//System.out.println(sl.nextInt());
							matrixI[i][j] = sl.nextInt();
							j++;
						}
						i++;
						j=0;
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
		//**************************************************
			case "matrixlI":
				System.out.println("cargando datos "+nombre);
				try {			
					s = new Scanner(f);
					int i=0,j=0;
					while (s.hasNextLine()) {
						String linea = s.nextLine();
						Scanner sl = new Scanner(linea);
						sl.useDelimiter(" ");
						while(sl.hasNext()){
							//System.out.println(sl.nextInt());
							matrixlI[i][j] = sl.nextInt();
							j++;
						}
						i++;
						j=0;
					}
					s.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("---> DEFAULT <---");
		
			}
		
	}
	
	public void imprimir(String nombre) {
		// TODO Auto-generated method stub
		switch(nombre){
			case "enabled":
				System.out.println("***IMPRIME "+nombre);
				for(int i=0;i<COL;i++){
					System.out.print("T"+i+" ");
				}
				System.out.println();
				for(int j=0;j<COL;j++){
					System.out.print(enabled[j]+"  ");
					if(j>9)System.out.print(" ");
				}
				System.out.println();
				break;
			case "marking":
				System.out.println("***IMPRIME "+nombre);
				for(int i=0;i<FIL;i++){
					System.out.print("P"+i+" ");
				}
				System.out.println();
				for(int i=0;i<FIL;i++)
				{
					System.out.print(marking[i]+"  ");
					if(i>9)System.out.print(" ");
				}
				System.out.println();
				break;
			case "vectorQ":
				System.out.println("***IMPRIME "+nombre);
				for(int i=0;i<FIL;i++)
					System.out.print(vectorQ[i]+" ");
				System.out.println();
				break;
			case "matrixH":
				System.out.println("***IMPRIME "+nombre);
				for(int i=0;i<FIL;i++){
					for(int j=0;j<COL;j++)
						System.out.print(matrixH[i][j]+" ");
					System.out.println();
				}
				break;
			case "matrixI":
				System.out.println("***IMPRIME "+nombre);
				for(int i=0;i<FIL;i++){
					for(int j=0;j<COL;j++)
						System.out.print(matrixI[i][j]+" ");
					System.out.println();
				}
				break;
			case "matrixlI":
				System.out.println("***IMPRIME "+nombre);
				for(int i=0;i<FIL;i++){
					for(int j=0;j<COL;j++)
						System.out.print(matrixlI[i][j]+" ");
					System.out.println();
				}
				break;
				
			default:
				System.out.println("---> DEFAULT <---");
			
		}
	}

	public void escribirArchivo(int shot) {
		// TODO Auto-generated method stub
		BufferedWriter out = null;   
		try {   
		    out = new BufferedWriter(new FileWriter("transicionesDisparadas.txt", true));   
		    out.write("T"+shot);out.newLine();
		} catch (IOException e) {   
		    // error processing code   
		} finally {   
		    if (out != null) {   
		        try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
		    }   
		}
		
	}

	public void borrarViejos(){
		File borrar=new File("transicionesDisparadas.txt");
		borrar.delete();
		
		borrar=new File("marcado.txt");
		borrar.delete();
		
	}

	public void escribirMarcado(){
		
		BufferedWriter out = null;   
		try {   
		    out = new BufferedWriter(new FileWriter("marcado.txt", true));   
		    for(int i=0;i<(FIL-1);i++)
		    	out.write(marking[i]+" ");
		    out.write(marking[FIL-1]);
		    out.newLine();
		} catch (IOException e) {   
		    // error processing code   
		} finally {   
		    if (out != null) {   
		        try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
		    }   
		}
	}
}

