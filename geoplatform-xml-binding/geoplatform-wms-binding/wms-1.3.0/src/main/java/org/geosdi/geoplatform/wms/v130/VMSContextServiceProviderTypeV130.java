package org.geosdi.geoplatform.wms.v130;

import org.geosdi.geoplatform.xml.context.provider.IGPContextServiceProvider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum VMSContextServiceProviderTypeV130 implements IGPContextServiceProvider.GPContextProviderType {

    VMS_130 {
        /**
         *
         * @return {@link String}
         */
        @Override
        public String getType() {
            return this.name();
        }
    };
}
