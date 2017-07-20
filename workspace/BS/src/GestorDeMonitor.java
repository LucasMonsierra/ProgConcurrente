public class GestorDeMonitor {
		
		private Semaforo mutex;
		private Cola cola;
		private RdP rdp;
		private int disparar[];
		private Politica pol;
		int i,flgh,id,transicion;
		private int bloqueados;
		long ventana;
		
		public GestorDeMonitor(int poli, int brazos){
			i=0;flgh=0;id=0;
			mutex = new Semaforo(1);
			rdp=new RdP(brazos);
			cola=new Cola();
			disparar=new int[35];
			pol=new Politica(poli);
			bloqueados=0;
			transicion=0;
			ventana=-1;	
		}

		public boolean dispararTransicion(int shot){
			
			mutex.Wait(); //PREGUNTO SI EL MONITOR ESTA LIBRE
			long threadId = Thread.currentThread().getId();
			System.out.println("El monitor es mio soy el hilo "+threadId);
			//transicion = shot;
			//PREGUNTO POR EL RECURSO
			ventana=rdp.actualizarMarcado(shot);
			/* Existen 4 estados posibles de ventana:
			 * 0 se disparo correctamente
			 * >0 se duerme un tiempo determinado
			 * -2 duerme sin tiempo determinado
			 * -1 el hilo muere
			 */
			while((ventana==-2)||(ventana>0)){	
				DELAY(shot);
				ventana=rdp.actualizarMarcado(shot);	
			}
			
			disparar=and(rdp.get_enabled(),cola.qEstan());	
			id=pol.elegir(disparar);
			
			System.out.println("Voy a DESPERTAR a la transición T" + id) ;
			if(id>=0)RESUME();
			
			mutex.Signal();
			threadId = Thread.currentThread().getId();
			System.out.println("Deje el monitor soy el hilo "+threadId+" Deje la transicion "+shot);
			if(ventana==-1) return false;// indico al hilo que termino su camino
			return true;
			
		}
		
		public int[] and(int[] a, int[] b){
			int i=0;
			int c[];
			c= new int[a.length];
			
			for(i=0;i<a.length;i++){
				c[i]=a[i]*b[i];
			}
			return c;
		}
		
		public void DELAY(int transicion) {
			bloqueados++;
			//System.out.println("Delay = > Cantidad de bloqueados "+bloqueados);
			
			mutex.Signal(); //RETIRO DEL MONITOR AL HILO, PARA ELIMINAR LA EXCLUSION MUTUA
			
			if(ventana==-2)
				cola.dormir(transicion,-1);//BLOQUEO EL HILO YA QUE NO CUENTA CON LOS RECURSOS
			else{
				try {
					//int i=transicion;
					long threadId = Thread.currentThread().getId();
					System.out.println("Soy el hilo "+threadId+" me voy a dormir "+ventana);
					Thread.sleep(ventana);
					threadId = Thread.currentThread().getId();
					System.out.println("Me desperte soy el hilo"+threadId);
				} catch (Exception e) {}
				//System.out.println("me encolo para esperar el monitor "+transicion);
				if((mutex.ocupado()==1)&&(mutex.cantidad()>5)){cola.dormir(transicion,100000000);}
			}
			//if(ventana==-2) Thread.stop();
			mutex.Wait();// CUANDO ALGUIEN LO DESBLOQUEA PREGUNTA NUEVAMENTE SI ESTA LIBRE EL MONITOR
		}
			
		//DESPIERTA A LOS HILOS QUE SE DURMIERON POR FALTA DE RECURSOS
		public void RESUME() {
			//System.out.println("Rsum = > Cantidad de bloqueados "+bloqueados);
			if(bloqueados>0) { //SI HAY HILOS BLOQUEADOS DESPIERTO A UNO
				bloqueados--; //DISMINUYO LA CANTIDAD DE BLOQUEADOS
				//System.out.println("id = " + id);
				//if (id >= 0) {// este if esta mal, corregirlo
					cola.wakeup(id);//DESBLOQUEO EL HILO YA QUE NO CUENTA CON LOS RECURSOS	
				//}
			}
		}
}



