package org.geosdi.geoplatform.services.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
@XmlSeeAlso(value = {WMSGetFeatureInfoBoundingBox.class})
@JsonDeserialize(as = WMSGetFeatureInfoBoundingBox.class)
public interface GPWMSGetFeatureInfoBoundingBox extends Serializable {

    /**
     * @return {@link Double}
     */
    Double getMinx();

    /**
     * @param theMinx
     */
    void setMinx(Double theMinx);

    /**
     * @return {@link Double}
     */
    Double getMiny();

    /**
     * @param theMiny
     */
    void setMiny(Double theMiny);

    /**
     * @return {@link Double}
     */
    Double getMaxx();

    /**
     * @param theMaxx
     */
    void setMaxx(Double theMaxx);

    /**
     * @return {@link Double}
     */
    Double getMaxy();

    /**
     * @param theMaxy
     */
    void setMaxy(Double theMaxy);

    /**
     * @return {@link GPWMSBoundingBox}
     */
    @XmlTransient
    GPWMSBoundingBox toWMSBoundingBox();
}