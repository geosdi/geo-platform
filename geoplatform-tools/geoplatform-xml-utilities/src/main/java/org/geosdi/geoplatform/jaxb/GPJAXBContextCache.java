package org.geosdi.geoplatform.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextCache {

    final Class type;
    final JAXBContext context;

    public GPJAXBContextCache(Class type) throws JAXBException {
        this.type = type;
        this.context = JAXBContext.newInstance(type);
    }
}
