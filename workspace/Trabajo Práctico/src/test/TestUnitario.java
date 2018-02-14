package test;

import archivos.Lector;
import archivos.Matriz;
import monitor.RdP;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;


public class TestUnitario {
	
	private static Matriz marcadoInicial, tinvariantes;
	private static HashMap<String, Matriz> hash;
	private static Lector lector;
	private static RdP rdp;


	@BeforeClass
	public static void test() throws Exception {
		lector = new Lector();
		rdp = new RdP();
	}

	@Before
	public void carga() {
		hash = lector.cargarDatos();
		marcadoInicial = hash.get("marcado");
		tinvariantes = hash.get("tinvariantes");		
	}

	@Test
	public void testGetSensibilizadas() { 
		Matriz vs = rdp.getVectorSencibilizadas();
		compararIguales(calcularTranSencib(), vs);
	}
	
	@Test
	public void testDispararNoSensibilizada() {
		Matriz vs = rdp.getVectorSencibilizadas();
		for ( int i = 0 ; i < vs.getColumnas() ; i++ ) {
			if ( vs.getValor(0, i) != 1 ) {
				rdp.disparar(i);
				compararIguales(marcadoInicial, rdp.getMarcado());
			}
		}
	}
	
	@Test
	public void testDispararSensibilizada() {
		Matriz vs = rdp.getVectorSencibilizadas();
		Matriz marc = rdp.getMarcado();
		for ( int i = 0 ; i < vs.getColumnas() ; i++ ) {
			if ( vs.getValor(0, i) == 1 ) {
				rdp.disparar(i);
				compararDistintos(marcadoInicial, rdp.getMarcado());
			}
			rdp.setMarcado(marc);
		}	
	}
	
	public Matriz calcularTranSencib() {
		
		Matriz sencib = new Matriz(1, tinvariantes.getColumnas());
		
		//Recorre los distintos T-Invariantes
		for ( int i = 0 ; i < tinvariantes.getFilas() ; i++ ) {
			//Recorre cada vector T-Invariante
			for ( int j = 0 ; j < tinvariantes.getColumnas() ; j++ ) {
				//Si encuentra un 1, setea un 1 en la posición y sigue con el otro T-Invariante
				if ( tinvariantes.getValor(i, j) == 1 ) {
					sencib.setValor(0, j, 1);
					break;
				}
			}
		}
		return sencib;
	}
	
	//Funcion general para hacer Asserts
	public void compararIguales(Matriz a, Matriz b) {
		Assert.assertEquals(a.toString(), b.toString());	
	}
	
	public void compararDistintos(Matriz a, Matriz b) {
		Assert.assertNotEquals(a.toString(), b.toString());
	}
}
