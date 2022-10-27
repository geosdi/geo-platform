/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public final class GPGeowebcacheConnectorStoreBuilder extends AbstractConnectorBuilder<GeowebcacheConnectorStoreBuilder, GPGeowebcacheConnectorStore> implements GeowebcacheConnectorStoreBuilder {

    private JacksonSupport jacksonSupport;

    private GPGeowebcacheConnectorStoreBuilder() {
    }

    /**
     * @return {@link GeowebcacheConnectorStoreBuilder}
     */
    public static GeowebcacheConnectorStoreBuilder geowebcacheConnectorBuilder() {
        return new GPGeowebcacheConnectorStoreBuilder();
    }

    /**
     * @param theJacksoSupport
     * @return
     */
    public GeowebcacheConnectorStoreBuilder withJacksonSupport(@Nullable JacksonSupport theJacksoSupport) {
        this.jacksonSupport = theJacksoSupport;
        return self();
    }

    /**
     * @param theVersion
     * @return {@link GeowebcacheConnectorStoreBuilder}
     */
    @Override
    public GeowebcacheConnectorStoreBuilder withVersion(String theVersion) {
        return super.withVersion(theVersion);
    }

    /**
     * @return {@link GeowebcacheConnectorStoreBuilder}
     * @throws Exception
     */
    @Override
    public GPGeowebcacheConnectorStore build() throws Exception {
        checkArgument(this.serverUrl != null, "Server URL must not be null");
        return new GPGeowebcacheConnectorStore(this.serverUrl, this.pooledConnectorConfig, this.securityConnector,
                this::toJacksonSupport);
    }

    /**
     * @return {@link ObjectMapper}
     */
    ObjectMapper toJacksonSupport() {
        this.jacksonSupport = ((this.jacksonSupport != null) ? this.jacksonSupport :
                new GPJacksonSupport().configure(NON_NULL));
        return this.jacksonSupport.getDefaultMapper();
    }
}