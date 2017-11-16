package org.geosdi.geoplatform.gui.shared.api;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGeoPlatformExecutor<T> {

    /**
     * @return {@link T}
     */
    T execute();
}
