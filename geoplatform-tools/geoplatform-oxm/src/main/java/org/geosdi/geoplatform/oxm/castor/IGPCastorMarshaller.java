package org.geosdi.geoplatform.oxm.castor;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPCastorMarshaller {

    /**
     * @return {@link Marshaller}
     * @throws Exception
     */
    Marshaller createMarshaller() throws Exception;

    /**
     * @return {@link Unmarshaller}
     * @throws Exception
     */
    Unmarshaller createUnmarshaller() throws Exception;
}