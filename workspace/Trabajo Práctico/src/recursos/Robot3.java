package recursos;

import java.util.logging.Logger;

public class Robot3 {
	
	private final static Logger logger = Logger.getGlobal();
	
	public void tomarPiezaC () {
		logger.info("Robot 3 toma pieza C del input para colocarla en Máquina 4.");
	}
	
	public void sacarPiezaAalt1 () {
		logger.info("Robot 3 toma pieza A de Máquina 2 para colocarla en output.");
	}
	
	public void sacarPiezaAalt2 () {		
		logger.info("Robot 3 toma pieza A de Máquina 4 para colocarla en output.");
	}
}
