package org.geosdi.geoplatform.services.request.adapter;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoBoundingBox;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoBoundingBox;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoBoundingBoxAdpter extends XmlAdapter<WMSGetFeatureInfoBoundingBox, GPWMSGetFeatureInfoBoundingBox> {

    /**
     * Convert a value type to a bound type.
     *
     * @param v The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public GPWMSGetFeatureInfoBoundingBox unmarshal(WMSGetFeatureInfoBoundingBox v) throws Exception {
        return v;
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public WMSGetFeatureInfoBoundingBox marshal(GPWMSGetFeatureInfoBoundingBox v) throws Exception {
        return (WMSGetFeatureInfoBoundingBox) v;
    }
}
