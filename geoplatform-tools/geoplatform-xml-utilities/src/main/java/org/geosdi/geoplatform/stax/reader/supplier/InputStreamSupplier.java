package org.geosdi.geoplatform.stax.reader.supplier;

import org.geosdi.geoplatform.stax.reader.builder.streamchain.InputStreamBuildHandler;
import org.geosdi.geoplatform.stax.reader.builder.streamchain.StreamReaderBuildHandler;

import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class InputStreamSupplier implements Supplier<InputStream> {

    private static final StreamReaderBuildHandler streamBuilder = new InputStreamBuildHandler();
    //
    private final Object value;

    public InputStreamSupplier(Object theObject) {
        this.value = theObject;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public InputStream get() {
        try {
            return streamBuilder.buildStream(this.value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
