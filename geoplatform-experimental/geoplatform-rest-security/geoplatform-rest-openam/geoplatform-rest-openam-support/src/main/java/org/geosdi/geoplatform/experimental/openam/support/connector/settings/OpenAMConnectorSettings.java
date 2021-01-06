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
package org.geosdi.geoplatform.experimental.openam.support.connector.settings;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMConnectorSettings")
public class OpenAMConnectorSettings implements GPConnectorSettings {

    private static final long serialVersionUID = 6121921445780578968L;
    //
    @Value("openAMConnectorConfigurator{openam.connector.scheme:@null}")
    private String scheme;
    @Value("openAMConnectorConfigurator{openam.connector.host:@null}")
    private String host;
    @Value("openAMConnectorConfigurator{openam.connector.path:@null}")
    private String path;
    @Value("openAMConnectorConfigurator{openam.connector.max_total_connections:@null}")
    private Integer maxTotalConnections;
    @Value("openAMConnectorConfigurator{openam.connector.default_max_per_route:@null}")
    private Integer defaultMaxPerRoute;

    @Override
    public String getScheme() {
        return this.scheme = ((this.scheme != null) && !(this.scheme.trim().isEmpty())) ? this.scheme : "http";
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * @return the maxTotalConnections
     */
    @Override
    public Integer getMaxTotalConnections() {
        return this.maxTotalConnections = ((this.maxTotalConnections != null) ? this.maxTotalConnections : this.defaultMaxTotalConnections());
    }

    /**
     * @return the defaultMaxPerRoute
     */
    @Override
    public Integer getDefaultMaxPerRoute() {
        return this.defaultMaxPerRoute = ((this.defaultMaxPerRoute != null) ? this.defaultMaxPerRoute : this.defaultMaxPerRoute());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument(((this.host != null) && !(this.host.trim().isEmpty())), "The Host Parameter must not be null or an empty value.");
        checkArgument(((this.path != null) && !(this.path.trim().isEmpty())), "The Path Parameter must not be null or an empty value.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {" + "scheme = "
                + getScheme()
                + ", host = " + host
                + ", path = " + path
                + ", maxTotalConnections = " + getMaxTotalConnections()
                + ", defaultMaxPerRoute = " + getDefaultMaxPerRoute()
                + '}';
    }
}
