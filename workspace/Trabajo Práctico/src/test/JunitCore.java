package test;

import java.util.HashMap;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import archivos.Matriz;

public class JunitCore {
	
	private int i = 0;
	
	public String testearMarcado(HashMap<String, Matriz> hash, Matriz marcado) {

		i++;
		TestPInvariantes.prepare(marcado, hash.get("pinvariantes"), hash.get("pinvaresult"));
		Result result = new JUnitCore().run(TestPInvariantes.class);
		
		String resultado = "Ejecuci�n n�: " + i + ". Test ran: " + result.getRunCount() + ", Failed: " + result.getFailureCount() + ".\n";
		
	    if (!result.getFailures().isEmpty()) { resultado = resultado.concat(result.getFailures().toString() + "\n"); }
	    
	    System.out.printf(resultado);
		return resultado;
	}
}