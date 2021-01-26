package org.geosdi.geoplatform.jasypt.support.digester;

import org.geosdi.geoplatform.jasypt.support.GPDigesterConfigurator;
import org.geosdi.geoplatform.jasypt.support.GPPooledDigester;
import org.jasypt.digest.config.SimpleStringDigesterConfig;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPJasyptDigesterConfig implements GPJasyptDigesterSupport {

    /**
     * @return {@link GPDigesterConfigurator}
     */
    @Override
    public GPDigesterConfigurator createPooledDigester() {
        return new GPPooledDigester() {
            {
                super.setConfig(createConfig());
            }
        };
    }

    /**
     * @return {@link SimpleStringDigesterConfig}
     */
    protected abstract SimpleStringDigesterConfig createConfig();
}
