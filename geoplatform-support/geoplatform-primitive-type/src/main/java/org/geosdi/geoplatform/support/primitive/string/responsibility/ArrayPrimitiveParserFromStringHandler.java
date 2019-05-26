package org.geosdi.geoplatform.support.primitive.string.responsibility;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class ArrayPrimitiveParserFromStringHandler<Type extends Object> extends AbstractPrimitiveParserFromStringHandler<Type[]> {

    /**
     * @param value
     * @return {@link Class<Type>}
     * @throws Exception
     */
    @Override
    public Class<Type[]> parseValue(String value) throws Exception {
        return (this.canParseInternal(value) ? getPrimitiveType() : super.forwardParseValue(value));
    }

    /**
     * @param value
     * @return {@link Boolean}
     * @throws Exception
     */
    protected Boolean canParseInternal(String value) throws Exception {
        String[] values = value.split(" ");
        return (value.length() > 1 ? (this.canParseValue(values[0]) && (this.canParseValue(values[1]))) : FALSE);
    }
}