package test;

import archivos.Lector;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestPInvariantes {

	private static Lector lector = new Lector();
	public static int[][] marcados;
	
	@BeforeClass public static void test() throws Exception {
		Thread.sleep(5000);
		marcados = lector.leerLog("Marcado.txt");
		Thread.sleep(1000);
		
	}
	
	@Test
	public void pruebaDeLector() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			for ( int j = 0 ; j < marcados[0].length ; j++) {
				System.out.print( marcados[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}
