package org.geosdi.geoplatform.experimental.el.query.index;

import org.geosdi.geoplatform.experimental.el.index.GPAbstractIndexCreator;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
//@ComponentScan(value = {"org.geosdi.geoplatform.experimental.el.query.index.settings.config"})
//@Component(value = "gpElasticSearchQueryIndexCreator")
public class GPElasticSearchQueryIndexCreator extends GPAbstractIndexCreator {

    @Resource(name = "gpQueryIndexSettings")
    private GPIndexSettings gpQueryIndexSettings;

    /**
     * @return {@link GPIndexSettings}
     */
    @Override
    public final <IS extends GPIndexSettings> IS getIndexSettings() {
        return (IS) this.gpQueryIndexSettings;
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
