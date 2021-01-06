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

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSRequestKey.COMMA_SEPARATOR;
import static org.geosdi.geoplatform.connector.server.request.WMSRequestKey.LAYERS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class WMSLayersKeyValuePair extends WMSGetMapBaseRequestKeyValuePair<Collection<String>> {

    private static final long serialVersionUID = 4376314577324930255L;
    //
    private Collection<String> layers;

    /**
     * @param theValue
     */
    WMSLayersKeyValuePair(@Nonnull(when = NEVER) String theValue) {
        super(LAYERS.toKey());
        checkArgument((theValue != null) && !(theValue.trim().isEmpty()), "The Parameter value must not be null or an empty string.");
        this.layers = of(theValue.split(COMMA_SEPARATOR.toKey()))
                .filter(Objects::nonNull)
                .filter(v -> !(v.trim().isEmpty()))
                .collect(toCollection(LinkedHashSet::new));
    }

    /**
     * @return {@link Collection<String>}
     */
    @Override
    public Collection<String> toValue() {
        return this.layers;
    }
}