package Planta;

import java.util.logging.Logger;

public class Maquina3 {
	
	private final static Logger logger = Logger.getGlobal();

	public void fabricarPiezaA () {
		logger.info("Robot 1 coloca pieza A en M�quina 3. Fabicando pieza A.\n");
	}
	
	public void fabricarPiezaC () {
		logger.info("M�quina 3 fabicando pieza C.\n");
	}
}
