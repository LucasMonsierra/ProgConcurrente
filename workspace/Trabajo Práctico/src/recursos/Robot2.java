package recursos;

import java.util.logging.Logger;

public class Robot2 {
	
	private final static Logger logger = Logger.getGlobal();
	
	public void tomarPiezaB () {
		logger.info("Robot 2 toma pieza B del input para colocarla en M�quina 2.");
	}
	
	public void sacarPiezaB () {
		logger.info("Robot 2 toma pieza B de M�quina 2 para colocarla en output.");
	}
	
	public void trasladarPiezaAalt1 () {
		logger.info("Robot 2 toma pieza A de M�quina 1 para colocarla en M�quina 2.");
	}
	
	public void trasladarPiezaAalt2 () {
		logger.info("Robot 2 toma pieza A de M�quina 3 para colocarla en M�quina 4.");
	}

	public void trasladarPiezaC () {
		logger.info("Robot 2 toma pieza C de M�quina 4 para colocarla en M�quina 3.");
	}

}
