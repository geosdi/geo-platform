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
package org.geosdi.geoplatform.oxm.castor;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPCastorMarshaller implements IGPCastorMarshaller {

    private final Mapping mapping;

    /**
     * @param mappingName
     * @throws Exception
     */
    public GPCastorMarshaller(@Nonnull(when = When.NEVER) String mappingName) throws Exception {
        checkArgument((mappingName != null) && !(mappingName.trim().isEmpty()), "The Parameter mappingName must not be null or an empty string.");
        Resource resource = new ClassPathResource(mappingName);
        this.mapping = new Mapping(this.getClass().getClassLoader());
        this.mapping.loadMapping(resource.getURL());
    }

    /**
     * @return {@link Marshaller}
     * @throws Exception
     */
    @Override
    public Marshaller createMarshaller() throws Exception {
        Marshaller marshaller = new Marshaller();
        marshaller.setMapping(this.mapping);
        return marshaller;
    }

    /**
     * @return {@link Unmarshaller}
     * @throws Exception
     */
    @Override
    public Unmarshaller createUnmarshaller() throws Exception {
        return new Unmarshaller(this.mapping);
    }
}