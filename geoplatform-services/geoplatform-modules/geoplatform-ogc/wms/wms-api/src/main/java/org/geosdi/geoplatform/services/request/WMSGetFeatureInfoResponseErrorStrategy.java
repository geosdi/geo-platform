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
package org.geosdi.geoplatform.services.request;

import org.geojson.Feature;
import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
abstract class WMSGetFeatureInfoResponseErrorStrategy<V> implements GPWMSGetFeatureInfoReponseErrorStrategy {

    protected final V value;

    /**
     * @param theValue
     */
    WMSGetFeatureInfoResponseErrorStrategy(@Nonnull(when = NEVER) V theValue) {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        this.value = theValue;
    }

    /**
     * @param wmsGetFeatureInfoElement
     * @param ex
     * @throws Exception
     */
    @Override
    public void buildError(@Nonnull(when = NEVER) GPWMSGetFeatureInfoElement wmsGetFeatureInfoElement, @Nonnull(when = NEVER) Exception ex) throws Exception {
        checkArgument(wmsGetFeatureInfoElement != null, "The Parameter wmsGetFeatureInfoElement must not be null.");
        checkArgument(wmsGetFeatureInfoElement.getWmsServerURL() != null, "The Parameter wmsServerURL must not be null.");
        checkArgument(wmsGetFeatureInfoElement.getLayers() != null, "The Parameter layers must not be null.");
        checkArgument(ex != null, "The Parameter exception must not be null.");
        Feature feature = new Feature();
        feature.setId("Error for : ".concat(wmsGetFeatureInfoElement.getWmsServerURL()));
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("LAYERS_IN_ERROR", wmsGetFeatureInfoElement.getLayers().stream().collect(joining(",")));
        properties.put("ERROR_MESSAGE", ex.getMessage());
        this.addFeatureError(feature);
    }

    /**
     * @param wmsGetFeatureInfoResponse
     * @return {@link WMSGetFeatureInfoResponse}
     * @throws Exception
     */
    @Override
    public WMSGetFeatureInfoResponse addError(@Nonnull(when = NEVER) WMSGetFeatureInfoResponse wmsGetFeatureInfoResponse) throws Exception {
        checkArgument(wmsGetFeatureInfoResponse != null, "The Parameter wmsGetFeatureInfoResponse must not be null.");
        if (hasErrors())
            wmsGetFeatureInfoResponse.addFeature(this.value);
        return wmsGetFeatureInfoResponse;
    }

    /**
     * @param feature
     * @throws Exception
     */
    protected abstract void addFeatureError(Feature feature) throws Exception;

    /**
     * @return {@link Boolean}
     */
    protected abstract boolean hasErrors();
}