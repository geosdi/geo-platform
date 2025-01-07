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

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GPGeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry.GPGeowebcacheEntryValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GPGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GPGeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GeowebcacheSrsBean;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@XmlAccessorType(value = FIELD)
@XmlRootElement(name = "seedRequest")
public class GeowebcacheSeedBody implements GPGeowebcacheSeedBody {

    private static final long serialVersionUID = 5970342361220683441L;
    //
    private String name;
    private String gridSetId;
    @XmlElement(name = "srs", type = GeowebcacheSrsBean.class)
    private GPGeowebcacheSrsBean srs;
    private Integer zoomStart;
    private Integer zoomStop;
    private String format;
    @XmlElement(name = "type", type = GeowebcacheSeedOperationType.class)
    private GPGeowebcacheSeedOperationType type;
    @XmlElement(name = "bounds", type = GeowebcacheBoundsBean.class)
    private GPGeowebcacheBoundsBean bounds;
    private Integer threadCount;
    @XmlElement(name = "parameters")
    private Map<String, List<GPGeowebcacheEntryValue>> parameters = Maps.newHashMap();

    /**
     * @param param
     * @throws Exception
     */
    @Override
    public void addParameter(@Nonnull(when = NEVER) GPGeowebcacheEntryValue... param) throws Exception {
        checkArgument(param != null, "The Parameter param must not be null.");
        this.parameters.put("entry", asList(param));
    }
}