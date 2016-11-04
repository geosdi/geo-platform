package org.geosdi.geoplatform.support.primitive.string.responsibility;

import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.STRING;

/**
 * <p>This is the last link in the chain, so the Default Value.</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class DefaultParserFromStringHandler extends AbstractPrimitiveParserFromStringHandler<String> {

    /**
     * @param value
     * @return {@link Class<String>}
     * @throws Exception
     */
    @Override
    public Class<String> parseValue(String value) throws Exception {
        return getPrimitiveType();
    }

    /**
     * @return {@link ParserType}
     */
    @Override
    public <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType() {
        return (ParserType) STRING;
    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        return Boolean.TRUE;
    }
}
