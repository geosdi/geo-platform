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
package org.geosdi.geoplatform.connector.geoserver.model.about.manifest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@JsonDeserialize(as = GPGeoserverAboutManifestEntry.class)
public interface IGPGeoserverAboutManifestEntry extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getArchiverVersion();

    /**
     * @return {@link String}
     */
    String getBundleLicense();

    /**
     * @return {@link Integer}
     */
    String getSpecificationVersion();

    /**
     * @return {@link Long}
     */
    Long getBndLastModified();

    /**
     * @return {@link String}
     */
    String getBundleName();

    /**
     * @return {@link String}
     */
    String getBundleDescription();

    /**
     * @return {@link String}
     */
    String getBuildJdk();

    /**
     * @return {@link String}
     */
    String getUrl();

    /**
     * @return {@link String}
     */
    String getBundleSymbolicName();

    /**
     * @return {@link String}
     */
    String getBuiltBy();

    /**
     * @return {@link String}
     */
    String getRequireCapability();

    /**
     * @return {@link String}
     */
    String getTool();

    /**
     * @return {@link String}
     */
    String getExtensionName();

    /**
     * @return {@link String}
     */
    String getImplementationTitle();

    /**
     * @return {@link String}
     */
    String getImplementationVersion();

    /**
     * @return {@link String}
     */
    String getImplementationBuildId();

    /**
     * @return {@link Integer}
     */
    Integer getManifestVersion();

    /**
     * @return {@link String}
     */
    String getCreatedBy();

    /**
     * @return {@link String}
     */
    String getImplementationVendorId();

    /**
     * @return {@link String}
     */
    String getBundleDocUrl();

    /**
     * @return {@link String}
     */
    String getBundleVendor();

    /**
     * @return {@link String}
     */
    String getImplementationVendor();

    /**
     * @return {@link Integer}
     */
    Integer getBundleManifestVersion();

    /**
     * @return {@link String}
     */
    String getBundleVersion();

    /**
     * @return {@link String}
     */
    String getSpecificationTitle();

    /**
     * @return {@link String}
     */
    String getImplementationUrl();
}