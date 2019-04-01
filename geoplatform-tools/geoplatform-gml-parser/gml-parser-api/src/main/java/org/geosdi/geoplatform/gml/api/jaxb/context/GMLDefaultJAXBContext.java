package org.geosdi.geoplatform.gml.api.jaxb.context;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GMLDefaultJAXBContext {

    /**
     * @return {@link Marshaller}
     * @throws Exception
     */
    Marshaller acquireDefaultMarshaller() throws Exception;

    /**
     * @return {@link Unmarshaller}
     * @throws Exception
     */
    Unmarshaller acquireDefaultUnmarshaller() throws Exception;
}