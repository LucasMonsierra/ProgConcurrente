package Planta;

import java.util.logging.Logger;

public class Robot3 {
	
	private final static Logger logger = Logger.getGlobal();
	
	public void tomarPiezaC () {
		logger.info("Robot 3 toma pieza C del input para colocarla en M�quina 4.\n");
	}
	
	public void sacarPiezaAalt1 () {
		logger.info("Robot 3 toma pieza A de M�quina 2 para colocarla en output.\n");
	}
	
	public void sacarPiezaAalt2 () {		
		logger.info("Robot 3 toma pieza A de M�quina 4 para colocarla en output.\n");
	}
}
