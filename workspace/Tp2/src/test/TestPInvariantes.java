package test;

import archivos.Lector;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;


public class TestPInvariantes {

	private static Lector lector = new Lector();
	public static int[][] marcados;
	
	@BeforeClass public static void test() throws Exception {
		Thread.sleep(5000);
		marcados = lector.leerLog("Marcado.txt");
		Thread.sleep(1000);
		
		/*for ( int i = 0 ; i < marcados.length ; i++ ) {
			for (int j = 0 ; j < marcados[0].length ; j++ ) {
				System.out.print(marcados[i][j]);
			}
			System.out.print("\n");
		}*/
	}
	
	@Test // M(M1) + M(P13) = 1
	public void invariante1() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][0] + marcados [i][7];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	/*
	@Test // M(M2) + M(P10) + M(P5) = 1
	public void invariante2 () {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][1] + marcados [i][9] + marcados[i][21];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M3) + M(P16) + M(P2) = 1
	public void invariante3() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][2] + marcados [i][15] + marcados[i][18];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M4) + M(P14) + M(P6) = 1
	public void invariante4() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][3] + marcados [i][13] + marcados[i][22];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P00) + M(P13) + M(P14) + M(P15) + M(P16) + M(P2) + M(P4) + M(P6) = 1
	public void invariante5() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][5] + marcados [i][12] + marcados[i][13] + marcados [i][14] + marcados [i][15] + marcados [i][18] + marcados [i][20] + marcados [i][22];
			Assert.assertEquals(expecteds, 1);			
		}
	}

	@Test // M(P02) + M(P10) + M(P9) = 1
	public void invariante6() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][6] + marcados [i][9] + marcados[i][25];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P03) + M(P10) + M(P3) = 1
	public void invariante7() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][7] + marcados [i][9] + marcados[i][19];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P10) + M(P11) + M(P12) + M(P9) = 1
	public void invariante8() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][9] + marcados [i][10] + marcados[i][11] + marcados[i][25];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P13) + M(P14) + M(P15) + M(P16) + M(P17) + M(P18) = 1
	public void invariante9() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][12] + marcados [i][13] + marcados[i][14] + marcados[i][15] + marcados[i][16] + marcados[i][17];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P0) + M(P1) + M(P2) + M(P3) + M(P4) + M(P5) + M(P6) + M(P7) + M(P8) = 3
	public void invariante10() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][4] + marcados [i][9] + marcados[i][18] + marcados[i][19] + marcados[i][20] + marcados[i][21] + marcados[i][22] + marcados[i][23] + marcados[i][24];
			Assert.assertEquals(expecteds, 3);			
		}
	}
	
	@Test // M(P0) + M(P17) + M(R1) = 1
	public void invariante11() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][4] + marcados [i][16] + marcados[i][26];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P11) + M(P15) + M(P3) + M(P4) + M(P9) + M(R2) = 1
	public void invariante12() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][10] + marcados [i][14] + marcados[i][19] + marcados [i][20] + marcados [i][25] + marcados [i][27];
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P13) + M(P7) + M(R3) = 1
	public void invariante13() {
		for ( int i = 0 ; i < marcados.length ; i++ ) {
			int expecteds = marcados[i][12] + marcados [i][23] + marcados[i][28];
			Assert.assertEquals(expecteds, 1);			
		}
	}*/
}
