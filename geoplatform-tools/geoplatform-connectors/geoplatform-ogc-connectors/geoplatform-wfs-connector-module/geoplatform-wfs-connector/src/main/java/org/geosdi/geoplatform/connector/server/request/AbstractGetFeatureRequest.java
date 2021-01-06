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
package org.geosdi.geoplatform.connector.server.request;

import lombok.Getter;
import lombok.Setter;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class AbstractGetFeatureRequest<T, Request> extends WFSRequest<T, Request> implements WFSGetFeatureRequest<T> {

    private static final String NAME_GEOMETRY = "the_geom"; // TODO name property geometry is always "the_geom"?
    //
    @Getter
    @Setter
    protected QName typeName;
    @Getter
    @Setter
    protected List<String> featureIDs;
    @Getter
    @Setter
    protected List<String> propertyNames;
    @Getter
    @Setter
    protected BBox bBox;
    protected String srs;
    @Getter
    @Setter
    protected String resultType;
    @Getter
    @Setter
    protected String outputFormat;
    @Getter
    @Setter
    protected BigInteger maxFeatures;
    @Getter
    @Setter
    protected QueryDTO queryDTO;
    @Setter
    private String geometryName;

    public AbstractGetFeatureRequest(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetFeatureIDs() {
        return ((this.featureIDs != null) && !(this.featureIDs.isEmpty()));
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetPropertyNames() {
        return ((this.propertyNames != null) && !(this.propertyNames.isEmpty()));
    }

    /**
     * Sets the value of the SRS query property.
     *
     * @param srs
     */
    @Override
    public void setSRS(String srs) {
        this.srs = srs;
    }

    /**
     * Gets the value of the SRS query property.
     *
     * @return srs
     */
    @Override
    public String getSRS() {
        return this.srs;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryName() {
        return (isSetGeometryName() ? this.geometryName : NAME_GEOMETRY);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetGeometryName() {
        return ((this.geometryName != null) && !(this.geometryName.trim().isEmpty()));
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetQueryDTO() {
        return ((this.queryDTO != null) && (this.queryDTO.isSetQueryRestrictionList()));
    }

    @Override
    public String toString() {
        return this.getClass()
                .getSimpleName() + " {" +
                "  typeName = " + typeName +
                ", featureIDs = " + featureIDs +
                ", bBox = " + bBox +
                ", srs = '" + srs + '\'' +
                ", resultType = '" + resultType + '\'' +
                ", outputFormat = '" + outputFormat + '\'' +
                ", maxFeatures = " + maxFeatures +
                ", queryDTO = " + queryDTO +
                '}';
    }
}