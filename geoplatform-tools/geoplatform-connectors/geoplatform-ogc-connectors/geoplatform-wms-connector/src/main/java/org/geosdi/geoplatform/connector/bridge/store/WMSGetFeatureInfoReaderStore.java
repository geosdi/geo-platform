package org.geosdi.geoplatform.connector.bridge.store;

import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WMSGetFeatureInfoReaderStore extends GPImplementorStore<GPWMSGetFeatureInfoReader<?>> {

    /**
     * @return {@link Map<WMSFeatureInfoFormat, GPWMSGetFeatureInfoReader<?>}
     */
    static Map<WMSFeatureInfoFormat, GPWMSGetFeatureInfoReader<?>> of(@Nonnull(when = When.NEVER) Set<GPWMSGetFeatureInfoReader<?>> wmsFeatureInforReaders) {
        checkArgument(wmsFeatureInforReaders != null, "The Parameter wmsGetFeatureInfoReaders must not be null.");
        return wmsFeatureInforReaders.stream()
                .filter(Objects::nonNull)
                .collect(toMap(k -> k.getKey().getImplementorKey(), identity()));
    }
}