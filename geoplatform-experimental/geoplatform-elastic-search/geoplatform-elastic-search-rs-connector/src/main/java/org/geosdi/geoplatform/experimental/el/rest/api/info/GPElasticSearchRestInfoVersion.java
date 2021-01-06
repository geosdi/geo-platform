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
package org.geosdi.geoplatform.experimental.el.rest.api.info;

import org.elasticsearch.client.core.MainResponse;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestInfoVersion extends Serializable {

    /**
     * @return {@link String}
     */
    String getNumber();

    /**
     * @return {@link String}
     */
    String getBuildFlavor();

    /**
     * @return {@link String}
     */
    String getBuildType();

    /**
     * @return {@link String}
     */
    String getBuildHash();

    /**
     * @return {@link String}
     */
    String getBuildDate();

    /**
     * @return {@link Boolean}
     */
    boolean isSnapshot();

    /**
     * @return {@link String}
     */
    String getLuceneVersion();

    /**
     * @return {@link String}
     */
    String getMinimumWireCompatibilityVersion();

    /**
     * @return {@link String}
     */
    String getMinimumIndexCompatibilityVersion();

    /**
     * @param version
     * @return {@link GPElasticSearchRestInfoVersion}
     */
    static GPElasticSearchRestInfoVersion of(@Nonnull(when = NEVER) MainResponse.Version version) {
        checkArgument(version != null, "The Parameter version must not be null.");
        return new ElasticSearchRestInfoVersion(version.getNumber(), version.getBuildFlavor(), version
                .getBuildType(), version.getBuildHash(), version.getBuildDate(), version.isSnapshot(), version
                .getLuceneVersion(), version.getMinimumWireCompatibilityVersion(), version
                .getMinimumIndexCompatibilityVersion());
    }
}