/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.jakarta.GPBaseJakartaContext;
import org.geosdi.geoplatform.jakarta.provider.GeoPlatformJakartaContextProvider;
import org.geosdi.geoplatform.jakarta.repository.GeoPlatformJakartaContextRepository;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Repository dei contesti JAXB {@code jakarta.xml.bind} dei connettori. Crea il contesto alla prima richiesta e
 * lo tiene per le successive.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ConnectorJAXBContextRepository extends GeoPlatformJakartaContextRepository {

    /**
     * Crea il provider indicato dalla chiave, lo registra e ne restituisce il contesto.
     *
     * @param key la chiave del contesto
     * @param <P> il tipo del contesto
     * @return {@link P}
     */
    @Override
    public <P extends GPBaseJakartaContext> P lookUpJakartaContext(@Nonnull(when = NEVER) GeoPlatformJakartaContextKey key) {
        checkArgument(key != null, "The Parameter key must not be null.");
        logger.debug("##############################CALLED lookUpJakartaContext with Key : {}\n", key);
        try {
            Object provider = key.getJakartaContextClass().getDeclaredConstructor().newInstance();
            if (!(provider instanceof GeoPlatformJakartaContextProvider)) {
                throw new IllegalArgumentException("The class : " + provider.getClass().getName() + " is not an instance of GeoPlatformJakartaContextProvider");
            }
            P jakartaContext = ((GeoPlatformJakartaContextProvider) provider).getJakartaProvider();
            super.registerProvider(key, jakartaContext);
            return jakartaContext;
        } catch (Exception ex) {
            logger.error("Impossibile creare il contesto jakarta per la chiave {}", key, ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getRepositoryName() {
        return getClass().getName();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getRepositoryName();
    }
}
