package archivos;

public class Matriz {
	private int[][] m;
	
	public Matriz(int fil, int col) {
		m = new int[fil][col];
	}
	
	//�ES NECESARIO ESTE M�TODO?
	public Matriz(int[][] matriz) {
		this.m = matriz;
	}
	
	public int[][] getMatriz() {
		return m;
	}
	
	//�ES NECESARIO ESTE M�TODO?
	public int getValor(int fil, int col) {
		return m[fil][col];
	}
	
	//�ES NECESARIO ESTE M�TODO?
	public void setValor(int fil, int col, int valor) {
		this.m[fil][col] = valor;
	}
	
	public int getFilas() {
		return m.length;
	}

	public int getColumnas() {
		return m[0].length;
	}
	
	public void clear() {
		for (int i = 0; i < this.getFilas(); i++) {
			for (int j = 0; j < this.getColumnas(); j++) {
				this.setValor(i, j, 0);
			}
		}
	}
	
	public Matriz and(Matriz B) {
		Matriz A = this;
		Matriz Result = new Matriz(B.getFilas(), B.getColumnas());

		for (int i = 0; i < A.getFilas(); i++) {
			for (int j = 0; j < A.getColumnas(); j++) {
				if (A.getValor(i, j) == 1 && B.getValor(i, j) == 1) {
					Result.setValor(i, j, 1);
				} 
				else {
					Result.setValor(i, j, 0);
				}
			}
		}
		return Result;
	}
	
	public boolean esCero() {
		int aux = 0;

		for (int i = 0; i < this.getFilas(); i++)
			for (int j = 0; j < this.getColumnas(); j++)
				aux = this.getValor(i, j) + aux;

		return aux == 0;
	}
	
	public boolean esUno() {
		int aux = 0;

		for (int i = 0; i < this.getFilas(); i++)
			for (int j = 0; j < this.getColumnas(); j++)
				aux = this.getValor(i, j) + aux;

		return aux == 1;
	}
	
	public String toString() {
		String texto = "";
		for (int i = 0; i < this.getFilas(); i++) {
			for (int j = 0; j < this.getColumnas(); j++) {
				texto += " " + this.m[i][j];
			}
			texto += "\n";
		}
		texto += "";
		return texto;
	}
}
