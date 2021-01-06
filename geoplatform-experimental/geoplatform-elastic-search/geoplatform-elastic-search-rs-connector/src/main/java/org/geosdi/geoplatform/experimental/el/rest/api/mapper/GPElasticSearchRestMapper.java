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
package org.geosdi.geoplatform.experimental.el.rest.api.mapper;

import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPElasticSearchRestMapper<D extends Document> extends GPBaseMapper<D> {

    /**
     * @param theDocumentClass
     * @param theReader
     */
    public GPElasticSearchRestMapper(@Nonnull(when = NEVER) Class<D> theDocumentClass, @Nullable JacksonSupport theReader) {
        super(theDocumentClass, theReader);
    }

    /**
     * @param theMapperName
     * @param theMapperNameCheck
     * @return {@link String}
     * @throws Exception
     */
    protected String mapperName(String theMapperName, @Nonnull(when = NEVER) GPElasticSearchCheck<String, String, Exception> theMapperNameCheck) throws Exception {
        checkArgument(theMapperNameCheck != null, "The Parameter mapperNameCheck must not null");
        return theMapperNameCheck.apply(theMapperName);
    }

    /**
     *
     * @param theElasticSearchDAOName
     * @return {@link String}
     */
    protected String createMapperName(String theElasticSearchDAOName) {
        return this.getClass()
                .getSimpleName() + "{\n"
                + "  elasticSearchRestDAO = " + theElasticSearchDAOName + "\n"
                + ", documentClass = " + this.getDocumentClassName() + "\n"
                + "}";
    }

    @Override
    public String toString() {
        return this.getMapperName();
    }
}