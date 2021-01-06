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
package org.geosdi.geoplatform.jaxb.repository;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public abstract class GeoPlatformJAXBContextRepository implements JAXBContextRepository {

    private static final long serialVersionUID = -1586699632538763482L;
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final Map<GeoPlatformJAXBContextKey, Object> values = new ConcurrentHashMap<>();

    protected GeoPlatformJAXBContextRepository() {
    }

    /**
     * <p> Register the JAXB Context for the Specific Connector </p>
     *
     * @param key      see also {@link GeoPlatformJAXBContextKey}
     * @param provider {@link Object}
     */
    @Override
    public void registerProvider(@Nonnull(when = NEVER) GeoPlatformJAXBContextKey key, @Nonnull(when = NEVER) Object provider) {
        checkArgument(key != null, "The Parameter key must not be null.");
        if (!key.isCompatibleValue(provider)) {
            throw new IllegalArgumentException("The Provider : " + provider + " is incompatible with Key : " + key);
        }
        if (!values.containsKey(key)) {
            values.put(key, provider);
        }
    }

    /**
     * <p> Retrieve the JAXBContext for Specific Connector registered </p>
     *
     * @param key
     * @return GPConnectorJAXBContext Provider registered for Key
     */
    @Override
    public <P extends GPBaseJAXBContext> P getProvider(GeoPlatformJAXBContextKey key) {
        checkArgument(key != null, "GeoPlatformJAXBContextKey must not be null.");
        logger.debug("##################################CALLED getProvider for Key : {}\n", key);
        return (values.get(key) != null ? (P) values.get(key) : lookUpJAXBContext(key));
    }

    public abstract static class GeoPlatformJAXBContextKey extends RenderingHints.Key {

        private static final AtomicInteger keyID = new AtomicInteger(0);
        private final Class<? extends GeoPlatformJAXBContextProvider> jaxbContextClass;

        /**
         * @param thejaxbContextClass
         */
        public GeoPlatformJAXBContextKey(@Nonnull(when = NEVER) Class<? extends GeoPlatformJAXBContextProvider> thejaxbContextClass) {
            super(keyID.getAndIncrement());
            checkArgument(thejaxbContextClass != null, "The Parameter jaxbContextClass must not be null.");
            this.jaxbContextClass = thejaxbContextClass;
        }

        /**
         * @return the class
         */
        public Class<?> getJAXBContextClass() {
            return jaxbContextClass;
        }

        @Override
        public String toString() {
            return getClass().getName() + "{\n"
                    + " privatekey = " + super.intKey() + "\n"
                    + "}";
        }
    }
}