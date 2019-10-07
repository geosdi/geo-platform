package org.geosdi.geoplatform.stax.reader.factory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import java.io.Serializable;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPXMLInputFactoryBuilder extends Serializable {

    /**
     * @param theProp
     * @param <F>
     * @return {@link F}
     */
    <F extends XMLInputFactory> F withProp(@Nonnull(when = NEVER) Map<String, Object> theProp);
}