package org.geosdi.geoplatform.support.primitive.string.responsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class AbstractPrimitiveParserFromStringHandler<Type extends Object>
        implements GPPrimitiveParserFromStringHandler<Type> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected AbstractPrimitiveParserFromStringHandler successor;

    /**
     * @param value
     * @return {@link Class<Type>}
     * @throws Exception
     */
    protected Class<Type> forwardParseValue(String value) throws Exception {
        if (successor != null) {
            return successor.parseValue(value);
        }
        logger.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@There are no Successor to define Class Parsing " +
                "Value : {}\n", value);
        throw new IllegalStateException("No Elements in Chain to define Primitive Class Type for Value " + value);
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    protected abstract Boolean canParseValue(String value);


    /**
     * @param theSuccessor
     * @return
     */
    @Override
    public <Successor extends GPPrimitiveParserFromStringHandler> void setSuccessor(Successor theSuccessor) {
        this.successor = (AbstractPrimitiveParserFromStringHandler) theSuccessor;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "parserType = " + getParserType() +
                '}';
    }
}
