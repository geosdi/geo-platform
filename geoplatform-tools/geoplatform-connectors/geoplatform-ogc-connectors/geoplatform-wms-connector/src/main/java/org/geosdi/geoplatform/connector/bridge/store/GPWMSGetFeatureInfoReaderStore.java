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
package org.geosdi.geoplatform.connector.bridge.store;

import org.geosdi.geoplatform.connector.bridge.finder.GPWMSGetFeatureInfoReaderFinder;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor.GPImplementorKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;
import static org.geosdi.geoplatform.connector.bridge.store.WMSGetFeatureInfoReaderStore.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoReaderStore implements WMSGetFeatureInfoReaderStore {

    private static final long serialVersionUID = -2890021272284084197L;
    //
    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoReaderStore.class);
    private static final GPImplementorFinder<GPWMSGetFeatureInfoReader<?>> finder = new GPWMSGetFeatureInfoReaderFinder<>();
    private static final Map<WMSFeatureInfoFormat, GPWMSGetFeatureInfoReader<?>> wmsGetFeatureInfoReaders;

    static {
        wmsGetFeatureInfoReaders = of(finder.getValidImplementors());
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", GPWMSGetFeatureInfoReaderStore.class.getSimpleName(), wmsGetFeatureInfoReaders.size());
    }

    /**
     * @param key
     * @return {@link GPWMSGetFeatureInfoReader<?>}
     * @throws Exception
     */
    @Override
    public GPWMSGetFeatureInfoReader<?> getImplementorByKey(GPImplementorKey key) throws Exception {
        checkArgument((key != null), "The Parameter key must not be null");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Try to find WMSGetFeatureInfoReader with Key : {}\n", key);
        return wmsGetFeatureInfoReaders.get(key);
    }

    /**
     * @return {@link Set<GPWMSGetFeatureInfoReader<?>>}
     */
    @Override
    public Set<GPWMSGetFeatureInfoReader<?>> getAllImplementors() {
        return unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<GPWMSGetFeatureInfoReader<?>>}
     */
    @Override
    public Collection<GPWMSGetFeatureInfoReader<?>> getValidImplementors() {
        return unmodifiableCollection(finder.getValidImplementors());
    }
}