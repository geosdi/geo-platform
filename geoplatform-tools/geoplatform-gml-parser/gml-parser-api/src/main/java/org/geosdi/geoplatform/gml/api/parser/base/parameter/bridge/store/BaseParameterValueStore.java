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
package org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.store;

import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.finder.BaseParameterValueFinder;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterEnum;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterValue;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor.GPImplementorKey;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BaseParameterValueStore implements GPImplementorStore<BaseParameterValue<? extends Object>> {

    private static final long serialVersionUID = -2136562582385932866L;
    //
    private static final Logger logger = LoggerFactory.getLogger(BaseParameterValueStore.class);
    private static final GPImplementorFinder<BaseParameterValue<? extends Object>> finder = new BaseParameterValueFinder();
    private static final Map<GPImplementorKey<BaseParameterEnum>, BaseParameterValue<? extends Object>> baseParameterValueImplementors;

    static {
        baseParameterValueImplementors = finder.getValidImplementors()
                .stream()
                .collect(Collectors.toMap(GPImplementor::getKey, identity()));
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", BaseParameterValueStore.class.getSimpleName(),
                baseParameterValueImplementors.size());
    }

    /**
     * @param key
     * @return {@link BaseParameterValue}
     * @throws Exception
     */
    @Override
    public BaseParameterValue<? extends Object> getImplementorByKey(GPImplementorKey key) throws Exception {
        checkArgument((key != null), "The Parameter key must not be null");
        return baseParameterValueImplementors.get(key);
    }

    /**
     * @return {@link Set<BaseParameterValue>}
     */
    @Override
    public Set<BaseParameterValue<? extends Object>> getAllImplementors() {
        return Collections.unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<BaseParameterValue>}
     */
    @Override
    public Collection<BaseParameterValue<? extends Object>> getValidImplementors() {
        return Collections.unmodifiableCollection(finder.getValidImplementors());
    }
}