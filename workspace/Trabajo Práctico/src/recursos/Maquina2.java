package recursos;

import java.util.logging.Logger;

public class Maquina2 {
	
	private final static Logger logger = Logger.getGlobal();

	public void fabricarPiezaA () {
		logger.info("M�quina 2 fabicando pieza A.");
	}
	
	public void fabricarPiezaB () {
		logger.info("M�quina 2 fabicando pieza B.");
	}
}
