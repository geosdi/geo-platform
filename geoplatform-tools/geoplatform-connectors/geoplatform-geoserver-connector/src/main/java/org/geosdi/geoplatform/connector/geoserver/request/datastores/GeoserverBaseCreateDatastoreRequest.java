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
package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.geopkg.IGPGeopackageDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.IGPPostgisDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.jndi.IGPPostgisJndiDatastoreBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.shape.IGPShapeFileDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.shape.IGPShapeFilesDirDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.wfs.IGPWFSDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverBaseCreateDatastoreRequest<Request extends GeoserverBaseCreateDatastoreRequest, Response extends Object> extends GPConnectorRequest<Response> {

    /**
     * @param theWorkspaceName
     */
    Request withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName);

    /**
     * <p>
     *      <ul>To create {@link IGPGeoserverCreateDatastoreBody} see :
     *          <li>{@link IGPPostgisDatastoreBodyBuilder.GPPostgisDatastoreBodyBuilder#postgisDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPPostgisJndiDatastoreBuilder.GPPostgisJndiDatastoreBuilder#postgisJndiDatastoreBuilder()}</li>
     *          <li>{@link IGPGeopackageDatastoreBodyBuilder.GPGeopackageDatastoreBodyBuilder#geopackageDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPShapeFileDatastoreBodyBuilder.GPShapeFileDatastoreBodyBuilder#shapeFileDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPShapeFilesDirDatastoreBodyBuilder.GPShapeFilesDirDatastoreBodyBuilder#shapeFilesDirDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPWFSDatastoreBodyBuilder.GPWFSDatastoreBodyBuilder#wfsDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPGeoserverCreateDatastoreBody#of(String, String, Boolean, Map)}</li>
     *      </ul>
     * </p>
     *
     * @param theDatastoreBody
     */
    Request withDatastoreBody(@Nonnull(when = NEVER) IGPGeoserverCreateDatastoreBody theDatastoreBody);
}