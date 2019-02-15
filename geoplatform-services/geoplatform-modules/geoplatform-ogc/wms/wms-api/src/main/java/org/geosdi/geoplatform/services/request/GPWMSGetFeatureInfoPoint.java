package org.geosdi.geoplatform.services.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.services.request.adapter.GPWMSGetFeatureInfoPointAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlJavaTypeAdapter(value = GPWMSGetFeatureInfoPointAdapter.class)
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