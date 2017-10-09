package monitor;

import archivos.Matriz;

public class Politicas {
	
	public int cual(Matriz m) {
		int i;
		for (i = 0; i < m.getColumnas() ; i++) {
			if (m.getValor(0, i) == 1) { break; }
		}
		return i;
	}

}
