package org.geosdi.geoplatform.support.text.spi;

import java.text.NumberFormat;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPNumberFormatSPISupport<F extends NumberFormat> {

    /**
     * @return {@link F}
     */
    F createNumberFormat();

    /**
     * @return {@link String}
     */
    String getNumberFormatSPISupportName();
}
