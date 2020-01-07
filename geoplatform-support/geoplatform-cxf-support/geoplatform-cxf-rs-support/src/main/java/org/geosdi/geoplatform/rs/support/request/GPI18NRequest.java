package org.geosdi.geoplatform.rs.support.request;

import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;

import javax.xml.bind.annotation.XmlTransient;

import static java.util.Locale.ENGLISH;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@ToString
@XmlTransient
public abstract class GPI18NRequest implements GPI18NRequestValidator {

    private static final long serialVersionUID = 1808463848496479182L;
    //
    private String lang;

    protected GPI18NRequest() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getLang() {
        return this.lang = ((this.lang != null) ? this.lang : ENGLISH.toString());
    }
}