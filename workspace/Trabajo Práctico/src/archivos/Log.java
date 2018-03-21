package archivos;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public Log() throws SecurityException, IOException {
		logger.setUseParentHandlers(false);
        FileHandler fileTxt = new FileHandler(System.getProperty("user.home") + "\\Log\\ExecutionLog.txt");
        fileTxt.setFormatter(new FormatoLog());
        logger.addHandler(fileTxt);
  	}

	public Logger getLog() {
		return logger;
	}
}
