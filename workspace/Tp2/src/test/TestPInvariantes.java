package test;

import archivos.Lector;
import org.junit.BeforeClass;
import org.junit.Assert;
import org.junit.Test;

public class TestPInvariantes {

	private static Lector lector = new Lector();
	public static int[][] marcados;
	
	@BeforeClass public static void test() throws Exception {
		marcados = lector.leerLog("Marcado.txt");
	}
}
