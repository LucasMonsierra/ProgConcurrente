package monitor;

import archivos.Matriz;

public class Politicas {
	
	int[] politica1 = { 10,  3,  3,  3,  3,  3,  3,  3,  3,  1,  10,  3,  3,  1,  15,  13,  13, 13,  13,  5};
					//{t10,t11,t12,t13,t14,t15,t16,t17,t18,t19, t20,t21,t22,t23, t30, t31, t32,t33, t34,t35} 

	public Politicas () {
	    //int[] iPolitica = { 20,  7,  7,  7,  6,  7,  6,  7,  6,  1,  15,  5,  5,  1,  10,  3,  3,  3,  3,  1};
	}
	
	public int cual(Matriz m) {
		int t = 0, may = 0;
		for (int i = 0; i < m.getColumnas() ; i++) {
			if (m.getValor(0, i) == 1 && politica1[i] > may ) { may = politica1[i]; t = i; }
		}
		return t;
	}

}
