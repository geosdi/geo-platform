package org.geosdi.geoplatform.stax.reader.factory;

import javax.xml.stream.XMLInputFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPXMLInputFactoryJDKBuilder extends GPXMLInputFactoryBuilder {

    String XML_INPUT_FACTORY_KEY = "javax.xml.stream.XMLInputFactory";
    String XML_INPUT_FACTORY_VALUE = "com.sun.xml.internal.stream.XMLInputFactoryImpl";

    /**
     * @param <F>
     * @return {@link F}
     */
    <F extends XMLInputFactory> F defaultFactory();
}