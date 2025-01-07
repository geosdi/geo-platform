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
package org.geosdi.geoplatform.connector.geoserver.model.store;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverBaseWorkspace;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverBaseStore<W extends IGPGeoserverBaseWorkspace> extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * <p>Name of the store</p>
     *
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link String}
     */
    String getDescription();

    /**
     * <p>Description of the store</p>
     *
     * @param theDescription
     */
    void setDescription(String theDescription);

    /**
     * @return {@link String}
     */
    String getType();

    /**
     * <p>Type of store.</p>
     *
     * @param theType
     */
    void setType(String theType);

    /**
     * @return {@link Boolean}
     */
    boolean isEnabled();

    /**
     * <p>Whether the store is enabled</p>
     *
     * @param theEnabled
     */
    void setEnabled(boolean theEnabled);

    /**
     * @return {@link Boolean}
     */
    boolean isDefaultStore();

    /**
     * <p>Whether the store is the default store of the workspace</p>
     *
     * @param theDefaultStore
     */
    void setDefaultStore(boolean theDefaultStore);

    /**
     * @return {@link String}
     */
    String getCapabilitiesURL();

    /**
     * <p>Location of the Capabilities URL where the store originates</p>
     *
     * @param theCapabilitiesURL
     */
    void setCapabilitiesURL(String theCapabilitiesURL);

    /**
     * @return {@link String}
     */
    String getUser();

    /**
     * <p>User name to use when connecting to the remote WMS</p>
     *
     * @param theUser
     */
    void setUser(String theUser);

    /**
     * @return {@link String}
     */
    String getPassword();

    /**
     * <p>Password or hash to use when connecting to the remote WMS</p>
     *
     * @param thePassword
     */
    void setPassword(String thePassword);

    /**
     * @return {@link Integer}
     */
    Integer getMaxConnections();

    /**
     * <p>Maximum number of simultaneous connections to use</p>
     *
     * @param theMaxConnections
     */
    void setMaxConnections(Integer theMaxConnections);

    /**
     * @return {@link String}
     */
    String getReadTimeout();

    /**
     * @param theReadTimeout
     */
    void setReadTimeout(String theReadTimeout);

    /**
     * @return {@link String}
     */
    String getConnectionTimeout();

    /**
     * <p>Time in seconds before connection time out</p>
     *
     * @param theConnectionTimeout
     */
    void setConnectionTimeout(String theConnectionTimeout);

    /**
     * @return {@link W}
     */
    W getWorkspace();

    /**
     * @param theWorkspace
     */
    void setWorkspace(W theWorkspace);

    /**
     * @return {@link Map<String, String>}
     */
    Map<String, String> getMetadata();

    /**
     * @param theMetadata
     */
    void setMetadata(Map<String, String> theMetadata);
}