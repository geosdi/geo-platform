package org.geosdi.geoplatform.jasypt.support.digester;

import org.geosdi.geoplatform.jasypt.support.GPDigesterConfigurator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJasyptDigesterSupport {

    /**
     * @param <Digester>
     * @return {@link Digester}
     */
    <Digester extends GPDigesterConfigurator> Digester createPooledDigester();
}
