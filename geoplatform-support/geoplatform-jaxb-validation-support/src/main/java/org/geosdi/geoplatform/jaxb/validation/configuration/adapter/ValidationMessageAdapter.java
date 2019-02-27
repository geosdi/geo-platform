package org.geosdi.geoplatform.jaxb.validation.configuration.adapter;

import org.geosdi.geoplatform.jaxb.validation.configuration.GPValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ValidationMessageAdapter extends XmlAdapter<GPValidationMessage, ValidationMessage> {

    /**
     * Convert a value type to a bound type.
     *
     * @param v The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public ValidationMessage unmarshal(GPValidationMessage v) throws Exception {
        return v;
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public GPValidationMessage marshal(ValidationMessage v) throws Exception {
        return (GPValidationMessage) v;
    }
}