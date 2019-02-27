package org.geosdi.geoplatform.jaxb.validation;

import org.geosdi.geoplatform.jaxb.validation.hibernate.IGPJAXBHibernateValidator;

import javax.validation.constraints.NotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGenericObject implements IGPJAXBHibernateValidator.GPJAXBValidationModel {

    private static final long serialVersionUID = -8496743928041519447L;
    //
    @NotNull(message = "This value must not be null.")
    private String value;

    /**
     * @return {@link String}
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}