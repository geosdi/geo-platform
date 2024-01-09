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
package org.geosdi.geoplatform.connector.geoserver.model.settings.service;

import org.geosdi.geoplatform.connector.geoserver.model.keyword.IGPGeoserverKeyword;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.version.GeoserverGeotoolsVersion;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverServiceSettings extends Serializable {

    /**
     * @return {@link boolean}
     */
    boolean isEnabled();

    /**
     * @param theEnabled
     */
    void setEnabled(boolean theEnabled);

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link String}
     */
    String getTitle();

    /**
     * @param theTitle
     */
    void setTitle(String theTitle);

    /**
     * @return {@link String}
     */
    String getMaintainer();

    /**
     * @return {@link String}
     */
    String getAbstrct();

    /**
     * @param theAbstrct
     */
    void setAbstrct(String theAbstrct);

    /**
     * @return {@link String}
     */
    String getAccessConstraints();

    /**
     * @param theAccessConstranits
     */
    void setAccessConstraints(String theAccessConstranits);

    /**
     * @return {@link String}
     */
    String getFees();

    /**
     * @param theFees
     */
    void setFees(String theFees);

    /**
     * @return {@link GeoserverGeotoolsVersion}
     */
    GeoserverGeotoolsVersion getVersion();

    /**
     * @param theVersions
     */
    void setVersion(GeoserverGeotoolsVersion theVersions);

    /**
     * @return {@link IGPGeoserverKeyword}
     */
    IGPGeoserverKeyword getKeyword();

    /**
     * @param theKeyword
     */
    void setKeyword(IGPGeoserverKeyword theKeyword);

    /**
     * @return {@link Boolean}
     */
    boolean isCiteCompliant();

    /**
     * @param theCiteCompliant
     */
    void setCiteCompliant(boolean theCiteCompliant);

    /**
     * @return {@link String}
     */
    String getOnlineResource();

    /**
     * @param theOnlineResource
     */
    void setOnlineResource(String theOnlineResource);

    /**
     * @return {@link String}
     */
    String getSchemaBaseURL();

    /**
     * @param theSchemaBaseURL
     */
    void setSchemaBaseURL(String theSchemaBaseURL);

    /**
     * @return {@link Boolean}
     */
    boolean isVerbose();

    /**
     * @param theVerbose
     */
    void setVerbose(boolean theVerbose);
}