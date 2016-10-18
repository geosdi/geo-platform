package org.geosdi.geoplatform.hibernate.validator.support.i18n;

import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPI18NValidator<P extends Object, MESSAGE extends Object> {


    /**
     * <p>Return Null, if there are no errors.</p>
     *
     * @param thePojo
     * @param locale
     * @return {@link MESSAGE}
     */
    MESSAGE validate(P thePojo, Locale locale);

    /**
     * @return {@link String}
     */
    String getValidatorName();
}
