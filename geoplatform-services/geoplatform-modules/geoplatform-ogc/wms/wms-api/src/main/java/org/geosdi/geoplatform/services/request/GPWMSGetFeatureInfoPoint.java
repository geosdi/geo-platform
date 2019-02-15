package org.geosdi.geoplatform.services.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
@XmlSeeAlso(value = WMSGetFeatureInfoPoint.class)
@JsonDeserialize(as = WMSGetFeatureInfoPoint.class)
public interface GPWMSGetFeatureInfoPoint extends Serializable {

    /**
     * @return {@link Integer}
     */
    Integer getX();

    /**
     * @param theX
     */
    void setX(Integer theX);

    /**
     * @return {@link Integer}
     */
    Integer getY();

    /**
     * @param theY
     */
    void setY(Integer theY);
}