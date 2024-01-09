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
package org.geosdi.geoplatform.connector.server.request;

import org.checkerframework.checker.units.qual.K;

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
public enum WFSGetFeatureOutputFormat implements GPWFSGetFeatureOutputFormat {

    GML_311("text/xml; subtype=gml/3.1.1"),
    GEOJSON("application/json"),
    CSV("csv");

    private final String outputFormat;

    /**
     * @param theOutputFormat
     */
    WFSGetFeatureOutputFormat(@Nonnull(when = NEVER) String theOutputFormat) {
        checkArgument((theOutputFormat != null) && !(theOutputFormat.trim().isEmpty()), "The Parameter outputFormat must not be null or an empty string.");
        this.outputFormat = theOutputFormat;
    }

    /**
     * @return {@link K}
     */
    @Override
    public GPWFSGetFeatureOutputFormat getImplementorKey() {
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getOutputFormat() {
        return this.outputFormat;
    }

    /**
     * @param theOutputformat
     * @return {@link WFSGetFeatureOutputFormat}
     */
    public static WFSGetFeatureOutputFormat forOutputFormat(String theOutputformat) {
        Optional<WFSGetFeatureOutputFormat> optional = stream(WFSGetFeatureOutputFormat.values())
                .filter(v -> ((theOutputformat != null) && !(theOutputformat.trim().isEmpty()))
                        ? v.outputFormat.equalsIgnoreCase(theOutputformat) : FALSE)
                .findFirst();
        return optional.orElse(GML_311);
    }
}