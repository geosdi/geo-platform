package org.geosdi.geoplatform.jaxb.validation.xsd.collector;

import org.geosdi.geoplatform.jaxb.validation.configuration.store.GPValidationMessageStore;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.ValidationEvent;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.jaxb.validation.xsd.builder.ValidationMessageBuilder.GPValidationMessageBuilder.validationMessageBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPValidationEventCollector implements IGPValidationEventCollector {

    private static final Logger logger = LoggerFactory.getLogger(GPValidationEventCollector.class);
    //
    private final ValidationMessageStore validationMessageStore = new GPValidationMessageStore();

    /**
     * @return {@link ValidationMessageStore}
     */
    @Override
    public ValidationMessageStore getValidationMessageStore() {
        return this.validationMessageStore;
    }

    @Override
    public boolean handleEvent(ValidationEvent event) {
        try {
            this.validationMessageStore.addValidationMessage(validationMessageBuilder()
                    .withValidationEvent(event)
                    .build());
        } catch (Exception ex) {
            logger.error("####################ERROR Transform ValidationEvent to Validation Message : {}\n",
                    ex.getMessage());
            ex.printStackTrace();
        }
        return TRUE;
    }
}