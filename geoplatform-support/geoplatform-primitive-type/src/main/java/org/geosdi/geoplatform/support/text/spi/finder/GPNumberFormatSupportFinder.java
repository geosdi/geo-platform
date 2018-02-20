package org.geosdi.geoplatform.support.text.spi.finder;

import org.geosdi.geoplatform.support.text.spi.GPNumberFormatSPISupport;

import java.text.NumberFormat;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPNumberFormatSupportFinder {

    /**
     * @param <F>
     * @return {@link GPNumberFormatSPISupport<F>}
     */
    <F extends NumberFormat> GPNumberFormatSPISupport<F> findNumberFormatSupport();
}
