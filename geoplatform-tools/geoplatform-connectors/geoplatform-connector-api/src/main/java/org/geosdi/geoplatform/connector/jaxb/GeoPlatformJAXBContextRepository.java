/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.connector.jaxb;

import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;
import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GeoPlatformJAXBContextRepository {

    private final Map<GeoPlatformJAXBContextKey, Object> values = new HashMap<GeoPlatformJAXBContextKey, Object>();

    public GeoPlatformJAXBContextRepository() {
    }

    /**
     * <p> Register the JAXB Context for the Specific Connector </p>
     *
     * @param key see also {@link GeoPlatformJAXBContextKey}
     * @param provider {@link Object}
     */
    public void registerProvider(GeoPlatformJAXBContextKey key,
            Object provider) {
        if (!key.isCompatibleValue(provider)) {
            throw new IllegalArgumentException("The Provider : "
                    + provider + " is incompatible with Key : " + key);
        }

        synchronized (values) {
            if (!values.containsKey(key)) {
                values.put(key, provider);
            }
        }
    }

    /**
     * <p> Retrieve the JAXBContext for Specific Connector registered </p>
     *
     * @param key
     *
     * @return GPConnectorJAXBContext Provider registered for Key
     */
    public <P extends GPConnectorJAXBContext> P getProvider(GeoPlatformJAXBContextKey key) {
        synchronized (values) {
            return (P) (values.get(key) != null ? values.get(key) : lookUpJAXBContext(
                        key));
        }
    }

    private <P extends GPConnectorJAXBContext> P lookUpJAXBContext(GeoPlatformJAXBContextKey key) {
        Object jaxbContext = null;

        try {
            Class<?> classe = key.getJAXBContextClass();

            jaxbContext = classe.newInstance();

            if (!(jaxbContext instanceof GeoPlatformJAXBContextProvider)) {
                throw new IllegalArgumentException(
                        "The class " + jaxbContext.getClass().getName()
                        + " is not an instance of GeoPlatformJAXBContextProvider");
            }

            registerProvider(key,
                    ((GeoPlatformJAXBContextProvider) jaxbContext).getJAXBProvider());
        } catch (InstantiationException ex) {
            LoggerFactory.getLogger(GeoPlatformJAXBContextRepository.class).error(
                    "Failed to Initialize JAXBContext for Class "
                    + GeoPlatformJAXBContextRepository.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        } catch (IllegalAccessException ex) {
            LoggerFactory.getLogger(GeoPlatformJAXBContextRepository.class).error(
                    "Failed to Initialize JAXBContext for Class "
                    + GeoPlatformJAXBContextRepository.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        }

        return (P) (((GeoPlatformJAXBContextProvider) jaxbContext).getJAXBProvider());
    }

    public abstract static class GeoPlatformJAXBContextKey
            extends RenderingHints.Key {

        private static int val;
        private Class<?> jaxbContextClass;

        public GeoPlatformJAXBContextKey(Class<?> thejaxbContextClass) {
            super(nextVal());
            this.jaxbContextClass = thejaxbContextClass;
        }

        private static synchronized int nextVal() {
            return ++val;
        }

        /**
         * @return the class
         */
        public Class<?> getJAXBContextClass() {
            return jaxbContextClass;
        }

        @Override
        public String toString() {
            return getClass().getName() + " : privatekey = " + super.intKey();
        }
    }
}
