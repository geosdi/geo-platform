/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.query.mediator;

import com.google.common.collect.ImmutableMap;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator.IGPElasticSearchQueryColleagueDecorator;

import javax.annotation.Resource;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractElasticSearchQueryMediator implements GPElasticSearchQueryMediator {

    @Resource(name = "elasticSearchQueryColleagueRegistry")
    protected Map<GPBaseIndexCreator.GPIndexSettings, IGPElasticSearchQueryColleagueDecorator> queryColleagueRegistry;

    /**
     * @param queryColleagueKey
     * @return {@link org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague}
     * @throws Exception
     */
    @Override
    public IGPElasticSearchQueryColleagueDecorator getQueryColleague(GPBaseIndexCreator.GPIndexSettings queryColleagueKey) throws Exception {
        checkArgument((queryColleagueKey != null), "The Parameter Query Colleague Key must not be null.");
        return (isQueryColleagueRegistered(queryColleagueKey) ? this.queryColleagueRegistry.get(queryColleagueKey) : null);
    }

    /**
     * @param queryColleagueKey
     * @return {@link Boolean}
     */
    protected final Boolean isQueryColleagueRegistered(GPBaseIndexCreator.GPIndexSettings queryColleagueKey) {
        return this.queryColleagueRegistry.containsKey(queryColleagueKey);
    }

    /**
     * @return {@link ImmutableMap<org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings, org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague>}
     */
    @Override
    public ImmutableMap<GPBaseIndexCreator.GPIndexSettings, IGPElasticSearchQueryColleagueDecorator> getAllQueryColleagues() {
        return new ImmutableMap.Builder<GPBaseIndexCreator.GPIndexSettings, IGPElasticSearchQueryColleagueDecorator>()
                .putAll(this.queryColleagueRegistry).build();
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument((this.queryColleagueRegistry != null)
                && !(this.queryColleagueRegistry.isEmpty()), "The Query Colleague Registry must not be " +
                "null or Emty Map.");
    }
}
