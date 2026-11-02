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
package org.geosdi.geoplatform.connector.jaxb.context;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.jakarta.GeoPlatformJakartaContext;
import org.geosdi.geoplatform.jakarta.provider.GeoPlatformJakartaContextProvider;
import org.geosdi.geoplatform.jakarta.repository.GeoPlatformJakartaContextRepository.GeoPlatformJakartaContextKey;

import javax.annotation.Nonnull;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Contesto JAXB {@code jakarta.xml.bind} di una versione WMS, per i binding già rigenerati per jakarta.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSJAXBContext extends GeoPlatformJakartaContext {

    @Getter
    private final WMSVersion version;

    /**
     * @param theContextPath il package dei binding
     * @param theVersion la versione WMS
     * @throws JAXBException se non è possibile creare il contesto
     */
    public WMSJAXBContext(String theContextPath, @Nonnull(when = NEVER) WMSVersion theVersion) throws JAXBException {
        super(theContextPath);
        checkArgument(theVersion != null, "Il parametro theVersion non deve essere null.");
        this.version = theVersion;
    }

    /**
     * @param theContextPath il package dei binding
     * @param theClassLoader il class loader da cui caricare i binding
     * @param theVersion la versione WMS
     * @throws JAXBException se non è possibile creare il contesto
     */
    public WMSJAXBContext(String theContextPath, ClassLoader theClassLoader, @Nonnull(when = NEVER) WMSVersion theVersion) throws JAXBException {
        super(theContextPath, theClassLoader);
        checkArgument(theVersion != null, "Il parametro theVersion non deve essere null.");
        this.version = theVersion;
    }

    /**
     * @param theContextPath il package dei binding
     * @param theClassLoader il class loader da cui caricare i binding
     * @param theProperties le proprietà del contesto
     * @param theVersion la versione WMS
     * @throws JAXBException se non è possibile creare il contesto
     */
    public WMSJAXBContext(String theContextPath, ClassLoader theClassLoader, Map<String, ?> theProperties,
            @Nonnull(when = NEVER) WMSVersion theVersion) throws JAXBException {
        super(theContextPath, theClassLoader, theProperties);
        checkArgument(theVersion != null, "Il parametro theVersion non deve essere null.");
        this.version = theVersion;
    }

    /**
     * @param theVersion la versione WMS
     * @param theClassToBeBound le classi da legare al contesto
     * @throws JAXBException se non è possibile creare il contesto
     */
    public WMSJAXBContext(@Nonnull(when = NEVER) WMSVersion theVersion, Class... theClassToBeBound) throws JAXBException {
        super(theClassToBeBound);
        checkArgument(theVersion != null, "Il parametro theVersion non deve essere null.");
        this.version = theVersion;
    }

    /**
     * @param theJaxbContext il contesto già creato
     * @param theVersion la versione WMS
     */
    public WMSJAXBContext(JAXBContext theJaxbContext, @Nonnull(when = NEVER) WMSVersion theVersion) {
        super(theJaxbContext);
        checkArgument(theVersion != null, "Il parametro theVersion non deve essere null.");
        this.version = theVersion;
    }

    /**
     * @return un nuovo {@link Marshaller}
     * @throws Exception se non è possibile crearlo
     */
    @Override
    public Marshaller acquireMarshaller() throws Exception {
        return super.createMarshaller();
    }

    /**
     * @return un nuovo {@link Unmarshaller}
     * @throws Exception se non è possibile crearlo
     */
    @Override
    public Unmarshaller acquireUnmarshaller() throws Exception {
        return super.createUnmarshaller();
    }

    /**
     * Chiave dei contesti {@link WMSJAXBContext} nel repository dei connettori.
     */
    public static class WMSJAXBContextKey extends GeoPlatformJakartaContextKey {

        /**
         * @param theJakartaContextClass la classe che fornisce il contesto
         */
        public WMSJAXBContextKey(@Nonnull(when = NEVER) Class<? extends GeoPlatformJakartaContextProvider> theJakartaContextClass) {
            super(theJakartaContextClass);
        }

        /**
         * @param val il valore da registrare con questa chiave
         * @return true se il valore è un {@link WMSJAXBContext}
         */
        @Override
        public boolean isCompatibleValue(Object val) {
            return (val instanceof WMSJAXBContext);
        }
    }
}
