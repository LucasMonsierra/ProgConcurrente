package recursos;

import java.util.logging.Logger;

public class Robot3 {
	
	private final static Logger logger = Logger.getGlobal();
	
	public void tomarPiezaC () {
		logger.info("Robot 3 toma pieza C del input para colocarla en M�quina 4.");
	}
	
	public void sacarPiezaAalt1 () {
		logger.info("Robot 3 toma pieza A de M�quina 2 para colocarla en output.");
	}
	
	public void sacarPiezaAalt2 () {		
		logger.info("Robot 3 toma pieza A de M�quina 4 para colocarla en output.");
	}
}
