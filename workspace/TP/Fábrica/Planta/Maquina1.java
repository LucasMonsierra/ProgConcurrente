package Planta;
import java.util.concurrent.TimeUnit;

public class Maquina1 {
	
	public void workLineaA () {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
