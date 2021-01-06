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
package org.geosdi.geoplatform.connector.wfs.response;

import org.geosdi.geoplatform.connector.wfs.response.collection.FeatureAttributesMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement(name = "FeatureDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeatureDTO implements Serializable {

    private static final long serialVersionUID = -5477132588177572703L;
    //
    private String fid;
    private String geometry;
    // Non-spatial attributes
    private FeatureAttributesMap attributes;

    public FeatureDTO() {
    }

    /**
     * @param theFID
     */
    public FeatureDTO(String theFID) {
        this.fid = theFID;
    }

    public String getFID() {
        return fid;
    }

    public void setFID(String fid) {
        this.fid = fid;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public FeatureAttributesMap getAttributes() {
        return attributes;
    }

    public void setAttributes(FeatureAttributesMap attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "FeatureDTO {" + "fid = " + fid + ", geometry = " + geometry + ", attributes = " + attributes + '}';
    }
}