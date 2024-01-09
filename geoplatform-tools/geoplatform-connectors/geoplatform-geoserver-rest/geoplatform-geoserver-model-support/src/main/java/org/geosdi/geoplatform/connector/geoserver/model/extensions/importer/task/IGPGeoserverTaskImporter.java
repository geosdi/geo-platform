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
package org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.GPTaskUpdateMode;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.IGPGeoserverImporterData;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.layer.IGPGeoserverImporterLayer;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.targetstore.IGPGeoserverTargetStoreImporter;

import java.io.Serializable;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@JsonDeserialize(as = GPGeoserverTaskImporter.class)
public interface IGPGeoserverTaskImporter extends Serializable {

    /**
     * @return {@link Long}
     */
    Integer getId();

    /**
     * @return {@link String}
     */
    String getHref();

    /**
     * @return {@link GeoserverStateTask}
     */
    GeoserverStateTask getState();

    /**
     * @return
     */
    GPTaskUpdateMode getUpdateMode();

    /**
     * @return {@link IGPGeoserverImporterData}
     */
    IGPGeoserverImporterData getData();

    /**
     * @return {@link IGPGeoserverTargetStoreImporter}
     */
    IGPGeoserverTargetStoreImporter getTargetStore();

    /**
     * @return {@link String}
     */
    String getProgress();

    /**
     * @return {@link IGPGeoserverTransormChain}
     */
    IGPGeoserverTransormChain getTransformChain();

    /**
     * @return {@link IGPGeoserverImporterLayer}
     */
    IGPGeoserverImporterLayer getLayer();
}