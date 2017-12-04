package org.geosdi.geoplatform.xml.csw;

import org.geosdi.geoplatform.xml.context.provider.IGPContextServiceProvider.GPContextProviderType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum CSWContextProviderTypeV202 implements GPContextProviderType {

    CSW_202 {
        /**
         * @return
         */
        @Override
        public String getType() {
            return this.name();
        }
    };
}
