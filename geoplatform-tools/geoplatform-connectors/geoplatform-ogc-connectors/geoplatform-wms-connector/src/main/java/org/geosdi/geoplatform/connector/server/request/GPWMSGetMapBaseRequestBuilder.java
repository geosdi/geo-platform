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
package org.geosdi.geoplatform.connector.server.request;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSKeyValuePairBuilder.GPWMSBaseKeyValuePairBuilder;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKeyValuePair;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.toCollection;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSRequestKey.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPWMSGetMapBaseRequestBuilder extends GPWMSBaseKeyValuePairBuilder<GPWMSGetMapBaseRequest, GPWMSGetMapBaseRequestBuilder> {

    GPWMSGetMapBaseRequestBuilder() {
    }

    /**
     * @return {@link GPWMSBaseKeyValuePairBuilder<GPWMSGetMapBaseRequest, GPWMSGetMapBaseRequestBuilder>}
     */
    public static GPWMSBaseKeyValuePairBuilder<GPWMSGetMapBaseRequest, GPWMSGetMapBaseRequestBuilder> builder() {
        return new GPWMSGetMapBaseRequestBuilder();
    }

    /**
     * @return {@link GPWMSGetMapBaseRequest}
     * @throws Exception
     */
    @Override
    protected GPWMSGetMapBaseRequest internalBuild() throws Exception {
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(this.keyValuePair.get());
        GPWMSRequestKeyValuePair<Collection<String>> layers = values.get(LAYERS.toKey());
        GPWMSRequestKeyValuePair<String> srs = values.getOrDefault(SRS.toKey(), values.get(CRS.toKey()));
        GPWMSRequestKeyValuePair<String> width = values.get(WIDTH.toKey());
        GPWMSRequestKeyValuePair<String> height = values.get(HEIGHT.toKey());
        GPWMSRequestKeyValuePair<GPWMSBoundingBox> bbox = values.get(BBOX.toKey());
        checkArgument(layers != null, "The Parameter layers must not be null.");
        checkArgument(srs != null, "The Parameter srs must not be null.");
        checkArgument(width != null, "The Parameter width must not be null.");
        checkArgument(height != null, "The Parameter height must not be null.");
        checkArgument(bbox != null, "The Parameter bbox must not be null.");
        return new WMSGetMapBaseRequest(bbox.toValue(), layers.toValue(), srs.toValue(), width.toValue(), height.toValue(), values.entrySet()
                .stream()
                .filter(this::check)
                .map(Map.Entry::getValue)
                .collect(toCollection(LinkedHashSet::new)));
    }

    /**
     * @param theEntry
     * @return {@link Boolean}
     */
    final Boolean check(@Nonnull(when = NEVER) Map.Entry<String, GPWMSRequestKeyValuePair> theEntry) {
        checkArgument(theEntry != null, "The Parameter entry must not be null.");
        switch (theEntry.getKey()) {
            case "LAYERS":
            case "SRS":
            case "CRS":
            case "WIDTH":
            case "HEIGHT":
            case "BBOX":
                return FALSE;
            default:
                return TRUE;
        }
    }
}