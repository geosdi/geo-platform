package org.geosdi.geoplatform.hibernate.validator.support.base;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPBaseValidator<P extends Object, MESSAGE extends Object> {

    /**
     * @param thePojo
     * @return {@link MESSAGE}
     */
    MESSAGE validate(P thePojo);

    /**
     * @param thePojo
     * @param groups
     * @return {@link MESSAGE}
     */
    MESSAGE validate(P thePojo, Class<?>... groups);

    /**
     * @return {@link String}
     */
    String getValidatorName();
}
