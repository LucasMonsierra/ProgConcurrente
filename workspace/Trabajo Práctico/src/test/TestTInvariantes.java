package test;

import archivos.Lector;
import archivos.Matriz;
import monitor.GestorMonitor;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;


public class TestTInvariantes {
	
	private static Matriz marcadoInicial, tinvariantes, marcado;
	private static HashMap<String, Matriz> hash;
	private static Lector lector;
	private static GestorMonitor monitor;


	@BeforeClass
	public static void test() throws Exception {
		lector = new Lector();
		monitor = new GestorMonitor();
		hash = lector.cargarDatos();
	}

	@Before
	public void carga() {
		tinvariantes = hash.get("tinvariantes");
		marcadoInicial = hash.get("marcado");
	}
	
	@Test
	public void tinvariantes() {
		
		//Recorre los distintos T-Invariantes
		for ( int i = 0 ; i < tinvariantes.getFilas() ; i++ ) {
			//Recorre cada vector T-Invariante
			for ( int j = 0 ; j < tinvariantes.getColumnas() ; j++ ) {
				//Si encuentra un 1, v� a disparar la transici�n
				if ( tinvariantes.getValor(i, j) == 1 ) {
					marcado = monitor.dispararTransicion(j);
				}
			}
			//Comparo el marcado final obtenido de cada vector T-Invariante
			for ( int k = 0 ; k < marcadoInicial.getColumnas() ; k++ ) {
				Assert.assertEquals(marcado.getValor(0, k), marcadoInicial.getValor(0, k));
			}
		}
	}
}
