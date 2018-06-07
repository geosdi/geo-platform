package org.geosdi.geoplatform.gui.client.service.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gwt.json.client.JSONValue;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.*;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class FeatureDTO  implements Serializable  {

    private static final long serialVersionUID = -5193900319713395815L;
    //
    private JSONValue geometry;
    private Map<String,Object> properties = new HashMap<>();
    private String id;

    public FeatureDTO() {
    }

    /**
     *
     * @return {@link JSONValue}
     */
    public JSONValue getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     */
    public void setGeometry(JSONValue geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return {@link Map}
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     *
     * @param properties
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     *
     * @return {@link String}
     */
    public String getId() {
        return id;
    }

    /***
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FeatureDTO{" +
                "geometry=" + geometry +
                ", properties=" + properties +
                ", id='" + id + '\'' +
                '}';
    }
}
