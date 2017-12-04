package org.geosdi.geoplatform.xml.wfs.v110;

import org.geosdi.geoplatform.xml.context.provider.IGPContextServiceProvider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WFSContextServiceProviderTypeV110 implements IGPContextServiceProvider.GPContextProviderType {

    WFS_110 {
        /**
         * @return
         */
        @Override
        public String getType() {
            return this.name();
        }
    };
}
