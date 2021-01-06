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
package org.geosdi.geoplatform.jaxb.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.jaxb.AbstractJAXBContextBuilder;
import org.geosdi.geoplatform.jaxb.pool.config.GPJAXBContextBuilderPoolConfig;
import org.geosdi.geoplatform.jaxb.pool.factory.GPJAXBContextCachePool;
import org.geosdi.geoplatform.jaxb.pool.factory.GPJAXBContextCachePoolFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * <p>Pooled class for {@link org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder}</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPJAXBContextBuilderPool extends AbstractJAXBContextBuilder {

    static {
        jaxbContextBuilderPool = new GenericKeyedObjectPool<>(new GPJAXBContextCachePoolFactory(), new GPJAXBContextBuilderPoolConfig());
    }

    private static volatile GenericKeyedObjectPool<GPJAXBContextCachePool, JAXBContext> jaxbContextBuilderPool;

    private GPJAXBContextBuilderPool() {
    }

    /**
     * @return {@link GPJAXBContextBuilderPool}
     */
    public static GPJAXBContextBuilderPool jaxbContextBuilderPool() {
        return new GPJAXBContextBuilderPool();
    }

    /**
     * @param type
     * @return {@link JAXBContext}
     * @throws JAXBException
     */
    @Override
    protected <T> JAXBContext getContext(Class<T> type) throws JAXBException {
        try {
            GPJAXBContextCachePool key = new GPJAXBContextCachePool(type);
            JAXBContext jaxbContext = jaxbContextBuilderPool.borrowObject(key);
            jaxbContextBuilderPool.returnObject(key, jaxbContext);
            return jaxbContext;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex.getMessage());
        }
    }
}