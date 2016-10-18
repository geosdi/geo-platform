package org.geosdi.geoplatform.hibernate.validator.support.request;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPI18NRequestValidator extends Serializable {

    /**
     * @return {@link String}
     */
    String getLang();

    /**
     * @param theLang
     */
    void setLang(String theLang);

    /**
     * @return {@link Locale}
     */
    @XmlTransient
    default Locale getLocale() {
        return Locale.forLanguageTag(getLang());
    }
}
