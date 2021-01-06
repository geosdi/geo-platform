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
package org.geosdi.geoplatform.rs.support.linker.mediator;

import org.geosdi.geoplatform.rs.support.linker.GPLinkerStrategy;
import org.geosdi.geoplatform.rs.support.request.linker.GPBaseLinkerRequest;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseLinkerStrategyMediator<Request extends GPBaseLinkerRequest> implements GPLinkerStrategyMediator<Request> {

    private final Map<Class<Request>, Map<GPLinkerStrategy.IGPLinkerStrategyKey, GPLinkerStrategy>> strategies;

    /**
     * @param theStrategies
     */
    protected GPBaseLinkerStrategyMediator(@Nonnull(when = NEVER) List<GPLinkerStrategy> theStrategies) {
        checkArgument(((theStrategies != null) && !(theStrategies.isEmpty())), "There are no class of ActaLinkerStrategy in the classpath.");
        this.strategies = theStrategies
                .stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(GPLinkerStrategy::forRequest, toMap(GPLinkerStrategy::getStrategyKey, identity())));
    }

    /**
     * @param theRequest
     * @return {@link Map<GPLinkerStrategy.IGPLinkerStrategyKey, GPLinkerStrategy>}
     * @throws Exception
     */
    @Override
    public Map<GPLinkerStrategy.IGPLinkerStrategyKey, GPLinkerStrategy> findStrategies(@Nonnull(when = NEVER) Request theRequest) throws Exception {
        checkArgument(theRequest != null, "The Parameter request must not be null.");
        return this.strategies.get(theRequest.getClass());
    }
}