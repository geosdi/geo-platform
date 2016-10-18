package org.geosdi.geoplatform.hibernate.validator.support.interpoletor;

import javax.validation.MessageInterpolator;
import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPI18NMessageInterpolator extends MessageInterpolator {

    /**
     * @param theDefaultLocale
     */
    void setDefaultLocale(Locale theDefaultLocale);
}
