package org.geosdi.geoplatform.services.request;

import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import static java.util.Locale.ENGLISH;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
@XmlSeeAlso(value = {GPWFSSearchFeaturesRequest.class, GPWFSSearchFeaturesByBboxRequest.class})
public abstract class GPWFSRequest implements GPI18NRequestValidator {

    private static final long serialVersionUID = -7835983957131722680L;
    //
    private String lang;

    public GPWFSRequest() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getLang() {
        return this.lang = ((this.lang != null) ? this.lang : ENGLISH.toString());
    }

    /**
     * @param theLang
     */
    @Override
    public void setLang(String theLang) {
        this.lang = theLang;
    }
}