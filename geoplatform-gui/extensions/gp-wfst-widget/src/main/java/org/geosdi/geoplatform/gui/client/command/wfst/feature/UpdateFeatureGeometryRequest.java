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
package org.geosdi.geoplatform.gui.client.command.wfst.feature;

import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.gui.client.command.wfst.WFSTRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class UpdateFeatureGeometryRequest extends WFSTRequest {

    private static final long serialVersionUID = 6508383449603270966L;
    //
    private String fid;
    private String geometryAttributeName;
    private String wktGeometry;

    public UpdateFeatureGeometryRequest() {
    }

    /**
     * @return the fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return the geometryAttributeName
     */
    public String getGeometryAttributeName() {
        return geometryAttributeName;
    }

    /**
     * @param geometryAttributeName the geometryAttributeName to set
     */
    public void setGeometryAttributeName(String geometryAttributeName) {
        this.geometryAttributeName = geometryAttributeName;
    }

    /**
     * @return the wktGeometry
     */
    public String getWktGeometry() {
        return wktGeometry;
    }

    /**
     * @param wktGeometry the wktGeometry to set
     */
    public void setWktGeometry(String wktGeometry) {
        this.wktGeometry = wktGeometry;
    }

    public GeometryAttributeDTO buildGeometryAttribute() {
        GeometryAttributeDTO geometryAttr = new GeometryAttributeDTO();
        geometryAttr.setName(this.geometryAttributeName);
        geometryAttr.setValue(wktGeometry);
        geometryAttr.setSrid(new Integer(900913));

        return geometryAttr;
    }

    @Override
    public String getCommandName() {
        return "command.wfst.feature.UpdateFeatureGeometryCommand";
    }

    @Override
    public String toString() {
        return super.toString() + " {" + "fid = " + fid
                + ", geometryAttributeName = " + geometryAttributeName
                + ", wktGeometry = " + wktGeometry + '}';
    }

}
