
public class Semaforo {

private int val;
private int cantidad;	
	public Semaforo(int i){
		val = i;
	}

	public synchronized void Wait(){
		
		while(val == 0){
			try {
				cantidad++;
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				long threadId = Thread.currentThread().getId();
				System.out.println("Me dormi esperando el semaforo soy el hilo "+threadId);
				e.printStackTrace();
			}
		}
		
		val--;
		
	}
	
	public synchronized void Signal(){
		val=1;
		if(cantidad>0){
			cantidad--;
			notify();
		}
	}
	public int ocupado(){
		return val;
	}
	public int cantidad(){
		return cantidad;
	}
}
