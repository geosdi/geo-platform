package org.geosdi.geoplatform.experimental.el.query.index.settings.spi.finder;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.index.settings.spi.GPDefaultQueryIndexSPISettings;
import org.geosdi.geoplatform.experimental.el.query.index.settings.spi.IGPQueryIndexSPISettings;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPQueryIndexSettingsFinder implements IGPQueryIndexSettingsFinder {

    /**
     * @return {@link IS}
     */
    @Override
    public <IS extends GPIndexSettings> IS findQueryIndexSettings() {
        Iterator<IGPQueryIndexSPISettings> it = ServiceLoader.load(IGPQueryIndexSPISettings.class).iterator();
        IGPQueryIndexSPISettings queryIndexSPISettings = (it.hasNext() ? it.next() : new GPDefaultQueryIndexSPISettings());
        return queryIndexSPISettings.createQueryIndexSettings();
    }
}
