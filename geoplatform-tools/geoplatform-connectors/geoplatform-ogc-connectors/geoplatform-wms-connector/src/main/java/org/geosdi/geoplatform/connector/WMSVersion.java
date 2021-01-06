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
package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.server.GPServerConnector;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WMSVersion implements GPServerConnector.GPServerConnectorVersion {

    V111("1.1.1"),
    V130("1.3.0");

    private final String version;

    /**
     * @param theVersion
     */
    WMSVersion(@Nonnull(when = NEVER) String theVersion) {
        checkArgument(((theVersion != null) && !(theVersion.trim().isEmpty())), "The Parameter version must not be null or an empty string.");
        this.version = theVersion;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return this.version;
    }

    /**
     * @param version
     * @return {@link WMSVersion} <p>Default value if no version is found is : {@link WMSVersion#V111}</p>
     */
    public static WMSVersion forValue(String version) {
        Optional<WMSVersion> optional = stream(WMSVersion.values())
                .filter(v -> ((version != null) && !(version.trim().isEmpty())) ?
                        v.getVersion().equalsIgnoreCase(version) : FALSE).findFirst();
        return ((optional != null) && !(optional.equals(Optional.empty()))) ? optional.get() : null;
    }
}
