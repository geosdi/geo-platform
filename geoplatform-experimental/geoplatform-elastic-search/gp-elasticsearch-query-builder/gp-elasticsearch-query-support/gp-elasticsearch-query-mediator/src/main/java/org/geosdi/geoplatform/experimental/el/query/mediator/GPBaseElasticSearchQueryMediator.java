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

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator.IGPElasticSearchQueryColleagueDecorator;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseElasticSearchQueryMediator extends AbstractElasticSearchQueryMediator {

    @GeoPlatformLog
    private static Logger logger;

    /**
     * @param queryColleague
     */
    @Override
    public void registerQueryColleague(IGPElasticSearchQueryColleagueDecorator queryColleague)
            throws Exception {
        checkArgument((queryColleague != null), "The Parameter Query Colleague must " +
                "not be null.");
        logger.trace("################################{} is registering QueryColleague : {}\n",
                this, queryColleague);
        if (!super.isQueryColleagueRegistered(queryColleague.getQueryColleagueKey())) {
            this.queryColleagueRegistry.put(queryColleague.getQueryColleagueKey(), queryColleague);
        }
    }

    /**
     * @param queryColleagueKey
     * @param queryTemplate
     * @param queryTemplateParameters
     * @return {@link R}
     * @throws Exception
     */
    @Override
    public <R, V> R executeQueryColleague(GPBaseIndexCreator.GPIndexSettings queryColleagueKey, String queryTemplate,
            @Nullable Map<String, V> queryTemplateParameters) throws Exception {
        checkArgument((queryColleagueKey != null), "The Parameter QueryColleagueKey must not be null.");
        checkArgument((queryTemplate != null) && !(queryTemplate.isEmpty()),
                "The Parameter QueryTemplate must not be Null or an Empty String.");
        logger.trace("#################################{} executing  ---------------->\n\n{}\n",
                this, queryTemplate);
        return (super.isQueryColleagueRegistered(queryColleagueKey) ? super.getQueryColleague(queryColleagueKey)
                .executeQueryColleague(queryTemplate, queryTemplateParameters) : null);
    }

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     *
     * @throws Exception in case of shutdown errors.
     *                   Exceptions will get logged but not rethrown to allow
     *                   other beans to release their resources too.
     */
    @Override
    public void destroy() throws Exception {
        logger.debug("#############################Called Destroy on : {}\n", this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
