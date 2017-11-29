package org.geosdi.geoplatform.services.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WMSHeaderParam implements Serializable {

    private static final long serialVersionUID = -22777621262043624L;
    //
    private String headerKey;
    private String headerValue;

    public WMSHeaderParam() {
    }

    public WMSHeaderParam(String headerKey, String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
    }

    /**
     * @return {@link String}
     */
    public String getHeaderKey() {
        return headerKey;
    }

    /**
     * @param headerKey
     */
    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    /**
     * @return {@link String}
     */
    public String getHeaderValue() {
        return headerValue;
    }

    /**
     * @param headerValue
     */
    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  headerKey = '" + headerKey + '\'' +
                ", headerValue = '" + headerValue + '\'' +
                '}';
    }
}
