package recursos;

import java.util.logging.Logger;

public class Robot1 {
	
	private final static Logger logger = Logger.getGlobal();

	public void tomarPiezaA () {
		logger.info("Robot 1 toma pieza A del input.");
	}

	public void sacarPiezaC () {
		logger.info("Robot 1 toma pieza C de Máquina 3 para colocarla en output.");
	}
}
