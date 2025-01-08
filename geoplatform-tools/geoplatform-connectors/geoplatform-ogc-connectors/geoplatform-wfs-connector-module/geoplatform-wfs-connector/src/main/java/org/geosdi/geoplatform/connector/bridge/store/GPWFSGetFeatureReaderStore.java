/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.bridge.finder.GPWFSGetFeatureReaderFinder;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWFSGetFeatureReader;
import org.geosdi.geoplatform.connector.server.request.GPWFSGetFeatureOutputFormat;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor.GPImplementorKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.bridge.store.WFSGetFeatureReaderStore.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWFSGetFeatureReaderStore implements WFSGetFeatureReaderStore {

    private static final long serialVersionUID = -6954625511971438724L;
    //
    private static final Logger logger = LoggerFactory.getLogger(GPWFSGetFeatureReaderStore.class);
    private static final GPImplementorFinder<GPWFSGetFeatureReader<?>> finder = new GPWFSGetFeatureReaderFinder<>();
    private static final Map<GPWFSGetFeatureOutputFormat, GPWFSGetFeatureReader<?>> wfsGetFeatureReaders;

    static {
        wfsGetFeatureReaders = of(finder.getValidImplementors());
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", GPWFSGetFeatureReaderStore.class.getSimpleName(), wfsGetFeatureReaders.size());
    }

    /**
     * @param key
     * @return {@link GPWFSGetFeatureReader<?>}
     * @throws Exception
     */
    @Override
    public GPWFSGetFeatureReader<?> getImplementorByKey(@Nonnull(when = NEVER) GPImplementorKey key) throws Exception {
        checkArgument((key != null), "The Parameter key must not be null");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Try to find WFSGetFeatureReader with Key : {}\n", key);
        return wfsGetFeatureReaders.get(key);
    }

    /**
     * @return {@link Set<GPWFSGetFeatureReader<?>>}
     */
    @Override
    public Set<GPWFSGetFeatureReader<?>> getAllImplementors() {
        return unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<GPWFSGetFeatureReader<?>>}
     */
    @Override
    public Collection<GPWFSGetFeatureReader<?>> getValidImplementors() {
        return unmodifiableCollection(finder.getValidImplementors());
    }
}