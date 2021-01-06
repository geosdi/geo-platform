/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
