/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRSDeserializer;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypesStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.keyword.GPGeoserverKeyword;
import org.geosdi.geoplatform.connector.geoserver.model.keyword.IGPGeoserverKeyword;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.adapter.GPGeoserverMetadataMapAdapter;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.IGPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverResponseSRS;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store.GPGeoserverCoverageStoreInfo;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@XmlTransient
public abstract class GPGeoserverResourceInfo<NativeBoundingBox extends GPGeoserverBoundingBox<?>> implements IGPGeoserverResourceInfo<NativeBoundingBox> {

    private static final long serialVersionUID = -2589320136857111299L;
    //
    @XmlElementRefs(value = {@XmlElementRef(name = "coverageStore", type = GPGeoserverCoverageStoreInfo.class),
            @XmlElementRef(name = "dataStore", type = GPGeoserverFeatureTypesStoreInfo.class)})
    private GPGeoserverStoreInfo store;
    @XmlElement(name = "abstract")
    private String abstractText;
    private String name;
    private String title;
    private String srs;
    private String nativeName;
    @JsonDeserialize(using = GPGeoserverCRSDeserializer.class)
    @XmlElements(value = {@XmlElement(name = "nativeCRS", type = GPGeoserverCRS.class),
            @XmlElement(name = "nativeCRS", type = String.class)})
    private Object nativeCRS;
    @XmlElement(type = GPGeoserverNamespace.class)
    private IGPGeoserverNamespace namespace;
    private boolean enabled;
    @XmlJavaTypeAdapter(value = GPGeoserverMetadataMapAdapter.class)
    private Map<String, String> metadata;
    @XmlElement(type = GPGeoserverKeyword.class)
    private IGPGeoserverKeyword keywords;
    private GPGeoserverLatLonBoundingBox latLonBoundingBox;
    @XmlAnyElement(lax=true)
    private NativeBoundingBox nativeBoundingBox;
    private GPGeoserverResponseSRS responseSRS;

    /**
     * @param theNativeCRS
     */
    public void setNativeCRS(@Nullable Object theNativeCRS) {
        checkArgument(((theNativeCRS != null) ? (theNativeCRS instanceof String) || (theNativeCRS instanceof GPGeoserverCRS) : TRUE), "The Parameter nativeCRS must be an Instance of String or GPGeoserverCRS.");
        this.nativeCRS = theNativeCRS;
    }
}