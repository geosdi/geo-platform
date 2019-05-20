package org.geosdi.geoplatform.oxm.jibx;

import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJibxBindingContext {

    /**
     * @return {@link IMarshallingContext}
     */
    IMarshallingContext createMarshallingContext() throws JiBXException;

    /**
     * @return {@link IUnmarshallingContext}
     */
    IUnmarshallingContext createUnmarshallingContext() throws JiBXException;
}