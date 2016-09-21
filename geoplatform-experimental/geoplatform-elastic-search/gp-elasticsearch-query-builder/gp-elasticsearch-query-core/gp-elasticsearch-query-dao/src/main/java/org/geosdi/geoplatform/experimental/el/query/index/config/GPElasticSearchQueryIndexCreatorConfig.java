package org.geosdi.geoplatform.experimental.el.query.index.config;

import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ComponentScan(value = {"org.geosdi.geoplatform.experimental.el.query.index.settings.config"})
public abstract class GPElasticSearchQueryIndexCreatorConfig {

    /**
     * @param <IC>
     * @return {@link IC}
     */
    public abstract <IC extends GPIndexCreator> IC configureElasticSearchQueryIndexCreator();
}
