package test;

import archivos.Lector;
import archivos.Matriz;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;


public class TestPInvariantes {

	private static Lector lector = new Lector();
	private static Matriz marcados, pinvariantes, resultado;
	private static HashMap<String, Matriz> hash;
	
	@BeforeClass 
	public static void test() throws Exception {
		marcados = lector.leerLog("Marcados.txt");
		hash = lector.cargarDatos();
	}
	
	@Before
	public void carga() throws Exception {
		pinvariantes = hash.get("pinvariantes");
		resultado = hash.get("pinvaresult");
	}

	@Test
	public void pinvariantes() {
		
		int expected = 0;
		
		//Recorre las filas de la matriz MARCADOS
		for ( int i = 0 ; i < marcados.getFilas() ; i++ ) {
			//Recorre las filas de la matriz PINVARIANTES
			for ( int j = 0 ; j < pinvariantes.getFilas() ; j++ ) {
				//Recorre las columnas de la matriz PINVARIANTES
				for ( int k = 0 ; k < pinvariantes.getColumnas() ; k++ ) {
					//Chequeo qu� valor de la fila de PINVARIANTES es DISTINTO de 0
					if ( pinvariantes.getValor(j, k) != 0 ) {
						//Con los valores de la columna obtengo el valor de la plaza en la matriz MARCADOS y lo sumo
						expected = expected + marcados.getValor(i, k);
					}
				}//Salgo del FOR que hace la carga de la variable EXPECTED
				Assert.assertEquals(expected, resultado.getValor(0, j));
				expected = 0;
			}
		}
	}

	/*
	@Test // M(M1) + M(P13) = 1
	public void invariante1() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 0) + marcados.getValor(i, 7);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M2) + M(P17) + M(P22) = 1
	public void invariante2 () {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 1) + marcados.getValor(i, 11) + marcados.getValor(i, 15);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M3) + M(P12) + M(P34) = 1
	public void invariante3() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 2) + marcados.getValor(i, 6) + marcados.getValor(i, 21);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(M4) + M(P16) + M(P32) = 1
	public void invariante4() {
		for ( int i = 0 ; i < marcados.getColumnas(); i++ ) {
			int expecteds = marcados.getValor(i, 3) + marcados.getValor(i, 10) + marcados.getValor(i, 19);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P10) + M(P11) + M(P12) + M(P13) + M(P14) + M(P15) + M(P16) + M(P17) + M(P18) = 6
	public void invariante5() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 4) + marcados.getValor(i, 5) + marcados.getValor(i, 6) + marcados.getValor(i, 7) + marcados.getValor(i, 8) + marcados.getValor(i, 9) + marcados.getValor(i, 10) + marcados.getValor(i, 11) + marcados.getValor(i, 12);
			Assert.assertEquals(expecteds, 6);			
		}
	}
	
	@Test // M(P20) + M(P21) + M(P22) + M (P23) = 1
	public void invariante6() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 13) + marcados.getValor(i, 14) + marcados.getValor(i, 15) + marcados.getValor(i, 16);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P30) + M(P31) + M(P32) + M(P33) + M(P34) + M(P35) = 2
	public void invariante7() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 17) + marcados.getValor(i, 18) + marcados.getValor(i, 19) + marcados.getValor(i, 20) + marcados.getValor(i, 21) + marcados.getValor(i, 22);
			Assert.assertEquals(expecteds, 2);			
		}
	}
	
	@Test // M(P11) + M(P35) + M(R1) = 1
	public void invariante8() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 5) + marcados.getValor(i, 22) + marcados.getValor(i, 23);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P14) + M(P15) + M(P21) + M(P23) + M(P33) + M(R2) = 1
	public void invariante9() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 8) + marcados.getValor(i, 9) + marcados.getValor(i, 14) + marcados.getValor(i, 16) + marcados.getValor(i, 20) + marcados.getValor(i, 24);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P18) + M(P31) + M(R3) = 1
	public void invariante10() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 12) + marcados.getValor(i, 18) + marcados.getValor(i, 25);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P15) + M(P22) + M(X1) = 1
	public void invariante11() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 9) + marcados.getValor(i, 15) + marcados.getValor(i, 26);
			Assert.assertEquals(expecteds, 1);			
		}
	}
	
	@Test // M(P12) + M(P14) + M(P16) + M(P31) + M(P32) + M(P33) + M(P34) + M(X2) = 1
	public void invariante12() {
		for ( int i = 0 ; i < marcados.getColumnas() ; i++ ) {
			int expecteds = marcados.getValor(i, 6) + marcados.getValor(i, 8) + marcados.getValor(i, 10) + marcados.getValor(i, 18) + marcados.getValor(i, 19) + marcados.getValor(i, 20) + marcados.getValor(i, 21) + marcados.getValor(i, 27);
			Assert.assertEquals(expecteds, 1);			
		}
	}*/
}
