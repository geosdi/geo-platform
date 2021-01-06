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
package org.geosdi.geoplatform.connector.geoserver.model.featuretypes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverResourceInfo;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRSDeserializer;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.link.IGPGeoserverMetadataLink;
import org.geosdi.geoplatform.connector.geoserver.model.projection.GPProjectionPolicy;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString(callSuper = true)
@XmlRootElement(name = "featureType")
@XmlAccessorType(value = FIELD)
@XmlType(propOrder = {"name", "nativeName", "namespace", "title", "abstract", "keywords", "metadataLinks", "dataLinks",
        "nativeCRS", "srs", "nativeBoundingBox", "latLonBoundingBox", "projectionPolicy", "enabled", "metadata", "store",
        "cqlFilter", "maxFeatures", "numDecimals", "responseSRS", "overridingServiceSRS", "skipNumberMatched",
        "circularArcPresent", "linearizationTolerance", "attributes"})
public class GPGeoserverFeatureTypeInfo extends GPGeoserverResourceInfo<GPGeoserverNativeBoundingBox> implements IGPGeoserverFeatureTypeInfo {

    private static final long serialVersionUID = 1449200355815165256L;
    //
    private List<IGPGeoserverMetadataLink> metadataLinks;
    private List<IGPGeoserverMetadataLink> dataLinks;
    @JsonDeserialize(using = GPGeoserverCRSDeserializer.class)
    private Object nativeCRS;
    private String cqlFilter;
    private Integer maxFeatures;
    private Integer numDecimals;
    private boolean overridingServiceSRS;
    private boolean skipNumberMatched;
    private boolean circularArcPresent;
    private Integer linearizationTolerance;
    private GPProjectionPolicy projectionPolicy;
    private IGPFeatureTypeAttributes attributes;

    /**
     * @param theNativeCRS
     */
    public void setNativeCRS(@Nullable Object theNativeCRS) {
        checkArgument(((theNativeCRS != null) ? (theNativeCRS instanceof String) || (theNativeCRS instanceof GPGeoserverCRS) : TRUE), "The Parameter nativeCRS must be an Instance of String or GPGeoserverCRS.");
        this.nativeCRS = theNativeCRS;
    }
}