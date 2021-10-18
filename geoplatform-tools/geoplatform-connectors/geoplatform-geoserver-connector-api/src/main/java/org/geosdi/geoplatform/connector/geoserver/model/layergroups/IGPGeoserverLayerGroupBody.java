/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.geoserver.model.layergroups;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.base.GeoserverBaseLayerGroup;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.keywords.IGPGeoserverLayerGroupKeywords;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.publishables.GPGeoserverLayerGroupPublishables;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.style.IGPGeoserverLayerGroupStyles;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.link.IGPGeoserverMetadataLink;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceBody;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverLayerGroupBody.class)
public interface IGPGeoserverLayerGroupBody extends GeoserverBaseLayerGroup {

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @param theMode
     */
    void setMode(GPGeoserverLayerGroupMode theMode);

    /**
     * @param theTitle
     */
    void setTitle(String theTitle);

    /**
     * @param theAbstractText
     */
    void setAbstractText(String theAbstractText);

    /**
     * @return {@link GPGeoserverCreateWorkspaceBody}
     */
    GPGeoserverCreateWorkspaceBody getWorkspace();

    /**
     * @param theWorkspace
     */
    void setWorkspace(GPGeoserverCreateWorkspaceBody theWorkspace);

    /**
     * @param theLayers
     */
    void setLayers(GPGeoserverLayerGroupPublishables theLayers);

    /**
     * @return {@link GPGeoserverLayerGroupPublishables}
     */
    GPGeoserverLayerGroupPublishables getLayers();

    /**
     * @param theKeywords
     */
    void setKeywords(IGPGeoserverLayerGroupKeywords theKeywords);

    /**
     * @param theStyles
     */
    void setStyles(IGPGeoserverLayerGroupStyles theStyles);

    /**
     * @return {@link GPGeoserverLatLonBoundingBox}
     */
    GPGeoserverLatLonBoundingBox getBounds();

    /**
     * @param theBounds
     */
    void setBounds(GPGeoserverLatLonBoundingBox theBounds);

    /**
     * @return {@link IGPGeoserverMetadataLink}
     */
    List<IGPGeoserverMetadataLink> getMetadataLinks();

    /**
     * @param theMetadataLinks
     */
    void setMetadataLinks(List<IGPGeoserverMetadataLink> theMetadataLinks);
}