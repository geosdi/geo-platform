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

import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.file.GPGeoserverDataStoreFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.update.GPParameterUpdate;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;

import javax.annotation.Nonnull;
import java.io.File;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverUpdateDataStoreWithStoreNameRequest extends GPJsonConnectorRequest<Boolean, GeoserverUpdateDataStoreWithStoreNameRequest> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theMethod
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withMethod(@Nonnull(when = NEVER) GPGeoserverUploadMethod theMethod);

    /**
     * @param theFormat
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) GPGeoserverDataStoreFileExtension theFormat);

    /**
     * @param theParameterConfigure
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withConfigure(@Nonnull(when = NEVER) GPGeoserverParameterConfigure theParameterConfigure);

    /**
     * @param theTarget
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withTarget(@Nonnull(when = NEVER) GPGeoserverDataStoreFileExtension theTarget);

    /**
     * @param theUpdate
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withUpdate(@Nonnull(when = NEVER) GPParameterUpdate theUpdate);

    /**
     * @param theCharset
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withCharset(@Nonnull(when = NEVER) String theCharset);

    /**
     * @param theFileName
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withFileName(@Nonnull(when = NEVER) String theFileName);

    /**
     * @param theFile
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    GeoserverUpdateDataStoreWithStoreNameRequest withFile(@Nonnull(when = NEVER) File theFile);

}