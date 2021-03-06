package test;

import archivos.Matriz;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestPInvariantes {

	private static Matriz pinvariantes, resultados, marcado;
	
	public static void prepare(Matriz marcado, Matriz pinvariantes, Matriz resultados) {
		TestPInvariantes.pinvariantes = pinvariantes;
		TestPInvariantes.resultados = resultados;
		TestPInvariantes.marcado = marcado;
    }
	
	@Before
	public void carga() {
		pinvariantes = pinvariantes.punto(marcado.transpuesta()).transpuesta();
	}
	
	@Test
	public void pinvariantes() {
		Assert.assertEquals(pinvariantes.toString(), resultados.toString());
	}


	/*
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
		}*/	
}
