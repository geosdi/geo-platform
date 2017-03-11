/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.mapper;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.reader.GPBaseJacksonReaderSupport;

import java.io.File;
import java.io.Writer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseJacksonMapper<T extends Object> extends GPBaseJacksonReaderSupport<T>
        implements GPJacksonMapper<T> {

    public GPBaseJacksonMapper(Class<T> theEntityClass, JacksonSupport theJacksonSupport) {
        super(((theJacksonSupport != null) ? theJacksonSupport : DEFAULT_MAPPER), theEntityClass);
    }

    @Override
    public String writeAsString(T entity) throws Exception {
        Preconditions.checkArgument(entity != null, "The Parameter Entity must not be null.");
        return this.jacksonSupport.getDefaultMapper().writeValueAsString(entity);
    }

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    @Override
    public void write(File file, T entity) throws Exception {
        Preconditions.checkArgument(entity != null, "The Parameter Entity must not be null.");
        Preconditions.checkArgument(file != null, "The Parameter File must not be null.");
        this.jacksonSupport.getDefaultMapper().writeValue(file, entity);
    }

    /**
     * @param writer
     * @param entity
     * @throws Exception
     */
    @Override
    public void write(Writer writer, T entity) throws Exception {
        Preconditions.checkArgument(entity != null, "The Parameter Entity must not be null.");
        Preconditions.checkArgument(writer != null, "The Parameter Writer must not be null.");
        this.jacksonSupport.getDefaultMapper().writeValue(writer, entity);
    }
}
