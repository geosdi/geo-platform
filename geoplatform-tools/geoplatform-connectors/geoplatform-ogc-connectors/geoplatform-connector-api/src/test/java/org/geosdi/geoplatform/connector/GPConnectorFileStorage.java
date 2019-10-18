package org.geosdi.geoplatform.connector;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPConnectorFileStorage implements IGPConnectorFileStorage {

    private static final long serialVersionUID = -1661050417757461241L;
    //
    private final Map<String, IGPConnectorFile> storage;

    /**
     * @param theStorage
     */
    GPConnectorFileStorage(@Nonnull(when = NEVER) Map<String, IGPConnectorFile> theStorage) {
        checkArgument(theStorage != null, "The Parameter storage must not be null.");
        this.storage = theStorage;
    }

    /**
     * @param theKey
     * @return {@link File}
     * @throws Exception
     */
    @Override
    public File find(@Nonnull(when = NEVER) String theKey) throws Exception {
        checkArgument((theKey != null) && !(theKey.trim().isEmpty()), "The Parameter key must not be null or an empty string.");
        checkArgument(this.storage.containsKey(theKey), "There is no File relative to key : " + theKey);
        return this.storage.get(theKey).getFile();
    }

    /**
     * @param theEntry
     * @throws Exception
     */
    @Override
    public <E extends IGPConnectorFile> void add(@Nonnull(when = NEVER) E theEntry) throws Exception {
        checkArgument(theEntry != null, "The Parameter entry must not be null.");
        this.storage.put(theEntry.getKey(), theEntry);
    }
}