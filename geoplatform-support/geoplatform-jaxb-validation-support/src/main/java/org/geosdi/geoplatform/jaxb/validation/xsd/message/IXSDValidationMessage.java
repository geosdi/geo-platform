package org.geosdi.geoplatform.jaxb.validation.xsd.message;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IXSDValidationMessage extends ValidationMessage {

    /**
     * @return {@link Integer}
     */
    Integer getColumnNumber();

    /**
     * @param theColumnNumber
     */
    void setColumnNumber(Integer theColumnNumber);

    /**
     * @return {@link Integer}
     */
    Integer getLineNumber();

    /**
     * @param theLineNumber
     */
    void setLineNumber(Integer theLineNumber);

    /**
     * @return {@link org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage.ValidationType}
     */
    default ValidationType getValidationType() {
        return ValidationType.XSD;
    }
}
