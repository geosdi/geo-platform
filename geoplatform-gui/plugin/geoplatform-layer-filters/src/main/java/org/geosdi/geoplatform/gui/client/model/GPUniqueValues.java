package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPUniqueValues extends GeoPlatformBeanModel {

    private static final long serialVersionUID = -3404721246760288083L;

    public enum GPUniqueValueKey {

        UNIQUE_VALUE, UNIQUE_TYPE;
    }

    public GPUniqueValues() {
    }

    public GPUniqueValues setAttributeValue(String value) {
        super.set(GPUniqueValueKey.UNIQUE_VALUE.toString(), value);
        return this;
    }

    public void setAttributeType(String type) {
        super.set(GPUniqueValueKey.UNIQUE_TYPE.toString(), type);
    }

}
