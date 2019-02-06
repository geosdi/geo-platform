package org.geosdi.geoplatform.connector.server.store;

import org.geosdi.geoplatform.connector.server.v111.GPWMSConnectorBuilderV111;
import org.geosdi.geoplatform.connector.server.v111.WMSConnectorBuilderV111;
import org.geosdi.geoplatform.connector.server.v130.GPWMSConnectorBuilderV130;
import org.geosdi.geoplatform.connector.server.v130.WMSConnectorBuilderV130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSConnectorBuilder {

    /**
     * @param <WMSConnectorBuilderV111>
     * @return {@link WMSConnectorBuilderV111}
     */
    <WMSConnectorBuilderV111 extends GPWMSConnectorBuilderV111> WMSConnectorBuilderV111 wmsConnectorBuilderV111();

    /**
     * @param <WMSConnectorBuilderV130>
     * @return {@link WMSConnectorBuilderV130}
     */
    <WMSConnectorBuilderV130 extends GPWMSConnectorBuilderV130> WMSConnectorBuilderV130 wmsConnectorBuilderV130();

    class WMSConnectorBuilder implements GPWMSConnectorBuilder {

        WMSConnectorBuilder() {
        }

        /**
         * @return {@link GPWMSConnectorBuilder}
         */
        public static GPWMSConnectorBuilder wmsConnectorBuilder() {
            return new WMSConnectorBuilder();
        }

        /**
         * @return {@link GPWMSConnectorBuilderV111}
         */
        @Override
        public GPWMSConnectorBuilderV111 wmsConnectorBuilderV111() {
            return WMSConnectorBuilderV111.wmsConnectorBuilderV111();
        }

        /**
         * @return {@link GPWMSConnectorBuilderV130}
         */
        @Override
        public GPWMSConnectorBuilderV130 wmsConnectorBuilderV130() {
            return WMSConnectorBuilderV130.wmsConnectorBuilderV130();
        }
    }
}