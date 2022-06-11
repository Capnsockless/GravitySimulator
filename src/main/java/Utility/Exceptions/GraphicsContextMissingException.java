package Utility.Exceptions;

import org.apache.logging.log4j.core.Logger;

public class GraphicsContextMissingException extends Throwable {
    public GraphicsContextMissingException(Logger log) {
        log.fatal("Graphics Context was missing from Space.");
    }
}
