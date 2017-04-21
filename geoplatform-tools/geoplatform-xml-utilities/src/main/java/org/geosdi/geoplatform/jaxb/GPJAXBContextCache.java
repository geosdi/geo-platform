package org.geosdi.geoplatform.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GPJAXBContextCache)) return false;
        GPJAXBContextCache that = (GPJAXBContextCache) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
