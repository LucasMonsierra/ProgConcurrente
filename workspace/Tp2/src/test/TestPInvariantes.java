package test;

import archivos.Lector;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;


public class TestPInvariantes {

	private static Lector lector = new Lector();
	public static int[][] marcados;
	
	@BeforeClass public static void test() throws Exception {
		marcados = lector.leerLog("Marcados.txt");
	}
	
	@Test // M(M1) + M(P13) = 1
	public void invariante1() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][0] + marcados [i][7];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M2) + M(P17) + M(P22) = 1
	public void invariante2 () {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][1] + marcados [i][11] + marcados[i][15];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M3) + M(P12) + M(P34) = 1
	public void invariante3() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][2] + marcados [i][6] + marcados[i][21];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M4) + M(P16) + M(P32) = 1
	public void invariante4() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][3] + marcados [i][10] + marcados[i][19];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P10) + M(P11) + M(P12) + M(P13) + M(P14) + M(P15) + M(P16) + M(P17) + M(P18) = 6
	public void invariante5() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][4] + marcados [i][5] + marcados[i][6] + marcados [i][7] + marcados [i][8] + marcados [i][9] + marcados [i][10] + marcados [i][11] + marcados[i][12];
			Assert.assertEquals(expecteds, 6);			
		}
	}
	
	@Test // M(P20) + M(P21) + M(P22) + M (P23) = 1
	public void invariante6() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][13] + marcados [i][14] + marcados[i][15] + marcados[i][16];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P30) + M(P31) + M(P32) + M(P33) + M(P34) + M(P35) = 2
	public void invariante7() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][17] + marcados [i][18] + marcados[i][19] + marcados [i][20] + marcados [i][21] + marcados [i][22];
			Assert.assertEquals(expecteds, 2);			
		}
	}
	
	@Test // M(P11) + M(P35) + M(R1) = 1
	public void invariante8() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][5] + marcados [i][22] + marcados[i][23];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P14) + M(P15) + M(P21) + M(P23) + M(P33) + M(R2) = 1
	public void invariante9() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][8] + marcados [i][9] + marcados[i][14] + marcados[i][16] + marcados[i][20] + marcados[i][24];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P18) + M(P31) + M(R3) = 1
	public void invariante10() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][12] + marcados [i][18] + marcados[i][25];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P15) + M(P22) + M(X1) = 1
	public void invariante11() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][9] + marcados [i][15] + marcados[i][26];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P12) + M(P14) + M(P16) + M(P31) + M(P32) + M(P33) + M(P34) + M(X2) = 1
	public void invariante12() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][6] + marcados [i][8] + marcados[i][10] + marcados [i][18] + marcados [i][19] + marcados [i][20] + marcados [i][21] + marcados [i][27];
			Assert.assertEquals(expecteds, 1);			
		}
	}
}
