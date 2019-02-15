package org.geosdi.geoplatform.services.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.services.request.adapter.GPWMSGetFeatureInfoBoundingBoxAdpter;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlJavaTypeAdapter(value = GPWMSGetFeatureInfoBoundingBoxAdpter.class)
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