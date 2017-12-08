package org.geosdi.geoplatform.connector.server.request.v100.function;

import org.geosdi.geoplatform.xml.ows.v110.CodeType;

import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ProcessIdentifierFunction implements Function<String, CodeType> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    public CodeType apply(String value) {
        CodeType codeType = new CodeType();
        codeType.setValue(value);
        return codeType;
    }
}
