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