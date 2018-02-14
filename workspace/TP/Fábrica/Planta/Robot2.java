package Planta;

import java.util.logging.Logger;

public class Robot2 {
	
	private final static Logger logger = Logger.getGlobal();
	
	public void tomarPiezaB () {
		logger.info("Robot 2 toma pieza B del input para colocarla en M�quina 2.\n");
	}
	
	public void sacarPiezaB () {
		logger.info("Robot 2 toma pieza C de M�quina 2 para colocarla en output.\n");
	}
	
	public void trasladarPiezaAalt1 () {
		logger.info("Robot 2 toma pieza A de M�quina 1 para colocarla en M�quina 2.\n");
	}
	
	public void trasladarPiezaAalt2 () {
		logger.info("Robot 2 toma pieza A de M�quina 3 para colocarla en M�quina 4.\n");
	}

	public void trasladarPiezaC () {
		logger.info("Robot 2 toma pieza C de M�quina 4 para colocarla en M�quina 3.\n");
	}

}
