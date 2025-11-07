/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GPGeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry.GPGeowebcacheEntryValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GPGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GPGeowebcacheSrsBean;
import tools.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedBody.class)
public interface GPGeowebcacheSeedBody extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @param theGridSetId
     */
    void setGridSetId(String theGridSetId);

    /**
     * @return {@link String}
     */
    String getGridSetId();

    /**
     * @return {@link GPGeowebcacheSrsBean}
     */
    <Srs extends GPGeowebcacheSrsBean> Srs getSrs();

    /**
     * @param theSrs
     */
    <Srs extends GPGeowebcacheSrsBean> void setSrs(Srs theSrs);

    /**
     * @return {@link Integer}
     */
    Integer getZoomStart();

    /**
     * @param theZoomStart
     */
    void setZoomStart(Integer theZoomStart);

    /**
     * @return {@link Integer}
     */
    Integer getZoomStop();

    /**
     * @param theZoomStop
     */
    void setZoomStop(Integer theZoomStop);

    /**
     * @return {@link String}
     */
    String getFormat();

    /**
     * @param theFormat
     */
    void setFormat(String theFormat);

    /**
     * @param <SeedOperationType>
     * @return {@link SeedOperationType}
     */
    <SeedOperationType extends GPGeowebcacheSeedOperationType> SeedOperationType getType();

    /**
     * @param theType
     * @param <SeedOperationType>
     */
    <SeedOperationType extends GPGeowebcacheSeedOperationType> void setType(SeedOperationType theType);

    /**
     * @param bounds
     */
    <Bounds extends GPGeowebcacheBoundsBean>void setBounds(Bounds bounds);

    /**
     *
     * @return {@link  Bounds}
     * @param <Bounds>
     */
    <Bounds extends GPGeowebcacheBoundsBean>Bounds getBounds();

    /**
     * @return {@link Integer}
     */
    Integer getThreadCount();

    /**
     * @param theThreadCount
     */
    void setThreadCount(Integer theThreadCount);

    /**
     * @return {@link Map<String, GPGeowebcacheEntryValue>}
     */
    Map<String, List<GPGeowebcacheEntryValue>> getParameters();

    /**
     * @param theParameters
     */
    void setParameters(Map<String, List<GPGeowebcacheEntryValue>> theParameters);

    /**
     * @param param
     * @throws Exception
     */
    void addParameter(@Nonnull(when = NEVER) GPGeowebcacheEntryValue... param) throws Exception;
}