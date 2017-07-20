package Gestor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Politica {
	//private PoliticaRdP polrdp;
	ConcurrentLinkedDeque<Integer> q;
			
	public Politica () throws NumberFormatException, IOException {
		//polrdp = new PoliticaRdP();
		q = new ConcurrentLinkedDeque<>();
	}
	
	public int cual (int[] m) {
		//int t = 0;
		//int []c = polrdp.getPoliticaRdP();
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		for ( int i = 0 ; i < m.length ; i++) {
			//c[i] = c[i] * m[i];
			if (m[i] == 1) {
				al.add(i);
				//t = i;
				//break;
			}
		}
		
		/*while (true) {
			if (m[q.element()] == 1) {
				t = q.pollFirst();
				//System.out.print("T:" + t + "\n");
				break;
			}
			else {
				System.out.print(q.element() + "\n");
				q.addLast(q.pollFirst());
			}
		}*/
		
		return al.get((int) Math.floor(Math.random()*al.size()));
		//return t;
	}
	
	/*public boolean puedo (int t) {
		return polrdp.chequeoPolRdp(t);
	}
	
	public void disparoP (int t) {
		polrdp.disparoPolRdp(t);
	}*/
}