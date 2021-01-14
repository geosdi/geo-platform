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
package org.geosdi.geoplatform.connector.server.request.kvp;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.compile;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSRequestKey.BBOX;
import static org.geosdi.geoplatform.connector.server.request.WMSRequestKey.COMMA_SEPARATOR;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class WMSBoundingBoxKeyValuePair extends WMSGetMapBaseRequestKeyValuePair<GPWMSBoundingBox> {

    private static final long serialVersionUID = 147472336825716237L;
    //
    private final GPWMSBoundingBox wmsBoundingBox;

    /**
     * @param theValue
     */
    WMSBoundingBoxKeyValuePair(@Nonnull(when = NEVER) String theValue) {
        super(BBOX.toKey());
        checkArgument((theValue != null) && !(theValue.trim().isEmpty()), "The Parameter value must not be null or an empty string.");
        Double[] boundingBoxValues = compile(COMMA_SEPARATOR.toKey()).splitAsStream(theValue)
                .filter(Objects::nonNull)
                .filter(v -> !v.trim().isEmpty())
                .map(Double::valueOf)
                .toArray(Double[]::new);
        checkArgument((boundingBoxValues != null) && (boundingBoxValues.length == 4), "The Parameter boundingBoxValues must not be null and must contains 4 elements.");
        this.wmsBoundingBox = new WMSBoundingBox(boundingBoxValues[0], boundingBoxValues[1], boundingBoxValues[2], boundingBoxValues[3]);
    }

    /**
     * @return {@link GPWMSBoundingBox}
     */
    @Override
    public GPWMSBoundingBox toValue() {
        return this.wmsBoundingBox;
    }
}