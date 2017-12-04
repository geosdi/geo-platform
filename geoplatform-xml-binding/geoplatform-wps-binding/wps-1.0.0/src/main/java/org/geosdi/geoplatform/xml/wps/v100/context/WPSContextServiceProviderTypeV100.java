package org.geosdi.geoplatform.xml.wps.v100.context;

import org.geosdi.geoplatform.xml.context.provider.IGPContextServiceProvider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WPSContextServiceProviderTypeV100 implements IGPContextServiceProvider.GPContextProviderType {

    WPS_100 {
        /**
         * @return
         */
        @Override
        public String getType() {
            return this.name();
        }
    };
}
