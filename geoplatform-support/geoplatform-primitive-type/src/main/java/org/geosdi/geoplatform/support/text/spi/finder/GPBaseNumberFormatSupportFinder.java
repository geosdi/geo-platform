package org.geosdi.geoplatform.support.text.spi.finder;

import org.geosdi.geoplatform.support.text.spi.GPBaseNumberFormatSupport;
import org.geosdi.geoplatform.support.text.spi.GPNumberFormatSPISupport;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseNumberFormatSupportFinder implements GPNumberFormatSupportFinder {

    /**
     * @return {@link F}
     */
    @Override
    public <F extends NumberFormat> GPNumberFormatSPISupport<F> findNumberFormatSupport() {
        Iterator<GPNumberFormatSPISupport> it = ServiceLoader.load(GPNumberFormatSPISupport.class).iterator();
        GPNumberFormatSPISupport numberFormatSPI = (it.hasNext() ? it.next() : new GPBaseNumberFormatSupport());
        return numberFormatSPI;
    }
}