package org.geosdi.geoplatform.jaxb.validation.xsd.collector;

import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;

import javax.xml.bind.ValidationEventHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPValidationEventCollector extends ValidationEventHandler {

    /**
     * @return {@link org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore}
     */
    ValidationMessageStore getValidationMessageStore();
}
