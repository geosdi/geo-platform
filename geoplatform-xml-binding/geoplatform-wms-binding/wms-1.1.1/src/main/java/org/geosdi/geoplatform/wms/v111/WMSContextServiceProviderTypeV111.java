package org.geosdi.geoplatform.wms.v111;

import org.geosdi.geoplatform.xml.context.provider.IGPContextServiceProvider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WMSContextServiceProviderTypeV111 implements IGPContextServiceProvider.GPContextProviderType {

    WMS_111 {
        /**
         *
         * @return {@link String}
         */
        @Override
        public String getType() {
            return this.name();
        }
    }
}