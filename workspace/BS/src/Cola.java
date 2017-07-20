
public class Cola {
	
	private int[] esperando;
	private Semaforo[] esperar;	
	static private int prioridad;
	private int[] cantidad;
	
	public Cola(){
		prioridad=0;
		
		esperar=new Semaforo[35];
		for (int i = 0; i < 35; i++) {
			esperar[i]=new Semaforo(0);	
		}
		
		esperando= new int[35];
		cantidad= new int[35];
	}

	public int[] qEstan(){
		return esperando;
	}
	
	public void wakeup(int i){
		
		cantidad[i] -= 1;
		if (cantidad[i]==0) esperando[i]=0;
		esperar[i].Signal();
//		long threadId = Thread.currentThread().getId();
//		System.out.println("Me desperte soy el hilo "+threadId);
	}
	
	public void dormir(int i,int pri){
		prioridad++;
		if(pri<0)esperando[i]=prioridad;
		else esperando[i]=pri;
		
		long threadId = Thread.currentThread().getId();
		System.out.println("me DORMI en la transicion: T" + i + " soy el hilo "+ threadId);
		cantidad[i]+=1;
		esperar[i].Wait();
		
	}
	
	public void imprimir_cantidad(){
		System.out.println("----------------------------------Cantidad esperando------------------------------------------");
		for(int i=0;i<35;i++)
			System.out.print(cantidad[i]);
		
		System.out.println();
	}
}

