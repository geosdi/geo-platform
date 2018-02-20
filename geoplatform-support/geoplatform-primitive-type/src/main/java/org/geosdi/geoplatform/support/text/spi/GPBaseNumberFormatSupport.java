package org.geosdi.geoplatform.support.text.spi;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.util.Locale.ITALY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseNumberFormatSupport implements GPNumberFormatSPISupport<DecimalFormat> {

    /**
     * @return {@link DecimalFormat}
     */
    @Override
    public DecimalFormat createNumberFormat() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(ITALY);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(otherSymbols);
        return decimalFormat;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getNumberFormatSPISupportName() {
        return this.getClass().getSimpleName();
    }
}