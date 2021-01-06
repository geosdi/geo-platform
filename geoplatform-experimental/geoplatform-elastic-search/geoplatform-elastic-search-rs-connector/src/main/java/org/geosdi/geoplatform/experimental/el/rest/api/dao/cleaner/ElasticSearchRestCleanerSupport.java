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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.cleaner;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.GPElasticSearchRestDAO;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.index.GPElasticSearchRestIndexDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
class ElasticSearchRestCleanerSupport implements GPElasticSearchCleanerSupport {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchRestCleanerSupport.class);
    //
    private final List<GPElasticSearchRestDAO> values;

    /**
     * @param theValues
     */
    ElasticSearchRestCleanerSupport(@Nonnull(when = NEVER) List<GPElasticSearchRestDAO> theValues) {
        checkArgument((theValues != null) && !(theValues.isEmpty()), "The Parameter values must not be null or an empty list.");
        this.values = theValues.stream()
                .filter(Objects::nonNull)
                .collect(toList());
        checkArgument(!this.values.isEmpty(), "The Parameter values must not contains null values.");
    }

    /**
     * @throws Exception
     */
    @Override
    public void deleteAllIndex() throws Exception {
        fromIterable(this.values)
                .doOnComplete(() -> logger.trace("################{} processed : {} values", this.getClass().getSimpleName(), values.size()))
                .subscribe(GPElasticSearchRestIndexDAO::deleteIndex, e -> e.printStackTrace());
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        logger.trace("#########################Called {}#destroy.", this.getClass().getSimpleName());
    }
}