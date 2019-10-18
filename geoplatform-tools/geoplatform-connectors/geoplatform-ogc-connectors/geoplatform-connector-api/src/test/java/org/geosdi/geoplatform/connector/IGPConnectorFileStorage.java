package org.geosdi.geoplatform.connector;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.File;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPConnectorFileStorage extends Serializable {

    /**
     * @param theKey
     * @return {@link File}
     * @throws Exception
     */
    File find(@Nonnull(when = When.NEVER) String theKey) throws Exception;

    /**
     * @param theEntry
     * @param <E>
     * @throws Exception
     */
    <E extends IGPConnectorFile> void add(@Nonnull(when = When.NEVER) E theEntry) throws Exception;

    /**
     * @param theStorage
     * @return {@link IGPConnectorFileStorage}
     * @throws Exception
     */
    static IGPConnectorFileStorage of(@Nonnull(when = When.NEVER) Map<String, IGPConnectorFile> theStorage) throws Exception {
        return new GPConnectorFileStorage(theStorage);
    }
}