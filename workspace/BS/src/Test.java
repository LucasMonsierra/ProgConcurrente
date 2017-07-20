import static org.junit.Assert.*;

import org.junit.Before;

public class Test {
	private RdP rdp;
	private int[] Maux;
	
 	@Before
	public void setUp() throws Exception {
		rdp= new RdP();
		Maux=rdp.get_marking();
	}

	@org.junit.Test
	public void SPuedeDisparar() {
		assertTrue(rdp.actualizarMarcado(0));
		
	}
	@org.junit.Test
	public void SDisparo(){
		rdp.actualizarMarcado(0);
		assertNotSame(rdp.get_marking(),Maux);
		
	}
}